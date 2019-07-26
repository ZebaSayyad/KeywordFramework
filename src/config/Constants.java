package config;

public class Constants {

	// system variables
	public static final String url = "https://www.amazon.in/";
	public static final String path_File = "D:\\FrameWork\\KeywordDrivenAmazon\\src\\dataEngine\\DataEngine11.xlsx";
	public static final String fileName = "DataEngine11.xlsx";
	public static final String path_OR = "D:\\FrameWork\\KeywordDrivenAmazon\\src\\config\\OR.txt";

	// name of excel sheetcol
	public static final int col_TestCaseID = 0;
	public static final int col_TestScenario = 1;
	public static final int col_PageObject = 4;
	public static final int col_ActionKeyword = 5;
	public static final int col_Runmode = 2;

	// result col
	public static final int col_TestCasesResult = 3;
	public static final int col_TestStepsResult = 7;

	public static final int col_DataSet = 6;

	// result
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";

	// excel sheetName
	public static final String sheetName1_TestSteps = "Test Steps";
	public static final String sheetName2_TestCases = "Test Cases";

	// test data
	public static final String email = "zebatanveer.2013@gmail.com";
	public static final String password = "zeba9823";
}