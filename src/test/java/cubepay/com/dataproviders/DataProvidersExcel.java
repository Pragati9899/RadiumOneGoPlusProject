package cubepay.com.dataproviders;

import cubepay.com.constants.FrameworkConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataProvidersExcel {
    @DataProvider
    public static Object[] getExcelData() throws IOException {
        FileInputStream fs = new FileInputStream(FrameworkConstants.getReadexcelfilePath());
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rownum = sheet.getLastRowNum();
        int columnnum = sheet.getRow(0).getLastCellNum();

        Object[] data = new Object[rownum];
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < columnnum; j++) {
                data[i - 1] = sheet.getRow(i).getCell(j).getRawValue();
            }
        }
        return data;
    }

    @DataProvider
    public static Object[] getExcelData1() throws IOException {
        FileInputStream fs = new FileInputStream(FrameworkConstants.getReadexcelfilePath());
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rownum = sheet.getLastRowNum();
        int columnnum = sheet.getRow(0).getLastCellNum();

        Object[] data = new Object[rownum];
        Map<String, String> map;
        for (int i = 1; i <= rownum; i++) {
            map = new HashMap<>();
            for (int j = 0; j < columnnum; j++) {
                String key = sheet.getRow(0).getCell(j).getRawValue();
                String value = sheet.getRow(i).getCell(j).getRawValue();
                map.put(key, value);
                data[i - 1] = map.get(key);
            }
        }
        return data;
    }


}
