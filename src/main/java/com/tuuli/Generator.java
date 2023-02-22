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
        //获取代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();

        //设置数据库相关配置
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        autoGenerator.setDataSource(dataSource);

        //设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");//设置代码输出位置，否则默认输出到D://目录下
        globalConfig.setOpen(false);//设置生成完毕后是否打开生成代码的目录，一般选择false，不需要打开目录
        globalConfig.setAuthor("tuuli");//作者名字，会在注释中添加作者名字
        globalConfig.setFileOverride(true);//是否覆盖原始生成的文件，选择true后，生成时若原来已有相同文件会自动覆盖，谨慎操作
        globalConfig.setMapperName("%sDao");//设置数据层接口名，%s为占位符，指代模块名称，默认为"%sMapper"
        globalConfig.setIdType(IdType.AUTO);//设置id生成策略
        autoGenerator.setGlobalConfig(globalConfig);

        //设置包名配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.tuuli");//设置生成的包名，默认为"com.baomidou"
        packageConfig.setEntity("domain");//设置实体类包名，默认为entity
        packageConfig.setMapper("dao");//设置数据层包名，默认为mapper
        autoGenerator.setPackageInfo(packageConfig);

        //策略设置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("tb_course", "tb_questions", "tb_tea_cour", "tb_teacher");//设置参与生成的表名，可填一个或多个
        strategyConfig.setTablePrefix("tb_");//设置数据库表名的前缀名称，设置后会省略该前缀
        strategyConfig.setRestControllerStyle(false);//设置是否启用Rest风格
        strategyConfig.setLogicDeleteFieldName("deleted");//设置逻辑删除字段名，没有就不填
        strategyConfig.setEntityLombokModel(true);//设置是否启用Lombok
        autoGenerator.setStrategy(strategyConfig);

        //执行生成操作
        //autoGenerator.execute();
    }
}
