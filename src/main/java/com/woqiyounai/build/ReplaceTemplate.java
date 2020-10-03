package com.woqiyounai.build;

import com.woqiyounai.entity.TypeList;
import com.woqiyounai.output.FileCreaterWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReplaceTemplate {

    @Autowired
    private FileCreaterWork fileCreaterWork;
    private Map<String, TypeList> replaceList = null;

    public void readReplaceTemplate(){
        System.out.println("读取 json 文件");
        replaceList = fileCreaterWork.readReplaceList();
    }

    public String replaceType(String type){
        TypeList typeList = replaceList.get(type.toUpperCase());
        if (null == typeList){
            return "String";
        }
        return typeList.getType();
    }
}
