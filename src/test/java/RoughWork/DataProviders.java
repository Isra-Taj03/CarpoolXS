package RoughWork;

import org.testng.annotations.DataProvider;

import Utilities.ExcelUtils;

public class DataProviders {
	@DataProvider(name = "AddDriverInfo")
    public static Object[][] getAddDriverData() throws Exception {
        String filePath = "./TestData/DriverData.xlsx";  // adjust path as needed
        String sheetName = "sheet1";               // adjust sheet name

        int rowCount = ExcelUtils.getRowCount(filePath, sheetName);
        int colCount = ExcelUtils.getCellCount(filePath, sheetName, 1);

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = ExcelUtils.getCellData(filePath, sheetName, i + 1, j);
            }
        }

        return data;
    }
}
