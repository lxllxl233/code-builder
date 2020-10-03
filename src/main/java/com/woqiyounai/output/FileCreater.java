package com.woqiyounai.output;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.woqiyounai.entity.TypeList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//根据模板创建文件和文件夹
@Component
public class FileCreater implements FileCreaterWork{
    @Override
    public String readFile(String fileName) {
        ClassPathResource classPathResource = new ClassPathResource("template/"+fileName);
        FileReader fileReader = null;
        String result = null;
        try {
            File file = classPathResource.getFile();
            fileReader = new FileReader(file);
            result = FileCopyUtils.copyToString(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void createFolder(String path, String folderName) {
        File file = new File(path+"/"+folderName);
        file.mkdir();
    }

    @Override
    public void createFile(String path,String fileTemplate) {
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            FileCopyUtils.copy(fileTemplate,fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=fileWriter){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Map<String, TypeList> readReplaceList() {
        ClassPathResource classPathResource = new ClassPathResource("config/replaceTable.json");
        FileReader fileReader = null;
        Map<String, TypeList> result = null;
        try {
            File file = classPathResource.getFile();
            fileReader = new FileReader(file);
            String str = FileCopyUtils.copyToString(fileReader);
            result = JSON.parseObject(str,new TypeReference<HashMap<String,TypeList>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
