package executionEngine;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;
import utility.Log;

public class DriverScript {

	public static Properties OR;

	public static ActionKeywords actionKeywords;

	public static String sActionKeyword;

	public static String pageObject;

	public static Method method[];

	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String testData;

	public static boolean bResult = true;

	public static void main(String[] args) throws Exception {

		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();

		String path = Constants.path_File;
		// set Excel file path`
		ExcelUtils.setExcelFile(path);

		DOMConfigurator.configure("log4j.xml");

		// OR file path
		String path_OR = Constants.path_OR;

		FileInputStream fis = new FileInputStream(path_OR);
		OR = new Properties(System.getProperties());
		OR.load(fis);

		DriverScript startEngine = new DriverScript();
		startEngine.execute_TestCase();
	}

	private void execute_TestCase() throws Exception {

		int iTotalTestCases = ExcelUtils.getRowCount(Constants.TestCases);

		// This loop will execute number of times equal to Total number of test cases

		for (int iTestcase = 1; iTestcase < iTotalTestCases; iTestcase++) {
			bResult = true;

			sTestCaseID = ExcelUtils.getData(iTestcase, Constants.col_TestCaseID, Constants.TestCases);

			sRunMode = ExcelUtils.getData(iTestcase, Constants.col_Runmode, Constants.TestCases);

			if (sRunMode.equals("Yes")) {

				iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.col_TestCaseID,
						Constants.TestSteps);
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.TestSteps, sTestCaseID, iTestStep);
				Log.startTestCase(sTestCaseID);
				// This loop will execute number of times equal to Total number of test steps
				for (; iTestStep < iTestLastStep; iTestStep++) {
					sActionKeyword = ExcelUtils.getData(iTestStep, Constants.col_ActionKeyword,
							Constants.TestSteps);
					pageObject = ExcelUtils.getData(iTestStep, Constants.col_PageObject,
							Constants.TestSteps);
					testData = ExcelUtils.getData(iTestStep, Constants.col_DataSet, Constants.TestSteps);
					execute_Actions();

					if (bResult == false) {
						ExcelUtils.setData(Constants.KEYWORD_FAIL, iTestcase, Constants.col_TestCasesResult,
								Constants.TestCases);
						Log.endTestCase(sTestCaseID);
						break;
					}

				}
				if (bResult == true) {
					ExcelUtils.setData(Constants.KEYWORD_PASS, iTestcase, Constants.col_TestCasesResult,
							Constants.TestCases);
					Log.endTestCase(sTestCaseID);
				}
			}
		}
	}

	// in reflection class there is a method with method[]
	private static void execute_Actions() throws Exception {

		for (int i = 0; i < method.length; i++) {
			// this will compare metho with ActionKeyword value got from the excel
			if (method[i].getName().equals(sActionKeyword)) {
				// this will invoke method after match found
				method[i].invoke(actionKeywords, pageObject, testData);

				if (bResult == true) {
					ExcelUtils.setData(Constants.KEYWORD_PASS, iTestStep, Constants.col_TestStepsResult,
							Constants.TestSteps);
					break;
				} else {
					ExcelUtils.setData(Constants.KEYWORD_FAIL, iTestStep, Constants.col_TestStepsResult,
							Constants.TestSteps);
					// ActionKeywords.closeBrowser();
					break;
				}

			}
		}

	}

}
