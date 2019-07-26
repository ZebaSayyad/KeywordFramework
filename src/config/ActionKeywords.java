package config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import executionEngine.DriverScript;
import utility.Log;

import static executionEngine.DriverScript.OR;

public class ActionKeywords {

	public static WebDriver driver;

	public static void openBrowser(String object, String data) {
		Log.info("Opening browser");
		try {
			if (data.equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();
				Log.info("Chrome browser started...");
			} else if (data.equalsIgnoreCase("IE")) {
				driver = new InternetExplorerDriver();
				Log.info("IE browser started");
			} else if (data.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
				Log.info("Firefox browser started");
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		} catch (Exception e) {
			Log.info("Not able to open Browser---" + e.getMessage());
			DriverScript.bResult = false;
		}
	}

	public static void navigate(String object, String data) {
		try {
			Log.info("Navigating to url");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(Constants.url);
		} catch (Exception e) {
			Log.info("Not able to navigate---" + e.getMessage());
			DriverScript.bResult = false;
		}
	}

	public static void click(String object, String data) {
		try {
			Log.info("Clicking on element " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		} catch (Exception e) {

			Log.error("Not able to click ---" + e.getMessage());
			DriverScript.bResult = false;
		}
	}

	public static void input(String object, String data) {
		try {
			Log.info("Entering text in Email ");
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		} catch (Exception e) {
			Log.error("Not able to Enter Email---" + e.getMessage());
			DriverScript.bResult = false;
		}
	}

	public static void wait_For(String object, String data) throws InterruptedException {
		try {
			Log.info("wait for 3 seconds");
			Thread.sleep(3000);
		} catch (Exception e) {

			Log.error("Not able to wait---" + e.getMessage());
			DriverScript.bResult = false;

		}
	}
	
	public static void mouseHover(String object, String data) throws InterruptedException {
		try {
			WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
			Actions action = new Actions(driver);
			Log.info("Mouse hovering...");
			action.moveToElement(element).build().perform();
			Log.info("Mouse hovered");
			wait_For("","");
		}catch(Exception e) {
			Log.error("Not able to hover mouse---" + e.getMessage());
			DriverScript.bResult = false;
		}
		
	}

	public static void closeBrowser(String object, String data) {
		try {
			Log.info("Closing the browser");
			driver.quit();
		} catch (Exception e) {
			Log.error("Not able to Close browser---" + e.getMessage());
			DriverScript.bResult = false;
		}
	}

}
