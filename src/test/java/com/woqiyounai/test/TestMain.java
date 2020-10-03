package com.woqiyounai.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woqiyounai.dao.ColumnsMapper;
import com.woqiyounai.entity.Columns;
import com.woqiyounai.output.FileCreater;
import com.woqiyounai.service.ArrangeDBService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestMain {

    @Autowired
    ArrangeDBService arrangeDBService;
    @Autowired
    ColumnsMapper columnsMapper;
    @Autowired
    FileCreater fileCreater;

    @Test
    void contextLoads() {
        Map<String, List<Columns>> allTable = arrangeDBService.selectTable("app","tb_user_account","tb_proxy");
        System.out.println(allTable.size());
    }

    @Test
    void columns() {
        QueryWrapper<Columns> columnsWrapper = new QueryWrapper<>();
        //tb_user_account   tb_proxy
        columnsWrapper.eq("TABLE_SCHEMA","app");
        columnsWrapper.in("TABLE_NAME", "tb_user_account","tb_proxy");
        List<Columns> columns1 = columnsMapper.selectList(columnsWrapper);
//        System.out.println(columns1);
//        columnsWrapper.or().eq("TABLE_NAME","tb_user_account");
//        List<Columns> columns = columnsMapper.selectList(columnsWrapper);
        System.out.println(columns1.size());
    }

    @Test
    public void fileCreate(){
        ClassPathResource classPathResource = new ClassPathResource("template/entity.temp");
        File file = new File("/home/lxl/IdeaProjects/code-builder/src/main/resources/output/Allin.java");
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(classPathResource.getFile());
            fileWriter = new FileWriter(file);
            String me = FileCopyUtils.copyToString(fileReader).replace("${name}", "ME");
            FileCopyUtils.copy(me,fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=fileReader){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testString(){
        String testStr = "asdf_hjd_kjhj_jkdf";
        String[] ss = testStr.split("_");
        String result = "";
        for (String s : ss) {
            result+=(s.substring(0, 1).toUpperCase() + s.substring(1));
        }
        System.out.println(result);
    }

    @Test
    public void folder() {
        System.out.println(
                fileCreater.readReplaceList()
        );
    }
}
