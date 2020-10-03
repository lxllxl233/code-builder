package com.woqiyounai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woqiyounai.dao.ColumnsMapper;
import com.woqiyounai.entity.Columns;
import com.woqiyounai.service.ArrangeDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ArrangeDBServiceImpl implements ArrangeDBService {

    @Autowired
    private ColumnsMapper columnsMapper;

    @Override
    public Map<String, List<Columns>> getAllTable(String databaseName) {
        QueryWrapper<Columns> columnsQueryWrapper = new QueryWrapper<>();
        columnsQueryWrapper.eq("TABLE_SCHEMA",databaseName);
        List<Columns> columns = columnsMapper.selectList(columnsQueryWrapper);
        Map<String, List<Columns>> map = new HashMap<>();

        columns.forEach(e->{
            if ((!map.containsKey(e.getTableName())) || null == map.get(e.getTableName())) {
                List<Columns> list = new ArrayList<>();
                list.add(e);
                map.put(e.getTableName(), list);
            }else {
                List<Columns> columnList = map.get(e.getTableName());
                columnList.add(e);
                map.put(e.getTableName(), columnList);
            }
        });
        return map;
    }

    @Override
    public Map<String, List<Columns>> selectTable(String databaseName,String... tables) {

        QueryWrapper<Columns> columnsQueryWrapper = new QueryWrapper<>();
        columnsQueryWrapper.eq("TABLE_SCHEMA",databaseName);
        columnsQueryWrapper.in("TABLE_NAME",tables);

        List<Columns> columns = columnsMapper.selectList(columnsQueryWrapper);
        Map<String, List<Columns>> map = new HashMap<>();

        columns.forEach(e->{
            if ((!map.containsKey(e.getTableName())) || null == map.get(e.getTableName())) {
                List<Columns> list = new ArrayList<>();
                list.add(e);
                map.put(e.getTableName(), list);
            }else {
                List<Columns> columnList = map.get(e.getTableName());
                columnList.add(e);
                map.put(e.getTableName(), columnList);
            }
        });
        return map;
    }
}
