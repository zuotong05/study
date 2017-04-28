package com.unisafecap.framework.tools.scaffold;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/** 
 * <P>
 *  java 类型
 * </P> 
 * @author zuotong
 * @since 2017年4月28日
 * @version V1.0 
 */
public class TypeUtils {

    private static final Map<String,String> dbType2JavaType = new HashMap<String, String>();

    /*
    CREATE TABLE `cat` (
            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
            `name` varchar(32) DEFAULT NULL COMMENT '名字',
            `status` tinyint(4) DEFAULT NULL COMMENT '状态',
            `age` int(11) DEFAULT NULL COMMENT '年龄',
            `weight` decimal(22,2) DEFAULT NULL COMMENT '重量',
            `brithday` date DEFAULT NULL COMMENT '出生日期',
            `version` bigint(20) NOT NULL COMMENT '版本号',
            `create_time` datetime DEFAULT NULL COMMENT '创建日期',
            `update_time` datetime DEFAULT NULL COMMENT '更新日期',
            `yn` tinyint(4) DEFAULT '1' COMMENT '是否有效',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1000072 DEFAULT CHARSET=utf8 COMMENT='猫表';

    CREATE TABLE `dup_key` (
            `id` varchar(256) NOT NULL COMMENT '主键',
            `version` bigint(20) NOT NULL COMMENT '版本号',
            `create_time` datetime DEFAULT NULL COMMENT '创建日期',
            `update_time` datetime DEFAULT NULL COMMENT '更新日期',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='防重表';

    */
    static {    	                
    	dbType2JavaType.put("char","String");
        dbType2JavaType.put("varchar","String");
        dbType2JavaType.put("text","String");    
        dbType2JavaType.put("bigint","Long");
        dbType2JavaType.put("tinyint","Integer");
        dbType2JavaType.put("int","Integer");
        dbType2JavaType.put("decimal","java.math.BigDecimal");
        dbType2JavaType.put("date","java.util.Date");
        dbType2JavaType.put("datetime","java.util.Date");
        dbType2JavaType.put("double","Double");
        dbType2JavaType.put("timestamp","java.util.Date");
    }

    public static String fromDbType2JavaType(String type_name) {


        String dbType =type_name.toLowerCase().trim();
        String javaType = dbType2JavaType.get(dbType);
        if(javaType == null)
            throw new RuntimeException(MessageFormat.format("No JavaType Found For DBType {0}",type_name));

        return javaType;
    }
}
