package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;
import executionEngine.DriverScript;

public class ExcelUtils {

	public static FileInputStream fist;
	public static XSSFWorkbook book;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static void setExcelFile(String filePath) throws IOException {
		try {
			FileInputStream fist = new FileInputStream(filePath);
			book = new XSSFWorkbook(fist);
		} catch (Exception e) {

			Log.error("Class Utils|Method setExcelFile|Exception desc :" + e.getMessage());
			DriverScript.bResult = false;
		}

	}

	public static String getData(int rowNum, int colNum, String sheetName) {
		String data = null;
		try {
			sheet = book.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			
			if ((cell) != null) 
			{
				data = cell.getStringCellValue();
			} 
		
			return data;
			
		} catch (Exception e) {
			Log.error("Class Utils|Method getData|Exception desc :" + e.getMessage());
			DriverScript.bResult = false;
			return "";
		}
	}

	public static int getRowCount(String sheetName) {
		int number = 0;
		try {
			sheet = book.getSheet(sheetName);
			number = sheet.getLastRowNum() + 1;
			// number=sheet.getLastRowNum();
		} catch (Exception e) {

			Log.error("Class Utils|Method getRowCount|Exception desc :" + e.getMessage());
			DriverScript.bResult = false;
		}

		return number;
	}

	public static int getRowContains(String testCaseName, int colNo, String sheetName) {
		int rowNum = 0;
		try {
			sheet = book.getSheet(sheetName);
			int rowCount = getRowCount(sheetName);

			for (rowNum = 0; rowNum < rowCount; rowNum++) {
				String testCaseToBeExecuted = getData(rowNum, colNo, sheetName);
				if (testCaseToBeExecuted.equalsIgnoreCase(testCaseName)) {
					break;
				}
			}
		} catch (Exception e) {
			Log.error("Class Utils|Method getRowContains|Exception desc :" + e.getMessage());
			DriverScript.bResult = false;
		}

		return rowNum;

	}

	public static int getTestStepsCount(String sheetName, String testCaseId, int testCaseStart) {

		try {
			int rowCount = getRowCount(sheetName);
			for (int i = testCaseStart; i < rowCount ; i++) {
				String testCaseIdToBeRun = getData(i, Constants.col_TestCaseID, sheetName);
				if (!testCaseId.equalsIgnoreCase(testCaseIdToBeRun)) {
					int number = i;
					return number;
				}
			}
			sheet = book.getSheet(sheetName);
			int number = sheet.getLastRowNum() + 1;
			return number;

		} catch (Exception e) {
			Log.error("Class Utils|Method getTestStepsCount|Exception desc :" + e.getMessage());
		}
		return 0;
	}

	public static void setData(String result, int rowNum, int colNum, String sheetName) {

		try {
			sheet = book.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(result);
			} else {
				cell.setCellValue(result);
			}
			
			FileOutputStream fos = new FileOutputStream(Constants.path_File);
			book.write(fos);
			fos.flush();
			fos.close();
			

		} catch (Exception e) {
			Log.error("Class Utils|Method setData|Exception desc :" + e.toString());
			DriverScript.bResult = false;
		}
	}

}
