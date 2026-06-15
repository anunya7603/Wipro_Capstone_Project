package utilities;

import java.io.InputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Utility class for reading Excel data
public class ExcelUtils {

	// Returns cell value from Excel sheet
    public static String getCellData(int row, int col) throws Exception {

        InputStream is = ExcelUtils.class.getClassLoader()
                .getResourceAsStream("TestData/FlightData.xlsx");
        
        Workbook wb = new XSSFWorkbook(is);
        Sheet sheet = wb.getSheet("Sheet1");
        String value = sheet.getRow(row)
                            .getCell(col)
                            .toString();
        wb.close();
        return value;
    }
}
