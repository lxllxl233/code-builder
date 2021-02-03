package com.woqiyounai.output;
import com.woqiyounai.entity.TypeList;

import java.util.Map;

public interface FileCreaterWork {
    //读取文件
    public String readFile(String fileName);
    //创建文件夹
    public void createFolder(String path,String folderName);
    //根据创建文件并写入传入的内容
    public void createFile(String path,String fileTemplate);

    //读取替换文件列表
    public Map<String, TypeList> readReplaceList();
}
