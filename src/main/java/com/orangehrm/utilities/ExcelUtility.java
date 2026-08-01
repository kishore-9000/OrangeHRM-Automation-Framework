package com.orangehrm.utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Utility for reading and writing test data using Apache POI.
 */
public class ExcelUtility {

    private String filePath;

    public ExcelUtility(String filePath) {
        this.filePath = filePath;
    }

    public int getRowCount(String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            return sheet != null ? sheet.getLastRowNum() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCellCount(String sheetName, int rowNum) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return 0;
            Row row = sheet.getRow(rowNum);
            return row != null ? row.getLastCellNum() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getCellData(String sheetName, int rowNum, int colNum) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return "";
            Row row = sheet.getRow(rowNum);
            if (row == null) return "";
            Cell cell = row.getCell(colNum);
            if (cell == null) return "";

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public Object[][] getTestData(String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return new Object[0][0];

            int totalRows = sheet.getLastRowNum();
            int totalCols = sheet.getRow(0).getLastCellNum();
            DataFormatter formatter = new DataFormatter();

            Object[][] data = new Object[totalRows][totalCols];
            for (int i = 1; i <= totalRows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < totalCols; j++) {
                    Cell cell = (row == null) ? null : row.getCell(j);
                    data[i - 1][j] = (cell == null) ? "" : formatter.formatCellValue(cell);
                }
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) {
        try {
            File file = new File(filePath);
            Workbook workbook;
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = WorkbookFactory.create(fis);
                fis.close();
            } else {
                workbook = WorkbookFactory.create(true); // create new xlsx
            }

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            Cell cell = row.createCell(colNum);
            cell.setCellValue(data);

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
