package com.tuuli;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;

/**
 * @author tuuli
 * @time Created in 2023/1/10 23:38
 * @description mybatis-plus 代码生成器
 */
public class Generator {
    public static void main(String[] args) {
       AutoGenerator autoGenerator = new AutoGenerator();

       DataSourceConfig dataSource = new DataSourceConfig();
       dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306/db?serverTimezone=UTC");
       dataSource.setUsername("root");
       dataSource.setPassword("1234");
       autoGenerator.setDataSource(dataSource);

       //设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setAuthor("tuuli");
        globalConfig.setFileOverride(true);
        globalConfig.setMapperName("%sDao");
        globalConfig.setIdType(IdType.AUTO);
        autoGenerator.setGlobalConfig(globalConfig);

        //设置包名配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.tuuli");
        packageConfig.setEntity("domain");
        packageConfig.setMapper("dao");
        autoGenerator.setPackageInfo(packageConfig);

        //策略设置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("tb_course","tb_questions","tb_tea_cour","tb_teacher");
        strategyConfig.setTablePrefix("tb_");
        strategyConfig.setRestControllerStyle(false);
        strategyConfig.setLogicDeleteFieldName("delete");
        strategyConfig.setEntityLombokModel(true);
        autoGenerator.setStrategy(strategyConfig);


        //autoGenerator.execute();
    }
}
