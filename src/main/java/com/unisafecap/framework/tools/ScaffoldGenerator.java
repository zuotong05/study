package com.unisafecap.framework.tools;

import com.unisafecap.framework.tools.scaffold.ScaffoldGen;

public class ScaffoldGenerator {

	public static void main(String[] args) {
		ScaffoldGen generator = new ScaffoldGen("ams.risk", "RiskControlInfo", "风控信息", "risk_control_info");
		generator.execute();

		generator = new ScaffoldGen("ams.risk", "RiskLoanDetail", "贷款单信息", "risk_loan_detail");
		generator.execute();

		generator = new ScaffoldGen("ams.risk", "RiskLoanUser", "个人用户信息", "risk_loan_user");
		generator.execute();

		generator = new ScaffoldGen("ams.risk", "RiskRelationUser", "关系人信息", "risk_relation_user");
		generator.execute();

		generator = new ScaffoldGen("ams.risk", "RiskLoanAccount", "放款账户信息", "risk_loan_account");
		generator.execute();

		generator = new ScaffoldGen("ams.risk", "SecuritySignCert", "签名证书", "security_sign_cert");
		generator.execute();

	}

}