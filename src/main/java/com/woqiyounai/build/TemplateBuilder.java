package com.woqiyounai.build;

import com.baomidou.mybatisplus.annotation.IdType;
import com.woqiyounai.entity.Columns;
import com.woqiyounai.output.FileCreaterWork;
import com.woqiyounai.util.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class TemplateBuilder {

    private String entityTemplate;
    private String daoTemplate;
    private String serviceTemplate;
    private String serviceImplTemplate;
    private String controllerTemplate;
    private String propertiesTemplate;
    private String pomTemplate;
    private String testTemplate;
    private String xmlTemplate;
    private String mainTemplate;
    private String configTemplate;

    //工具类
    private String rStaticTemplate;
    private String rObjectTemplate;
    private String resultCodeTemplate;
    //vo类
    private String voTemplate;

    @Autowired
    private FileCreaterWork fileCreaterWork;
    @Autowired
    private ReplaceTemplate replaceTemplate;
    private BuildTemplate buildTemplate;

    public void buildAll(BuildTemplate buildTemplate){

        this.buildTemplate = buildTemplate;

        String javaPath = getJavaPath(buildTemplate);
        String testPath = getTestPath(buildTemplate);
        String projectPath = buildTemplate.getOutputPath()+"/"+buildTemplate.getProjectName();
        readTemplate();

        String entityPackage = "package "+buildTemplate.getPackages()+"."+buildTemplate.getEntityPName()+";";
        String voPackage = "package "+buildTemplate.getPackages()+"."+buildTemplate.getEntityPName()+".vo;";
        String daoPackage = "package "+buildTemplate.getPackages()+"."+buildTemplate.getDaoPName()+";";
        String servicePackage = "package "+buildTemplate.getPackages()+"."+buildTemplate.getServicePName()+";";
        String serviceImplPackage = "package "+buildTemplate.getPackages()+"."+buildTemplate.getServicePName()+"."+buildTemplate.getServiceImplPName()+";";
        String utilImplPackage = "package "+buildTemplate.getPackages()+".util;";
        String controllerPackage = "package "+buildTemplate.getPackages()+"."+buildTemplate.getControllerPName()+";";
        Map<String, List<Columns>> beanMap = buildTemplate.getBeanMap();
        beanMap.forEach((tableName,columns)->{
            String entityName = buildTemplate.getEntityPrefix() + CodeUtils.toHumpClass(tableName) + buildTemplate.getEntitySuffix();
            String voName = entityName+"Vo";
            String daoName = buildTemplate.getDaoPrefix() + CodeUtils.toHumpClass(tableName) + buildTemplate.getDaoSuffix();
            String serviceName = buildTemplate.getServicePrefix() + CodeUtils.toHumpClass(tableName) + buildTemplate.getServiceSuffix();
            String serviceImplName = serviceName + buildTemplate.getServiceImplSuffix();
            String controllerName = buildTemplate.getControllerPrefix() + CodeUtils.toHumpClass(tableName) + buildTemplate.getControllerSuffix();

            String utilImport = "import "+buildTemplate.getPackages()+".util.*;";
            String entityImport = "import "+buildTemplate.getPackages()+"."+buildTemplate.getEntityPName()+"."+entityName+";";
            String voImport = "import "+buildTemplate.getPackages()+"."+buildTemplate.getEntityPName()+".vo."+entityName+"Vo;";
            String daoImport = "import "+buildTemplate.getPackages()+"."+buildTemplate.getDaoPName()+"."+daoName+";";
            String serviceImport = "import "+buildTemplate.getPackages()+"."+buildTemplate.getServicePName()+"."+serviceName+";";
            String serviceImplImport = "import "+buildTemplate.getPackages()+"."+buildTemplate.getServicePName()+"."+buildTemplate.getServiceImplPName()+"."+serviceImplName+";";
            String controllerImport = "import "+buildTemplate.getPackages()+"."+buildTemplate.getControllerPName()+"."+controllerName+";";

            String tempTemplate = entityTemplate;
            tempTemplate = replaceTemplateName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateParamName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateImport(tempTemplate,entityImport,daoImport,serviceImport,serviceImplImport,
                    controllerImport,voImport,utilImport);
            tempTemplate = replaceTemplatePackage(tempTemplate,entityPackage,daoPackage,servicePackage,serviceImplPackage,controllerPackage
                    ,utilImplPackage,voPackage);
            buildEntity(tempTemplate,columns,javaPath+"/"+buildTemplate.getEntityPName()+"/"+entityName+".java",buildTemplate.getIdType());

            tempTemplate = daoTemplate;
            tempTemplate = replaceTemplateName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateParamName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateImport(tempTemplate,entityImport,daoImport,serviceImport,serviceImplImport,
                    controllerImport,voImport,utilImport);
            tempTemplate = replaceTemplatePackage(tempTemplate,entityPackage,daoPackage,servicePackage,serviceImplPackage,controllerPackage
                    ,utilImplPackage,voPackage);
            buildDao(tempTemplate,javaPath+"/"+buildTemplate.getDaoPName()+"/"+daoName+".java");

            tempTemplate = serviceTemplate;
            tempTemplate = replaceTemplateName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateParamName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateImport(tempTemplate,entityImport,daoImport,serviceImport,serviceImplImport,
                    controllerImport,voImport,utilImport);
            tempTemplate = replaceTemplatePackage(tempTemplate,entityPackage,daoPackage,servicePackage,serviceImplPackage,controllerPackage
                    ,utilImplPackage,voPackage);
            buildService(tempTemplate,javaPath+"/"+buildTemplate.getServicePName()+"/"+serviceName+".java");

            tempTemplate = serviceImplTemplate;
            tempTemplate = replaceTemplateName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateParamName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateImport(tempTemplate,entityImport,daoImport,serviceImport,serviceImplImport,
                    controllerImport,voImport,utilImport);
            tempTemplate = replaceTemplatePackage(tempTemplate,entityPackage,daoPackage,servicePackage,serviceImplPackage,controllerPackage
                    ,utilImplPackage,voPackage);
            buildServiceImpl(tempTemplate,javaPath+"/"+buildTemplate.getServicePName()+"/"+buildTemplate.getServiceImplPName()+"/"+serviceImplName+".java");

            tempTemplate = controllerTemplate;
            tempTemplate = replaceTemplateName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateParamName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateImport(tempTemplate,entityImport,daoImport,serviceImport,serviceImplImport,
                    controllerImport,voImport,utilImport);
            tempTemplate = replaceTemplatePackage(tempTemplate,entityPackage,daoPackage,servicePackage,serviceImplPackage,controllerPackage
                    ,utilImplPackage,voPackage);
            buildController(tempTemplate,javaPath+"/"+buildTemplate.getControllerPName()+"/"+controllerName+".java");

            tempTemplate = rStaticTemplate;
            tempTemplate = replaceTemplateName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateParamName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateImport(tempTemplate,entityImport,daoImport,serviceImport,serviceImplImport,
                    controllerImport,voImport,utilImport);
            tempTemplate = replaceTemplatePackage(tempTemplate,entityPackage,daoPackage,servicePackage,serviceImplPackage,controllerPackage
                    ,utilImplPackage,voPackage);
            buildController(tempTemplate,javaPath+"/util/RStatic.java");

            tempTemplate = rObjectTemplate;
            tempTemplate = replaceTemplateName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateParamName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateImport(tempTemplate,entityImport,daoImport,serviceImport,serviceImplImport,
                    controllerImport,voImport,utilImport);
            tempTemplate = replaceTemplatePackage(tempTemplate,entityPackage,daoPackage,servicePackage,serviceImplPackage,controllerPackage
                    ,utilImplPackage,voPackage);
            buildController(tempTemplate,javaPath+"/util/RObject.java");

            tempTemplate = resultCodeTemplate;
            tempTemplate = replaceTemplateName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateParamName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateImport(tempTemplate,entityImport,daoImport,serviceImport,serviceImplImport,
                    controllerImport,voImport,utilImport);
            tempTemplate = replaceTemplatePackage(tempTemplate,entityPackage,daoPackage,servicePackage,serviceImplPackage,controllerPackage
                    ,utilImplPackage,voPackage);
            buildController(tempTemplate,javaPath+"/util/ResultCode.java");

            tempTemplate = voTemplate;
            tempTemplate = replaceTemplateName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateParamName(tempTemplate,entityName,daoName,serviceName,serviceImplName,controllerName,voName);
            tempTemplate = replaceTemplateImport(tempTemplate,entityImport,daoImport,serviceImport,serviceImplImport,
                    controllerImport,voImport,utilImport);
            tempTemplate = replaceTemplatePackage(tempTemplate,entityPackage,daoPackage,servicePackage,serviceImplPackage,controllerPackage
                    ,utilImplPackage,voPackage);
            buildVoEntity(tempTemplate,columns,javaPath+"/"+buildTemplate.getEntityPName()+"/vo/"+entityName+"Vo.java");

            buildXml(xmlTemplate,buildTemplate.getOutputPath()+"/"+buildTemplate.getProjectName()+"/src/main/resources/xml/"+daoName+".xml",buildTemplate.getPackages()+"."+buildTemplate.getDaoPName()+"."+daoName);

        });
        buildPom(pomTemplate,buildTemplate.getOutputPath()+"/"+buildTemplate.getProjectName()+"/pom.xml",buildTemplate);
        buildProperties(propertiesTemplate,buildTemplate.getOutputPath()+"/"+buildTemplate.getProjectName()+"/src/main/resources/application.properties",buildTemplate);
        buildTest(testTemplate,testPath+"/TemplateTests.java",buildTemplate.getPackages());
        String mainName = buildTemplate.getProjectName().substring(0,1).toUpperCase()+buildTemplate.getProjectName().substring(1)+"Main";
        buildMain(mainTemplate,javaPath+"/"+mainName+".java","package "+buildTemplate.getPackages()+";",mainName);
        buildConfig(configTemplate,javaPath+"/config/SwaggerConfig.java","package "+buildTemplate.getPackages()+".config;");
    }

    public String replaceTemplateImport(String template,String entityImport,String daoImport,String serviceImport,
                                        String serviceImplImport,String controllerImport,
                                        String voImport,String utilImport){
        template = template
                .replaceAll("\\$\\{entityImport}",entityImport)
                .replaceAll("\\$\\{daoImport}",daoImport)
                .replaceAll("\\$\\{serviceImport}",serviceImport)
                .replaceAll("\\$\\{serviceImplImport}",serviceImplImport)
                .replaceAll("\\$\\{controllerImport}",controllerImport)
                .replaceAll("\\$\\{voImport}",voImport)
                .replaceAll("\\$\\{utilImport}",utilImport);
        return template;
    }

    public String replaceTemplateName(String template,String entityName,String daoName,String serviceName
            ,String serviceImplName,String controllerName,String voName) {
        template = template
                .replaceAll("\\$\\{entityName}",entityName)
                .replaceAll("\\$\\{daoName}",daoName)
                .replaceAll("\\$\\{serviceName}",serviceName)
                .replaceAll("\\$\\{serviceImplName}",serviceImplName)
                .replaceAll("\\$\\{controllerName}",controllerName)
                .replaceAll("\\$\\{voName}",voName);
        return template;
    }

    public String replaceTemplateParamName(String template,String entityName,String daoName,String serviceName
            ,String serviceImplName,String controllerName,String voName) {
        template = template
                .replaceAll("\\$\\{entityParamName}",toLowerCaseFirstOne(entityName))
                .replaceAll("\\$\\{daoParamName}",toLowerCaseFirstOne(daoName))
                .replaceAll("\\$\\{serviceParamName}",toLowerCaseFirstOne(serviceName))
                .replaceAll("\\$\\{serviceImplParamName}",toLowerCaseFirstOne(serviceImplName))
                .replaceAll("\\$\\{controllerParamName}",toLowerCaseFirstOne(controllerName))
                .replaceAll("\\$\\{voParamName}",toLowerCaseFirstOne(voName));
        return template;
    }

    public String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public String replaceTemplatePackage(String template,String entityPackage,String daoPackage,String servicePackage,
                                         String serviceImplPackage,String controllerPackage,String utilImplPackage,String voPackage){
        template = template
                .replaceAll("\\$\\{entityPackage}",entityPackage)
                .replaceAll("\\$\\{daoPackage}",daoPackage)
                .replaceAll("\\$\\{servicePackage}",servicePackage)
                .replaceAll("\\$\\{serviceImplPackage}",serviceImplPackage)
                .replaceAll("\\$\\{controllerPackage}",controllerPackage)
                .replaceAll("\\$\\{utilPackage}",utilImplPackage)
                .replaceAll("\\$\\{voPackage}",voPackage);
        return template;
    }

    //构建 entity 文件
    private void buildEntity(String template,List<Columns> columns,String path,IdType idType){
        String properties = "";
        for (Columns column : columns) {
            properties = properties + ("\t@ApiModelProperty(value = \""+column.getColumnComment()+"\")\n");
            if (column.getColumnKey().equals("PRI")){
                properties = properties + ("\t@TableId(value = \""+column.getColumnName()+"\", type =  IdType."+idType+")\n");

            }
            String columnName = CodeUtils.toHumpName(column.getColumnName());
            properties = properties + "\tprivate "+
                    //替换数据类型
                    replaceTemplate.replaceType(column.getDataType())
                    +" "+(columnName) + ";\n";
        }
        template = template.replace("${entityProperties}",properties);
        template = template.replace("${tableName}",columns.get(0).getTableName());
        fileCreaterWork.createFile(path,template);
    }
    private void buildVoEntity(String template,List<Columns> columns,String path){
        String properties = "";
        for (Columns column : columns) {
            String columnName = CodeUtils.toHumpName(column.getColumnName());
            if (Arrays.stream(buildTemplate.getVoIgnores()).noneMatch(e->{
                return e.equals(columnName);
            })) {
                properties = properties + ("\t@ApiModelProperty(value = \""+column.getColumnComment()+"\")\n");
                properties = properties + "\tprivate " +
                        //替换数据类型
                        replaceTemplate.replaceType(column.getDataType())
                        + " " + (columnName) + ";\n";
            }
        }
        template = template.replace("${entityProperties}",properties);
        template = template.replace("${tableName}",columns.get(0).getTableName());
        fileCreaterWork.createFile(path,template);
    }

    //构建 dao 文件
    private void buildDao(String template,String path){
        fileCreaterWork.createFile(path,template);
    }
    //构建 service 文件
    private void buildService(String template,String path){
        fileCreaterWork.createFile(path,template);
    }
    //构建 serviceImpl 文件
    private void buildServiceImpl(String template,String path){
        fileCreaterWork.createFile(path,template);
    }
    //构建 controller 文件
    private void buildController(String template,String path){
        fileCreaterWork.createFile(path,template);
    }

    private void buildUtil(String template,String path){
        fileCreaterWork.createFile(template,path);
    }


    private void buildPom(String template,String path,BuildTemplate buildTemplate){
        template = template.replaceAll("\\$\\{packages}",buildTemplate.getPackages());
        template = template.replaceAll("\\$\\{projectName}",buildTemplate.getProjectName());
        fileCreaterWork.createFile(path,template);
    }

    private void buildTest(String template,String path,String packages){
        template = template.replace("${testPackages}","package "+packages+";");
        fileCreaterWork.createFile(path,template);
    }

    private void buildXml(String template,String path,String daoPackagePath){
        template = template.replaceAll("\\$\\{daoPackagePath}",daoPackagePath);
        fileCreaterWork.createFile(path,template);
    }

    private void buildMain(String template,String path,String mainPackage,String mainName){
        template = template.replace("${mainPackage}",mainPackage);
        template = template.replaceAll("\\$\\{MainName}",mainName);
        fileCreaterWork.createFile(path,template);
    }

    private void buildConfig(String template,String path,String swaggerPackage){
        template = template.replace("${swaggerPackage}",swaggerPackage);
        fileCreaterWork.createFile(path,template);
    }

    private void buildProperties(String template,String path,BuildTemplate buildTemplate){
        String mysqlConn = "spring.datasource.url="+buildTemplate.getUrl()+"\n";
        mysqlConn += "spring.datasource.driver-class-name="+buildTemplate.getDriver()+"\n";
        mysqlConn += "spring.datasource.username="+buildTemplate.getUsername()+"\n";
        mysqlConn += "spring.datasource.password="+buildTemplate.getPassword();
        template = template.replace("${mysqlConnection}",mysqlConn);
        fileCreaterWork.createFile(path,template);
    }


    private String getJavaPath(BuildTemplate buildTemplate){
        String result = buildTemplate.getOutputPath() + "/" + buildTemplate.getProjectName() + "/src/main/java";
        String[] split = buildTemplate.getPackages().split("\\.");
        for (String s : split) {
            result = result + "/" + s;
        }
        return result;
    }

    private String getTestPath(BuildTemplate buildTemplate){
        String result = buildTemplate.getOutputPath() + "/" + buildTemplate.getProjectName() + "/src/test/java";
        String[] split = buildTemplate.getPackages().split("\\.");
        for (String s : split) {
            result = result + "/" + s;
        }
        return result;
    }

    //读取模板信息
    private void readTemplate(){
        System.out.println("开始读取模板，模板文件在 resource/template 文件夹下");
        entityTemplate = fileCreaterWork.readFile("entity.temp");
        daoTemplate = fileCreaterWork.readFile("dao.temp");
        serviceTemplate = fileCreaterWork.readFile("service.temp");
        serviceImplTemplate = fileCreaterWork.readFile("serviceImpl.temp");
        controllerTemplate = fileCreaterWork.readFile("controller.temp");
        propertiesTemplate = fileCreaterWork.readFile("properties.temp");
        pomTemplate = fileCreaterWork.readFile("pom.temp");
        testTemplate = fileCreaterWork.readFile("test.temp");
        xmlTemplate = fileCreaterWork.readFile("xml.temp");
        mainTemplate = fileCreaterWork.readFile("main.temp");
        configTemplate = fileCreaterWork.readFile("swagger-config.temp");
        //读取工具类
        resultCodeTemplate = fileCreaterWork.readFile("resultCode.temp");
        rObjectTemplate = fileCreaterWork.readFile("rObject.temp");
        rStaticTemplate = fileCreaterWork.readFile("rStatic.temp");
        //读取vo模板
        voTemplate = fileCreaterWork.readFile("vo.temp");
        replaceTemplate.readReplaceTemplate();
        System.out.println("读取模板文件完毕");
    }


    public static void main(String[] args) {
        IdType[] values = IdType.values();
        for (IdType value : values) {
            System.out.println(value);
        }
    }
}
