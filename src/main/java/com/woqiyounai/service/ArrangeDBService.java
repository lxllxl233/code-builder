package com.woqiyounai.service;

import com.woqiyounai.entity.Columns;

import java.util.List;
import java.util.Map;

public interface ArrangeDBService {

    //查找所有表
    public Map<String, List<Columns>> getAllTable(String databaseName);
    //查找部分表
    public Map<String,List<Columns>> selectTable(String databaseName,String... tables);
}
