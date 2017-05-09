package com.unisafecap.ams.risk.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.unisafecap.ams.risk.model.RiskLoanDetail;
import com.unisafecap.ams.risk.model.RiskLoanUser;
import com.unisafecap.ams.risk.model.RiskRelationUser;
import com.unisafecap.ams.risk.model.dto.request.RiskControlAuditDto;
import com.unisafecap.ams.risk.model.dto.request.RiskControlAuditQueryDto;
import com.unisafecap.ams.risk.model.dto.request.RiskLoanApplyDto;
import com.unisafecap.ams.risk.model.dto.request.RiskLoanUserDto;
import com.unisafecap.ams.risk.model.dto.request.RiskRelationUserDto;
import com.unisafecap.ams.risk.model.dto.response.RiskControlAuditResult;
import com.unisafecap.ams.risk.model.dto.response.RiskLoanDetailResult;
import com.unisafecap.ams.risk.model.enums.AuditStatus;
import com.unisafecap.framework.common.enums.ServiceErrorCode;
import com.unisafecap.framework.common.utils.IdWorker18;
import com.unisafecap.framework.common.utils.StringUtils;
import com.unisafecap.framework.model.dto.RequestData;
import com.unisafecap.framework.model.dto.ResponseData;

/**
 * <P>
 * 风控审核
 * </P>
 * 
 * @author zuotong
 * @since 2017年5月4日
 * @version V1.0
 */

@Service("riskControlService")
public class RiskControlService {
	@Autowired
	private RiskLoanDetailService riskLoanDetailService;

	@Autowired
	private RiskLoanUserService riskLoanUserService;

	@Autowired
	private RiskRelationUserService riskRelationUserService;
	
	@Autowired
	private SecuritySignCertService securitySignCertService;

	public ResponseData<?> riskControlAudit(RequestData requestData) throws Exception {
		ResponseData<Object> response = new ResponseData<Object>();

		if (StringUtils.isBlank(requestData.getOrgCode()) || StringUtils.isBlank(requestData.getTimestamp()) || StringUtils.isBlank(requestData.getBizContent())) {

			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			return response;
		}

		byte[] cleartxt = securitySignCertService.decryptData(requestData.getOrgCode(),requestData.getBizContent());
		if (null == cleartxt) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			response.setMsg("签名信封数据有误");
			return response;
		}

		RiskControlAuditDto auditDto = JSON.parseObject(new String(cleartxt), RiskControlAuditDto.class);

		RiskLoanApplyDto loanApplyDto = auditDto.getLoanApply();

		if (StringUtils.isBlank(auditDto.getOutTradeNo()) || StringUtils.isBlank(loanApplyDto.getTrustProjectCode()) || StringUtils.isBlank(loanApplyDto.getContractNo())) {
			response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
			return response;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("outTradeNo", auditDto.getOutTradeNo());
		List<RiskLoanDetail> list = riskLoanDetailService.findPartByMap(map);
		if (list != null && list.size() > 0) {
			response.setServiceErrorCode(ServiceErrorCode.OUT_TRADE_NO_REPEAT);
			return response;
		}

		map = new HashMap<String, Object>();
		map.put("trustProjectCode", loanApplyDto.getTrustProjectCode());
		map.put("contractNo", loanApplyDto.getContractNo());
		list = riskLoanDetailService.findPartByMap(map);
		if (list != null && list.size() > 0) {
			response.setServiceErrorCode(ServiceErrorCode.CONTRACT_NO_REPEAT);
			return response;
		}

		AuditStatus auditStatus = AuditStatus.PASS;// 默认 通过
		IdWorker18 idWorker = new IdWorker18(0, 0);
		String tradeNo = requestData.getOrgCode() + idWorker.nextId();
		RiskLoanDetail loanDetail = new RiskLoanDetail();
		loanDetail.setOrgCode(requestData.getOrgCode());
		loanDetail.setTimestamp(requestData.getTimestamp());
		loanDetail.setOutTradeNo(auditDto.getOutTradeNo());
		loanDetail.setCallbackUrl(auditDto.getCallbackUrl());
		BeanUtils.copyProperties(loanApplyDto, loanDetail);
		BeanUtils.copyProperties(loanApplyDto.getLoanAccount(), loanDetail);
		loanDetail.setTradeNo(tradeNo);
		loanDetail.setAuditStatus(String.valueOf(auditStatus.getValue()));

		int result = riskLoanDetailService.create4Selective(loanDetail);
		if (result < 1)
			throw new RuntimeException("保存贷款单信息失败");

		// 个人用户信息
		RiskLoanUserDto loanUserDto = loanApplyDto.getLoanUser();
		RiskLoanUser loanUser = new RiskLoanUser();
		BeanUtils.copyProperties(loanUserDto, loanUser);
		loanUser.setLoanDetailId(loanDetail.getId());
		loanUser.setContractNo(loanDetail.getContractNo());
		loanUser.setTrustProjectCode(loanDetail.getTrustProjectCode());
		result = riskLoanUserService.create4Selective(loanUser);
		if (result < 1)
			throw new RuntimeException("个人用户信息失败");

		List<RiskRelationUserDto> relationUsers = loanApplyDto.getRelationUsers();
		for (Iterator<RiskRelationUserDto> iterator = relationUsers.iterator(); iterator.hasNext();) {
			RiskRelationUserDto relationUserDto = iterator.next();
			RiskRelationUser relationUser = new RiskRelationUser();
			BeanUtils.copyProperties(relationUserDto, relationUser);
			result = riskRelationUserService.create4Selective(relationUser);
			if (result < 1)
				throw new RuntimeException("个人用户信息失败");
		}

		RiskControlAuditResult auditResult = new RiskControlAuditResult();
		auditResult.setTradeNo(tradeNo);
		if (auditStatus.getValue().equals(AuditStatus.PASS.getValue())) {// 判断审核是否通过，以后预留用
			response.setServiceErrorCode(ServiceErrorCode.SUCCESS);
			RiskLoanDetailResult loanDetailResult = new RiskLoanDetailResult();
			BeanUtils.copyProperties(loanDetail, loanDetailResult);
			auditResult.setLoanDetail(loanDetailResult);
		} else {
			response.setServiceErrorCode(ServiceErrorCode.HANDLING);
		}
		response.setBizContent(securitySignCertService.encryptData(requestData.getOrgCode(),JSON.toJSONString(auditResult)));

		return response;

	}
	

