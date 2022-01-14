package com.graduation.warning.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyGenerator {
    public static void main(String[] args) {
        //全局配置
        GlobalConfig globalConfig=new GlobalConfig();
        globalConfig.setAuthor("auth")//设置作者
                .setActiveRecord(true)//是否支持AR模式
                .setOutputDir("D:\\work\\project\\education\\education\\server\\src\\main\\java")   //生成文件的路径
                .setFileOverride(true)   //文件覆盖
                .setIdType(IdType.AUTO); //主键策略

        //数据源配置
        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://8.136.0.90:3306/education?useUnicode=true&characterEncoding=utf-8")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("313540638");

        //策略配置
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setCapitalMode(true)  //全局大写
                .setNaming(NamingStrategy.underline_to_camel); //数据库表映射到实体的命名策略
//                .setInclude("edoc_category","edoc_entry");//表名,不指定表名生成全部
        // .setTablePrefix("tbl_");//表前缀

        //包名策略
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setParent("com.graduation.education")
                .setEntity("entity")
                .setMapper("dao")
                .setService("service")
                .setController("controller")
                .setXml("dao");

        AutoGenerator autoGenerator=new AutoGenerator();
        autoGenerator.setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setGlobalConfig(globalConfig)
                .setStrategy(strategyConfig);
        autoGenerator.execute();

    }
}
