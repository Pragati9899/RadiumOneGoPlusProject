package cubepay.com.utilities;

import cubepay.com.constants.FrameworkConstants;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelUtils {

    private ExcelUtils() {
    }


    public static List<Map<String, String>> readExcelData() throws IOException {

        List<Map<String, String>> list;
        try (FileInputStream fs = new FileInputStream(FrameworkConstants.getRunmanagerexcelfilepath());
             XSSFWorkbook workbook = new XSSFWorkbook(fs)) {
            XSSFSheet sheet = workbook.getSheet("RUNMANAGERSHEET");

            int rownum = sheet.getLastRowNum();
            int columnnum = sheet.getRow(0).getLastCellNum();

            Map<String, String> map;
            list = new ArrayList<>();

            for (int i = 1; i <= rownum; i++) {
                map = new HashMap<>();
                for (int j = 0; j < columnnum; j++) {
                    String key = sheet.getRow(0).getCell(j).getRawValue();
                    String value = sheet.getRow(i).getCell(j).getRawValue();
                    map.put(key, value);
                }
                list.add(map);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Not able to found excel file");
        } catch (IOException e) {
            throw new IOException("Not able to read data in  excel file");
        }
        return list;
    }



  /*  public static List<Map<String, String>> readExcelData(){
        List<Map<String, String>> dataList;
        try (FileInputStream fileInputStream = new FileInputStream(FrameworkConstants.getRunmanagerexcelfilepath());
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            XSSFSheet sheet = workbook.getSheet("RUNMANAGERSHEET");
            int rowNum = sheet.getLastRowNum();
            int columnNum = sheet.getRow(0).getLastCellNum();

            dataList = new ArrayList<>();

            for (int i = 1; i <= rowNum; i++) {
                Map<String, String> dataMap = new HashMap<>();
                for (int j = 0; j < columnNum; j++) {
                    String key = sheet.getRow(0).getCell(j).getRawValue();
                    String value = sheet.getRow(i).getCell(j).getRawValue();
                    dataMap.put(key, value);
                }
                dataList.add(dataMap);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Excel file not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel data", e);
        }
        return dataList;
    }*/
}
