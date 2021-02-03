package com.woqiyounai.build;

import com.baomidou.mybatisplus.annotation.IdType;
import com.woqiyounai.entity.Columns;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;


//项目模板类，根据该模板生成项目
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BuildTemplate<tables> {
    private String outputPath;//项目输出路径

    private String projectName;//项目名
    private String packages;//项目基本包名

    private String entityPName = "entity";//实体类包名
    private String daoPName = "dao";//dao层包名
    private String servicePName = "service";//service包名
    private String serviceImplPName = "impl";//service实现类包名
    private String controllerPName = "controller";//controller包名

    private String entityPrefix = "";//实体类前缀
    private String entitySuffix = "Entity";//实体类后缀
    private String daoPrefix = "";//dao前缀
    private String daoSuffix = "Dao";//dao后缀
    private String servicePrefix = "";//service前缀
    private String serviceSuffix = "Service";//service后缀
    private String serviceImplSuffix = "Impl";//service实现类后缀
    private String controllerPrefix = "";//controller前缀
    private String controllerSuffix = "Controller";//controller后缀
    private Map<String, List<Columns>> beanMap = null;//实体类 Map

    //数据库信息
    private String username;
    private String password;
    private String url;
    private String driver;

    //数据库名
    private String schemaName;
    private String[] tables;
    private boolean useAll = true;

    //主键生成策略
    private IdType idType;

    //vo对象
    private String[] voIgnores;

    public BuildTemplate outputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    public BuildTemplate projectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public BuildTemplate packages(String packages) {
        this.packages = packages;
        return this;
    }

    public BuildTemplate entityPName(String entityPName) {
        this.entityPName = entityPName;
        return this;
    }

    public BuildTemplate daoPName(String daoPName) {
        this.daoPName = daoPName;
        return this;
    }

    public BuildTemplate servicePName(String servicePName) {
        this.servicePName = servicePName;
        return this;
    }

    public BuildTemplate serviceImplPName(String serviceImplPName) {
        this.serviceImplPName = serviceImplPName;
        return this;
    }

    public BuildTemplate controllerPName(String controllerPName) {
        this.controllerPName = controllerPName;
        return this;
    }

    public BuildTemplate entityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
        return this;
    }

    public BuildTemplate entitySuffix(String entitySuffix) {
        this.entitySuffix = entitySuffix;
        return this;
    }

    public BuildTemplate daoPrefix(String daoPrefix) {
        this.daoPrefix = daoPrefix;
        return this;
    }

    public BuildTemplate daoSuffix(String daoSuffix) {
        this.daoSuffix = daoSuffix;
        return this;
    }

    public BuildTemplate servicePrefix(String servicePrefix) {
        this.servicePrefix = servicePrefix;
        return this;
    }

    public BuildTemplate serviceSuffix(String serviceSuffix) {
        this.serviceSuffix = serviceSuffix;
        return this;
    }

    public BuildTemplate serviceImplSuffix(String serviceImplSuffix) {
        this.serviceImplSuffix = serviceImplSuffix;
        return this;
    }

    public BuildTemplate controllerPrefix(String controllerPrefix) {
        this.controllerPrefix = controllerPrefix;
        return this;
    }

    public BuildTemplate controllerSuffix(String controllerSuffix) {
        this.controllerSuffix = controllerSuffix;
        return this;
    }

    public BuildTemplate username(String username) {
        this.username = username;
        return this;
    }

    public BuildTemplate password(String password) {
        this.password = password;
        return this;
    }

    public BuildTemplate url(String url) {
        this.url = url;
        return this;
    }

    public BuildTemplate driver(String driver) {
        this.driver = driver;
        return this;
    }

    public BuildTemplate schemaName(String schemaName) {
        this.schemaName = schemaName;
        return this;
    }

    public BuildTemplate tables(String... tables){
        this.tables = tables.clone();
        this.useAll = false;
        return this;
    }

    public BuildTemplate voIgnores(String... voIgnores){
        this.voIgnores = voIgnores.clone();
        return this;
    }

    public BuildTemplate idType(IdType idType){
        this.idType = idType;
        return this;
    }

    public boolean isUseAll(){
        return this.useAll;
    }
}
