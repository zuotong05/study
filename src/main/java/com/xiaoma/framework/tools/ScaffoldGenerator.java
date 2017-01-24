package com.xiaoma.framework.tools;

import java.util.Scanner;

import com.xiaoma.framework.tools.scaffold.ScaffoldGen;



public class ScaffoldGenerator {

	private static Scanner scanner;

	private static String readString5(String prompt) {
		scanner = new Scanner(System.in);
		System.out.print(prompt);
		String str = scanner.nextLine();
		return str;
	}

	public static void main(String[] args) {
		
//		ScaffoldGen generator1 = new ScaffoldGen("mat", "Info", "资料", "yztf_mat_info");
//		generator1.execute();
//		ScaffoldGen generator2 = new ScaffoldGen("mat", "Category", "资料分类", "yztf_mat_category");
//		generator2.execute();
		ScaffoldGen generator3 = new ScaffoldGen("shop", "User", "钱包明细", "sys_users");
		generator3.execute();
//		ScaffoldGen generator4 = new ScaffoldGen("mat", "Label", "资料标签", "yztf_mat_label");
//		generator4.execute();
//		ScaffoldGen generator5 = new ScaffoldGen("mat", "InfoPermit", "资料权限", "info_permit");
//		generator5.execute();
		
		/*String pkgName = readString5("请输入子系统名:");
		String clzName = readString5("请输入业务对象名,即Model,双词以上要求单词首字大写:");
		String clzComment = readString5("请输入业务对象注释:");
		String tblName = readString5("请输入表名:");
		if (StringUtils.isNotBlank(pkgName) && StringUtils.isNotBlank(clzName) && StringUtils.isNotBlank(clzComment) && StringUtils.isNotBlank(tblName)) {
			System.out.println("*******************************开始生成*******************************");
			ScaffoldGen generator = new ScaffoldGen(pkgName, clzName, clzComment, tblName);
			generator.execute();
			System.out.println("*******************************结束生成*******************************");
		} else {
			System.out.println("输入有空");
		}
		if (null != scanner)
			scanner.close();*/

	}

}