package config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static executionEngine.DriverScript.OR;

public class ActionKeywords {

	public static WebDriver driver;

	public static void openBrowser(String object) {
		driver = new ChromeDriver();
	}

	public static void navigate(String object) {
		driver.get(Constants.url);
	}

	public static void click(String object) {
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}

	public static void input_Email(String object) {
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.email);
	}


	public static void input_Password(String object) {
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.password);
	}

	public static void wait_For() throws InterruptedException {
		Thread.sleep(3000);
	}

	public static void click_SignOut() throws InterruptedException {
		WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')][1]"));

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Actions action = new Actions(driver);
		action.moveToElement(element1);
		Thread.sleep(3000);
		action.build().perform();

		driver.findElement(By.xpath("//span[contains(text(),'Sign Out')]")).click();
	}

	public static void closeBrowser() {
		driver.quit();
	}

}
