package utilities;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public static String getUsername() {
        String path = "testData/LoginData.xlsx";
        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            String username = sheet.getRow(1).getCell(0).getStringCellValue();
            workbook.close();  
            return username;
        } catch (Exception e) {
            return "";
        }
    }
    public static String getPassword() {

        String path = "testData/LoginData.xlsx";
        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            String password = sheet.getRow(1).getCell(1).getStringCellValue();
            workbook.close();
            return password;
        } catch (Exception e) {
            return "";
        }
    }
}