	@Transactional(readOnly = true)
	public ResponseData<?> riskControlAuditQuery(RequestData requestData) {
		ResponseData<Object> response = new ResponseData<Object>();
		try {
			if (StringUtils.isBlank(requestData.getOrgCode()) || StringUtils.isBlank(requestData.getTimestamp()) || StringUtils.isBlank(requestData.getBizContent())) {

				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				return response;
			}
			
			byte[] cleartxt = securitySignCertService.decryptData(requestData.getOrgCode(),requestData.getBizContent());
			if (null == cleartxt) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				response.setMsg("签名信封数据有误");
				return response;
			}

			RiskControlAuditQueryDto auditQueryDto = JSON.parseObject(new String(cleartxt), RiskControlAuditQueryDto.class);
			String outTradeNo = auditQueryDto.getOutTradeNo();
			String trustProjectCode = auditQueryDto.getTrustProjectCode();
			String contractNo = auditQueryDto.getContractNo();
			if (null == auditQueryDto || StringUtils.isBlank(outTradeNo) || StringUtils.isBlank(trustProjectCode) || StringUtils.isBlank(contractNo)) {
				response.setServiceErrorCode(ServiceErrorCode.PARAMETER_ERROR);
				return response;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("outTradeNo", auditQueryDto.getOutTradeNo());
			map.put("trustProjectCode", auditQueryDto.getTrustProjectCode());
			map.put("contractNo", auditQueryDto.getContractNo());
			List<RiskLoanDetail> list = riskLoanDetailService.findPartByMap(map);
			if (list.isEmpty() || list.size() == 0) {
				response.setServiceErrorCode(ServiceErrorCode.LOAN_NON_EXISTENT);
				return response;
			} else {
				response.setServiceErrorCode(ServiceErrorCode.SUCCESS);
				RiskControlAuditResult auditResult = new RiskControlAuditResult();

				RiskLoanDetailResult loanDetailResult = new RiskLoanDetailResult();
				BeanUtils.copyProperties(list.get(0), loanDetailResult);
				auditResult.setLoanDetail(loanDetailResult);

				response.setBizContent(securitySignCertService.encryptData(requestData.getOrgCode(), JSON.toJSONString(auditResult)));
				return response;
			}
		}
		catch (Exception e) {
			response.setServiceErrorCode(ServiceErrorCode.UNKNOWN_EXCEPTION);
			return response;
		}
	}

