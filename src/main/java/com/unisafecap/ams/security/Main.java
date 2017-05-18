package com.unisafecap.ams.security;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.unisafecap.ams.risk.model.RiskLoanAccount;
import com.unisafecap.ams.risk.model.RiskLoanDetail;
import com.unisafecap.ams.risk.model.RiskLoanUser;
import com.unisafecap.ams.risk.model.dto.request.RiskControlAuditDto;
import com.unisafecap.ams.security.sign.SignEnvelopService;
import com.unisafecap.ams.security.sign.SignEnvelopServiceImpl;
import com.unisafecap.ams.security.sign.util.Base64;
import com.unisafecap.ams.security.sign.util.FileUtil;

public class Main {

	public static void main(String[] args) {
		//byte[] srcData = "{\"outTradeNo\":\"******\",\"trustProjectCode\":\"10050\",\"contractNo\":\"CP1273917239\"}".getBytes(); // 源数据串
		byte[] srcData = "{\"outTradeNo\":\"879a2315-65fd-4e45-b3e8-6c07c5d96c22\",\"trustProjectCode\":\"MDX02\",\"certType\":\"0\",\"certId\":\"411526198709206423\"}".getBytes(); // 源数据串
		RiskControlAuditDto audit = new RiskControlAuditDto();
		audit.setCallbackUrl("");
		audit.setOutTradeNo("879a2315-65fd-4e45-b3e8-6c07c5d96c22");
		audit.setTrustProjectCode("MDX02");
		
		/*RiskLoanDetail loanApply = new RiskLoanDetail();
		loanApply.setContractNo("CP1273917239");
		loanApply.setAssureFee(BigDecimal.valueOf(100.01));			
		audit.setLoanApply(loanApply);*/	
		
		RiskLoanUser loanUser = new RiskLoanUser();
		loanUser.setCustomerName("陈晶晶");
		loanUser.setCertType("0");
		loanUser.setCertId("411526198709206423");
		loanUser.setPhone("18601680291");
		audit.setLoanUser(loanUser);

		/*List<RiskRelationUser> relationUsers = new ArrayList<RiskRelationUser>();
		RiskRelationUser relationUser = new RiskRelationUser();
		relationUser.setCertId("411381198602177118");
		relationUsers.add(relationUser);		
		audit.setRelationUsers(relationUsers);*/

		RiskLoanAccount loanAccount = new RiskLoanAccount();
		loanAccount.setPutoutAccountNo("6212261202005964680");
		audit.setLoanAccount(loanAccount);
		
		//byte[] srcData =	JSON.toJSONString(audit).getBytes();

		// String userDir = System.getProperty("user.dir");
		String file1 = "D:/project/asm-frame/cert/test1.pfx"; // 合作机构信贷系统方证书
		String file2 = "D:/project/asm-frame/cert/bhxt_001.cer"; // 渤海信托小微贷证书

		String priCert = Base64.encode(FileUtil.readFile(file1));
		String pubCert = Base64.encode(FileUtil.readFile(file2));
		String password = "123456"; // 合作方证书密码

		System.out.println("合作机构信贷系统方证书：" + priCert);
		System.out.println("渤海信托小微贷证书：" + pubCert);

		SignEnvelopService signEnvelopService = new SignEnvelopServiceImpl();

		// 签名数字信封
		String signedEvpDataB64FromOrg = signEnvelopService.signEnvelop(priCert, password, pubCert, srcData);
		System.out.println("加密后的数据：" + signedEvpDataB64FromOrg);

		String signedEvpDataB64FromBHXT = "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";

		// 解密数字信封并验签,未通过时返回null
		byte[] cleartxt = signEnvelopService.verifyEnvelop(priCert, password, signedEvpDataB64FromBHXT);
		System.out.println("解密后的数据：" + new String(cleartxt));

	}
}
