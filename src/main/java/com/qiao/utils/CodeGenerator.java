package com.qiao.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * mp代码生成器
 *
 * @since 2022-01-26
 */
public class CodeGenerator {

    public static void main(String[] args) {
        generate();
    }

    private static void generate() {// 指定数据库
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/webapp?serverTimezone=GMT%2b8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("乔羽") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\桌面\\Application\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.qiao") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\vs-work\\idea\\Application\\src\\main\\resources\\mapper\\"));
                    // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();//entity使用lombok
                    //builder.mapperBuilder().enableMapperAnnotation().build();// 开启 @Mapper 注解
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("orders20") // 设置需要生成的表名                        改表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
                })
                .templateConfig(tem -> {
                    tem.disable(TemplateType.ENTITY,
                            TemplateType.MAPPER,
                            TemplateType.SERVICE,
                            TemplateType.SERVICEIMPL,
                            TemplateType.XML);//只生成controller，其他都禁用
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，·默认的是Velocity引擎模板
                .execute();

    }
}
