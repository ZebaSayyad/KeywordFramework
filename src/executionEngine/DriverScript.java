package executionEngine;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;

public class DriverScript {

	public static Properties OR;

	public static ActionKeywords actionKeywords;

	public static String sActionKeyword;

	public static String pageObject;

	public static Method method[];

	public DriverScript() {
		actionKeywords = new ActionKeywords();

		method = actionKeywords.getClass().getMethods();
	}

	public static void main(String[] args) throws Exception {

		String path = Constants.path_File;

		// set Excel file path
		ExcelUtils.setExcelFile(path, Constants.sheetName);

		// OR file path
		String path_OR = Constants.path_OR;

		FileInputStream fis = new FileInputStream(path_OR);
		OR = new Properties(System.getProperties());
		OR.load(fis);

		for (int row = 1; row <= 10; row++) {
			// get data from excel, colu is fix i.e Action Keyword and row varies so loop is
			// used
			sActionKeyword = ExcelUtils.getData(row, Constants.col_ActionKeyword,Constants.sheetName);
			pageObject=ExcelUtils.getData(row, Constants.col_PageObject,Constants.sheetName);

			execute_Actions();

		}

	}

	// in reflection class there is a method with method[]
	private static void execute_Actions() throws Exception {

		for (int i = 0; i < method.length; i++) {
			// this will compare metho with ActionKeyword value got from the excel
			if (method[i].getName().equals(sActionKeyword)) {
				// this will invoke method after match found
				method[i].invoke(actionKeywords, pageObject);

				break;
			}
		}

	}

}
