package com.hxt.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hxt.test.entity.User;
import com.hxt.test.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upload")
public class FileUpload {

    @Autowired
    private UserService userService;

    @PostMapping("/excel")
    public String fileUpload(@RequestParam MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName == null || "".equals(fileName)) {
            return "请选择文件";
        }
        Workbook workbook = null;
        InputStream inputStream = null;
        String postFix = fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            inputStream = file.getInputStream();
            if ("xls".equals(postFix)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if ("xlsx".equals(postFix)) {
                workbook = new XSSFWorkbook(inputStream);
            } else {
                return "文件类型错误";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        List<User> list = new ArrayList<>();
        List<User> existList = new ArrayList<>();
        List<User> newList = new ArrayList<>();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                Row row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                try {
                    User user = new User();
                    user.setId((int) row.getCell(0).getNumericCellValue());
                    user.setName(row.getCell(1).getStringCellValue());
                    user.setPassword(row.getCell(2).getStringCellValue());
                    list.add(user);
                } catch (Exception e) {
                    e.printStackTrace();
                    return sheet.getSheetName() + "第" + (j + 1) + "行：" + "(格式错误)";
                }
            }
        }
        if (workbook != null) {
            workbook.close();
        }
        if (list.isEmpty()) {
            return "没有数据";
        }
        //查询重复数据
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct id");
        List<User> idDataList = userService.list(queryWrapper);

        Map<Integer, User> map = idDataList.stream().collect(Collectors.toMap(User::getId, g -> g));
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //已有
            if (map.containsKey(list.get(i).getId())){
                existList.add(list.get(i));
                numberList.add(i+2);
            }else {
             //新增
                list.get(i);
                newList.add(list.get(i));
            }
        }

        if (!existList.isEmpty()){
            return "有重复数据";
        }
        userService.saveBatch(newList);
        return "success";
    }
}
