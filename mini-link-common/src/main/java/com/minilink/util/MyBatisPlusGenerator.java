package com.minilink.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.sql.Types;
import java.util.Collections;

/**
 * @Author 徐志斌
 * @Date: 2024/12/6 14:52
 * @Version 1.0
 * @Description: MyBatis-Plus 代码生成器
 */
public class MyBatisPlusGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mini_link?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "Pxy161122";
        String projectPath = System.getProperty("user.dir");
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("徐志斌")
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .outputDir(projectPath + "/src/main/java")
                            .disableOpenDir();
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT || typeCode == Types.TINYINT) {
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> {
                    builder.parent("com.minilink")
                            .controller("controller")
                            .entity("pojo.po")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper"))
                            .build();
                })
                .strategyConfig(builder -> {
                    builder.enableCapitalMode()
                            .enableSkipView()
                            .disableSqlFilter()
                            .addInclude("mini_link_user")
                            .entityBuilder().enableFileOverride().enableLombok()
                            .enableChainModel()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleted")
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .idType(IdType.ASSIGN_ID)
                            .formatFileName("%s")
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            .addTableFills(new Column("deleted", FieldFill.INSERT))
                            .controllerBuilder()
                            .enableFileOverride()
                            .enableHyphenStyle()
                            .enableRestStyle()
                            .formatFileName("%sController")
                            .serviceBuilder()
                            .enableFileOverride()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .mapperBuilder()
                            .enableFileOverride()
                            .enableBaseColumnList()
                            .enableBaseResultMap()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");
                }).templateEngine(new FreemarkerTemplateEngine())
                .templateConfig(builder -> {
                    builder.controller("/templates/controller.java").service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .build();
                }).execute();
        System.out.println("代码生成器执行成功！");
    }
}
