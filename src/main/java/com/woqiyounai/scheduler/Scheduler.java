package com.woqiyounai.scheduler;

import com.woqiyounai.build.BuildTemplate;
import com.woqiyounai.build.TemplateBuilder;
import com.woqiyounai.entity.Columns;
import com.woqiyounai.service.ArrangeDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

//调度者,负责全局的调度
@Component
public class Scheduler {

    @Autowired
    private ArrangeDBService arrangeDBService;
    @Autowired
    private TemplateBuilder templateBuilder;

    private Map<String, List<Columns>> beanMap = null;//实体类 Map

    private String filePath;
    private String testPath;


    private BuildTemplate buildTemplate;

    public void setBuildTemplate(BuildTemplate buildTemplate) {
        this.buildTemplate = buildTemplate;
    }
    public void createProject(){
        readDb();
        createFolder();
        templateBuilder.buildAll(buildTemplate);
    }

    //查询数据库拿到表的信息，创建生成的文件内容
    private Map<String, List<Columns>> readDb(){
        System.out.println("读取数据库 ing...");
        if (buildTemplate.isUseAll()){
            beanMap = arrangeDBService.getAllTable(buildTemplate.getSchemaName());
        }else {
            beanMap = arrangeDBService.selectTable(buildTemplate.getSchemaName(),buildTemplate.getTables());
        }
        System.out.println("读取完毕");
        buildTemplate.setBeanMap(beanMap);
        return beanMap;
    }

    //生成文件夹
    private void createFolder(){
        System.out.println("创建文件夹 ing...");
        String basePath = buildTemplate.getOutputPath() + "/" + buildTemplate.getProjectName();
        File projectFolder = new File(basePath);
        projectFolder.mkdir();
        new File(basePath+"/src").mkdir();
        new File(basePath+"/src/main").mkdir();
        new File(basePath+"/src/test").mkdir();
        new File(basePath+"/src/main/java").mkdir();
        new File(basePath+"/src/test/java").mkdir();
        new File(basePath+"/src/main/resources").mkdir();
        new File(basePath+"/src/main/resources/xml").mkdir();
        String[] split = buildTemplate.getPackages().split("\\.");
        basePath = basePath + "/src/main/java";
        this.testPath = buildTemplate.getOutputPath() + "/" + buildTemplate.getProjectName() + "/src/test/java";
        for (String s : split) {
            basePath = basePath + "/" + s;
            this.testPath = this.testPath + "/" +s;
            new File(basePath).mkdir();
            new File(this.testPath).mkdir();
        }
        this.filePath = basePath;
        new File(basePath + "/" + buildTemplate.getEntityPName()).mkdir();
        new File(basePath + "/" + buildTemplate.getDaoPName()).mkdir();
        new File(basePath + "/" + buildTemplate.getServicePName()).mkdir();
        new File(basePath + "/" + buildTemplate.getServicePName() +"/"+ buildTemplate.getServiceImplPName()).mkdir();
        new File(basePath + "/" + buildTemplate.getControllerPName()).mkdir();
    }
}