	public static void main(String[] args) {
		RiskControlService m = new RiskControlService();
		RequestData requestData = new RequestData();
		requestData.setOrgCode("10050");
		requestData.setTimestamp("2017-05-04 13:21:49");
		requestData.setBizContent("MIIIDgYJKoZIhvcNAQcDoIIH/zCCB/sCAQAxggGTMIIBjwIBADB3MG8xCzAJBgNVBAYTAkNOMRAwDgYDVQQIEwdiZWlqaW5nMRAwDgYDVQQHEwdiZWlqaW5nMREwDwYDVQQKEwhCSFhULUFNUzEMMAoGA1UECxMDQU1TMRswGQYDVQQDExJ3d3cuYm9oYWl0cnVzdC5jb20CBD40OpswDQYJKoZIhvcNAQEBBQAEggEAHzukoy9SHpqW6igxBqLvpLG/r801MV90esgmrjYAUZ4uiS25k3dtmhBcQ8y0x1yyRUMLpmJX/54d1hfrFh+Fg+OOKFvl5GAtxEEF7nRi/aW005W/5bIYSukVqPjR5VKMmIqO3pjjFQKpKv+zMUJ+w1oWct1JasvLbDRWwYvlZfasBNmkHIzh0KgiXRAqZBX5RUItgp3Kk7ycOEHCGS/LapMeHfVvW+I3zVeOJvtI5u1gkM9CjUKL5Dmu9jmEFSRzdzIz5QJFQ9wSSXy1ATOJVdhmX7uhB6MMfk/CGt2VYJcDX4Lfv8dDLTHuEjUvPLkyk/ufkxpBxM64e619Em1QQDCCBl0GCSqGSIb3DQEHATAUBggqhkiG9w0DBwQIbNMGlJNszU+AggY4SWXj7BaNxGBOql2Jo7kdV6C8gnrIQ4rThAqt/DDpVVdnbkj3WZ2Hc2hlQwGtkq2qoHdmsngYcFt+ZVI4gu9XdW+nAWlCtCUYLZ75KZfetMwKOqmWdkIj9YA5n2qwUJYwgMHdfu9xzAQ4Hpo16A2bJnvz+sbAgH1NOUacLOtdobqkT3xUg0SSoa+SytxF+n1WaOjqBW9OVVTluvk4nHYh8O8nrfikRQm+6iSCesDCwzPCIXTJv5HeDLzdAoWUw0ZBROC0tmQo9Ac2ileO/2rfCLuEtzzw8kkUA87eE3unJ2jH5kYi8LR8viYWgJ9zN95j/MxjudWBu3Zic2yk6hBSsg4ikhAy1VMqSHRSoXENf5pIxDq5qcuTiax1WSKue9ngD/RhBNs5tSGafjej2BJFAsjmOXC9nRrvVDjSgkLy1HbeV02s4u/aLctT4saB/Im4FaQ812tdfv3Z3vLU25HrO/uvLSeKsVTYkOR1NHdAxWWj30nRI0xq36PBBZvhl4ed+7UyF2Ff7V4amhycrUEMqJ56MeZnU+Q8s3/RkHglpEBKpiV6hkWhF214A1pWnQZCLfCe2HLlcaWZbkLjM7IidTh+o4teUjsH51TM8paBcaJX2RuQwTVYph/UwfriRCFF5NZsypxqZGW72sjbAR/6NPoxcI9ndZN+n9oVIQIbfZY3j1vIWs5goyKzM3pGBhyvFAFhRIJvTwQJ3vLtUBRQ+LCkGNhe7Axy8mvkWekztRxJVkhgGgw/N9m1gSExxPGIYLWu9UQaNThTurqfpml41OnvscsR1CKHGNyKzuzoj4LXd+GapK0Xaqo8zI9fmjkueONP5cg/XrM57v5HjV7NYFKjxRM+WLEe8AtEencOd6Y3cCieTvLfXCOYh3gYBMlTJY76C7O7DwJY4Af2l0Y25Kg7FETTAmCSJknWBqX2uDrx6Z2zEGMqpaX0w7QYhX03mUg60fr8mBraadNXKgHkOdhUPA3NRZ1/m/MWFXVQGfAw1GyisPWkJFpw/hrSBtvGPBTk7DMJQSW9gFGL/YcxwrKzyNWZq86ZyNtm8A3jYvy4FHBoMUWixL8eXkawf0RASX+YaS2bruKaVAhdfES/0d3jtDL8ocI5/6atHMpgMxJ95J/RdVmlZ8dRDYX0U1DlfcX0Md2HYzK1raWn7mr/Zqt08RgMD1OfOanB4RAh5eS5YD6rzHdz916Wv0WcCYOCVpKrVBRNgMhS27qadSXt1Fz6+HPF4Z/O617BKyJlspmhdR5KgS+MRljhsKqiLtuPtMCcDgBnrrT26Id6PV6dFd0fvsNqSKQ0caTK+5u75tLHkLTnQTe+2N9yHidG4zVkrNzSlE/2MrjzrQMPV0mwtDoKUf9xwcCpIkOgkpKpwi7MruTAdTv1fOY6EnGwHdf7Sn7NHFEzPcRoV9gA8xqaIlc5tR7nzqNxRBslf/sOhdObKss+ABxMAD8HGTWwVZzb1gL36MBZr9Ek1v1FdcJ1LSC+K37tSZowbX4dKLgEzK43c0zJ3ic5a8TUg+oRtodUD/nnwMmgIdbDdi0hhH7V36paT6wYgNUBpI7i8a3mcUDNWHp5tHbHTE63FUy0hFkiWj72YcQmH3spPgWaDijuhm0s3tcM0eyQqCYSwk4qbzE9+X7an/nmy39oOex29j4hFuccdMvgb2zG+BXjT+QYclOku81BVTgX84lgysJYVeCyn6lN3tXj6w1k76bMq5XoK/fNV3oAZ22FxMir/zlGa8QogxeZXyzabSqZX/GpJ+SL6tvj7Ys1Vgd6CVh6J/gDjsqCXHIJZonD1/i8sLL4KGyjphO9Oy5s/OLqpIqv3GVqbmP/HoDuOk91WzSDvRcZwpcfM+rVK082lOpEpef/eXzfP4qlzw2yha6RWpUACVokNQW8Dc5Uf9iEDpISVjnhKHZwF6n9hzIDUiSMaSQYoB24dlhq4yzQrfI54N1mVzin9SKHQM2zk8MOqjE4acEWuUZhWwv3iV7FCDhxdPb9XPMgJFvmgUEw/Uy+jXn2l9fv0iUtvZ6BFAH8oYrXl+4lNHA2Ge/6FDgQnVrC3AbeI7ANKlYJDVWxgsICJX0zDXX6vtKXx0rn7/1N3cCY93v2THNBZGlUXbU=");
		m.riskControlAuditQuery(requestData);

		// reqeustData.setBizContent("MIIIDgYJKoZIhvcNAQcDoIIH/zCCB/sCAQAxggGTMIIBjwIBADB3MG8xCzAJBgNVBAYTAkNOMRAwDgYDVQQIEwdiZWlqaW5nMRAwDgYDVQQHEwdiZWlqaW5nMREwDwYDVQQKEwhCSFhULUFNUzEMMAoGA1UECxMDQU1TMRswGQYDVQQDExJ3d3cuYm9oYWl0cnVzdC5jb20CBD40OpswDQYJKoZIhvcNAQEBBQAEggEAh0Ku8VjNAmPU+hExtcVbJcH2lty/amkR3PJm5NYOO5eNfNA0c0EQ06xyF2U6w9heU+dZ4YV7XHSzlmy8S+8t+RcqdOgD2c3xz8TCZ4IeK0mI+ShzTdvEcjsTnARSoYKfC8sgLlXW5H7OOzzy6SbEgKVrj+/9mLqRdPN9Ahdkd2n9NF4o+51HTb9TAqf9z+i0+5GK9YGOHqLztCgDXtpqRx4ZrBYs8gqF0SfWRPsfNlzyMscsrROTFmj8lvpBEwmewPoqJVubU9HowUuDdGCAm0n/cDBimRLB9DwO3qShEOCETV4WJCUpCiWY230uUCihubmq7Sin4dEjtQk+nA/2wTCCBl0GCSqGSIb3DQEHATAUBggqhkiG9w0DBwQI+qW98Pr+ZN6AggY4YajN3lGyDPglZdfjzYuXdk9CVFqoTyxaVcYrccDJUAuU5mP7KAeiJ92Pj33qa5dKUZItT2otOLNu8giShIx6uBUyj3jTAlb3KwCJYAkBZtvWKWCCr+eM4fmEp6xey3bpKqlc/tHmAa7V5mp1RQoBcMj4G3U09PC50LWcBOVMKFn2CWwiGfGm9QZbIoc9AslelFB+f//saLGN4VJZ9TzQ1t9hvil4iLpTiVR3xQeEAhMqbE36FsClgOirfWf2uOwPRs/1A3kDjva1efEHkS6Z4OkDp0zkvmjRpmOwSLWhI9qSWqDtbv1DD6PjnxZAVLivTE4t0uPqDyHPE2wGZ4GS+wfuHe7DME5qWXZ+hIvT/dEAf3I3/MSQ68Ad9EYSP1v24hf48hvqacEnbHaFtRyuQFIPY6YIPptZhpNBiI3WBe47wNANIydug+114NKl9FaCZNcLqsxdaTPcwvVvlGFvdoKYWCzlbxpkutJ6DC7K0nR7Yxia0pdgWR+ACviHTZbfpAcYvqJH7KF/0PTWyRLzVBsfCSIB5m9TeymiOFSZdyEWp2a23X/KTuTe8DTAW0hFcHj/x0gt7YUAkUNjtHRViD4VWxU/1UpqwwT5GB4AyftKHxh1y1wQTsoGgoFaLd/sz8reOVws1a081ygkZd6FzCyBQbyb2xfPX1wyPBPT6XKOu9J1wyfz8MsTivx5Vi0aU7fUXxce65qFsiZWILiXi+yBkt7lci8fXL/U8PM1ksVSWUKN52E30OmW9icvubyHjTnv0ISy3fEq+7esbsZMfl8rCLXvJYznWLsCV4tlZw0YCMRcGU38Zkaf2h0hHRohoqE9cJ0NSb8HCji7qer6TuV0HOHBaM2KUpE31TDsoHeR8ycqF9SRb/SXuuyvw1pqHjQze2nW008EYTmgiz/S1YiX0kBunSq3SNW+SOMWaOWfyCCjffQGD0kSyj/rw7pPKta4jXk9s82lzoG7J+n2lKUYP/uOfjCmkjELqX7rkH7K8fN7gLO8E5PwTz8hX5BBcTmk0JzEa41JUBfU5yeJOtBbM7Y6hS3sw5p7W8gZ0QVotTU2nQnBrhU8SkjuIVtpvrIUHs18NI7jCKx8pXbMwR/eLbiF99VDGiHzpMPQJGUjO1An2vtRqkHb6GPUwY6CPhwsccWZSUJ2KlsfQ4lnyo9p7jxVwKQz/mOmh/E3Yx274NrjZfBosHxfm+MfFUQswcUhJxa15jvtKbRq8sWbFPgojCbs1itLTmkphbel5qDjnXIdhpxw27GldvxyK7AHFYmca46cKRgCI0DsXha5pBDY0B8OHXdv47Y6nCywj8WVPaWO872d8JxOTU+GhzVnngLC65b8zLhGH2A9QQTZPDa+F4SaS7e5/zrOsZLF9kY82IXuVnylynB7EgYHM8r8JxaLjHniYvbbAGwEDyH/P3gLBZ9SzW6t7PIFpzvBgIFxkVC57okB/ggTcktTOYd81DVVRXZYdSdQqkLM7LvIeUJQ9tNfk6Orw9XSRShBYBGCFP6SgjOfDuBFOQw8cjBp1IhnubTRna3XH5+ExfBz1Kj66C2JqLfCWr3ZCJcCyjWjRNSp4Cb03upsMkNpM++GWGpGOgxsD2VCtYesfUvRGTZ8XFMvJUnCLBlpvgot2Ku33WLhd3t4a298WqXuj3waE8GmUe61iD0+RClAQT8anbwn14+boXHE3CQiku36mh5B5BtRVUiuS+sQXSNUuAhgM0TSK13h9WEMbuNcIe+BFR6xSA687qNGoCjRCDwIWWzTRKwZdfQocGuSJc4BLxsjunkdSl+hTPLyILTCLcx8NuXqVDhDApHQ0m8kIfWva9jE1r+9+R661r+jeiUQsddmRY8tyJce+UbcVelzSHyYt9YAp8wflEs/Gdrd87v/cl+Cveps24id1aXhRUlfgZ4plSYv87MWPw0g3Ur3i8mEfGFZlOOtd/VDG6RypQm+Pb50TcPhTpnrftPf2mNrHtpXkUBNCDdHW2dQzR1MT8G7jS/naYD62/fPxY5yskr2AtVKBQMzWcwABmx+ik329I6TfluUy5kGHSjwyQFxx3ot47OMlgT04AWKXX+9do8JlSYoXmeu7I/gMkd3+2o6KkmkknLPYELcT/A=");
		// reqeustData.setBizContent("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");
		// m.riskControlAudit(reqeustData);
	}

}
