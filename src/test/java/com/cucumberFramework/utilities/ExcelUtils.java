package com.cucumberFramework.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtils {

    private static Workbook workbook;
    private static Row currentRow;
    private static Cell currentCell;

    public static FileInputStream file(String filePath) throws IOException, FileNotFoundException {
        File file = new File(filePath);
        return new FileInputStream(file);

    }

    public static String cellValue(String row, String column,String path) throws IOException {
        FileInputStream file = file(path);
        workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        int rowIndex = Integer.parseInt(row);
        Row row1 = sheet.getRow(rowIndex);
        int columnIndex = Integer.parseInt(column);
        Cell cell = row1.getCell(columnIndex);
        String value = cell.toString();
        currentRow = row1;
        currentCell = cell;
        return value;
    }
    public static FileOutputStream fileOut(String filePath) throws IOException {
        File file = new File(filePath);
        return new FileOutputStream(file);
    }
    public static void setCellValue(String value, String path) throws IOException {
        Cell cell = currentRow.createCell(currentCell.getColumnIndex()+1);
        cell.setCellValue(value);
        FileOutputStream fileOut = fileOut(path);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

    }
}
