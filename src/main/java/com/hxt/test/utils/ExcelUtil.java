package com.hxt.test.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.thymeleaf.util.StringUtils;

import java.util.List;

public class ExcelUtil {
    /**
     * 创建SXSSFWorkbook
     *
     * @param headers
     * @param sheetName
     * @return
     */
    public static SXSSFWorkbook createSingleSheetSXSSFWorkbook(List<String> headers, String sheetName) {
        XSSFWorkbook xwb = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook(xwb);
        XSSFCellStyle cellStyle = xwb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        Sheet xssfSheet = workbook.createSheet(StringUtils.isEmpty(sheetName) ? "data records" : sheetName);
        Row row = xssfSheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            xssfSheet.setColumnWidth(i, 20 * 256);
            Cell cell = row.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(cellStyle);
        }
        return workbook;
    }
}
