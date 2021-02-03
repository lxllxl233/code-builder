package com.woqiyounai;

import com.baomidou.mybatisplus.annotation.IdType;
import com.woqiyounai.build.BuildTemplate;
import com.woqiyounai.scheduler.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CodeBuilderMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CodeBuilderMain.class, args);
        //全局调度者
        Scheduler scheduler = run.getBean(Scheduler.class);
        BuildTemplate buildTemplate = new BuildTemplate();
        buildTemplate
                //设置项目信息/home/lxl/IdeaProjects
                .outputPath("/home/lxl/IdeaProjects")
                .projectName("buildpro")
                //设置包名
                .packages("com.pingjiao")
                .entityPName("entity")
                .daoPName("mapper")
                .servicePName("service")
                .serviceImplPName("impl")
                .controllerPName("controller")
                //设置文件前缀后缀
                .entityPrefix("").entitySuffix("Entity")
                .daoPrefix("").daoSuffix("Mapper")
                .servicePrefix("").serviceSuffix("Service")
                .serviceImplSuffix("Impl")
                .controllerPrefix("").controllerSuffix("Controller")
                //设置数据库主键生成策略
                .idType(IdType.UUID)
                //设置数据库信息(你要生成的项目的)，使用本工程生成项目请修改本工程的配置文件
                .username("root")
                .password("root")
                .url("jdbc:mysql://127.0.0.1:3306/st?serverTimezone=GMT%2B8&characterEncoding=UTF-8")
                .driver("com.mysql.cj.jdbc.Driver")
                //设置数据库连接信息
                .schemaName("pingjiao")
                .voIgnores("id","updateTime","createTime");//生成 vo 对象忽略的属性
                //.tables("tb_t_c_e_bind"); //不配置该项就会扫描该schema下的所有表
        //启动调度器
        scheduler.setBuildTemplate(buildTemplate);
        scheduler.createProject();
    }
}
