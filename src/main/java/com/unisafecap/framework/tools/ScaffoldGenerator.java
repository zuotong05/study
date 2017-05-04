package com.unisafecap.framework.tools;

import com.unisafecap.framework.tools.scaffold.ScaffoldGen;

public class ScaffoldGenerator {

	public static void main(String[] args) {
		ScaffoldGen generator = new ScaffoldGen("ams.risk", "RiskLoanApply", "贷款单信息", "risk_loan_apply");
		generator.execute();

		generator = new ScaffoldGen("ams.risk", "RiskLoanUser", "个人用户信息", "risk_loan_user");
		generator.execute();

		generator = new ScaffoldGen("ams.risk", "RiskRelationUser", "关系人信息", "risk_relation_user");
		generator.execute();

	}

}