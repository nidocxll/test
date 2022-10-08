package com.hxt.test.controller;

import com.hxt.test.entity.User;
import com.hxt.test.service.UserService;
import com.hxt.test.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/download")
public class FileDownload {

    @Autowired
    private UserService userService;
    @GetMapping("/excel")
    public String fileDownload(HttpServletResponse response) throws IOException {

        List<User> list = userService.list();
        //设置表头
        List<String> headers = Arrays.asList("序号","id", "姓名", "密码");
        SXSSFWorkbook workbook = ExcelUtil.createSingleSheetSXSSFWorkbook(headers, "用户信息导出");

        SXSSFSheet sheet = workbook.getSheetAt(0);

        CellStyle cellStyle = workbook.createCellStyle();

        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //创建单元格

        for (int i = 1; i < list.size(); i++) {
            SXSSFRow row = sheet.createRow(i);
            for (int j = 0; j < headers.size()+1; j++) {
                row.createCell(j).setCellStyle(cellStyle);
            }
            User user = list.get(i - 1);
            row.getCell(0).setCellValue(i);
            row.getCell(1).setCellValue(user.getId());
            row.getCell(2).setCellValue(user.getName());
            row.getCell(3).setCellValue(user.getPassword());
        }
        response.setHeader("Content-Disposition", "attachment;filename=hxt.xlsx");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        } finally {
            workbook.close();
            if (outputStream != null){
                outputStream.close();
            }
        }
        return "";
    }

    @GetMapping("/excel1")
    public String fileDownload1(HttpServletResponse response) throws IOException {

        List<User> list = userService.list();

        XSSFWorkbook xwb = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook(xwb);
        XSSFCellStyle cellStyle = xwb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        Sheet xssfSheet = workbook.createSheet("用户信息导出");
        Row row1 = xssfSheet.createRow(0);
        List<String> headers = Arrays.asList("序号", "");
        for (int i = 0; i < headers.size(); i++) {
            xssfSheet.setColumnWidth(i, 20 * 256);
            Cell cell = row1.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(cellStyle);
        }
        xssfSheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));  //序号
        xssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));  //用户信息
        Row row2 = xssfSheet.createRow(1);
        List<String> headers1 = Arrays.asList("id","姓名","密码");
        for (int i = 1; i < 1 + headers1.size(); i++) {
            xssfSheet.setColumnWidth(i, 20 * 256);
            Cell cell = row2.createCell(i);
            cell.setCellValue(headers1.get(i - 1));
            cell.setCellStyle(cellStyle);
        }
        Sheet sheet = workbook.getSheetAt(0);
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);

        //创建单元格

        for (int i = 2; i < list.size()+2; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < headers.size(); j++) {
                row.createCell(j).setCellStyle(cellStyle);
            }
            User user = list.get(i - 2);
            row.getCell(0).setCellValue(i-1);
            row.getCell(1).setCellValue(user.getId());
            row.getCell(2).setCellValue(user.getName());
            row.getCell(3).setCellValue(user.getPassword());
        }

        response.setHeader("Content-Disposition", "attachment;filename=hxt.xlsx");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        } finally {
            workbook.close();
            if (outputStream != null){
                outputStream.close();
            }
        }
        return "";
    }
}
