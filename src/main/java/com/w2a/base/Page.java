package com.w2a.base;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.w2a.pages.actions.TopNavigation;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;

public class Page {
	
	/*
	 * WebDriver
	 * 
	 * ExcelReader
	 * Logs
	 * WebDriverWait
	 * ExtentReports
	 * 
	 * 
	 * 
	 */

	public static WebDriver driver;
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static TopNavigation topNav;
	public static JavascriptExecutor javascriptexecutor;
	
	public static void initConfiguration() {

		if (Constants.browser.equals("firefox")) {

			System.setProperty("webdriver.gecko.driver", "C:\\Softwares\\jars\\selenium\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.debug("Launching firefox");
		} else if (Constants.browser.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\jars\\selenium\\chromedriver.exe");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			driver = new ChromeDriver(options);
			log.debug("Chrome Launched !!!");
		}

		driver.get(Constants.testsiteurl);
		driver.manage().window().maximize();
		/*	driver.manage().timeouts().implicitlyWait(Constants.implicitwait, TimeUnit.SECONDS);
		wait = new WebDriverWait()*/
		topNav = new TopNavigation(driver);
		javascriptexecutor = (JavascriptExecutor)driver;
	}
	
	public static void click(WebElement element) {
		element.click();
		log.debug("Clcking on an element :"+element);
		test.log(Status.INFO, "Clicking on : "+element);
	}

	public static void typeusingjavascript(WebElement element, String value) {
		javascriptexecutor.executeScript("arguments[0].value='"+value+"';", element);
		log.debug("Typing in : " + element + " entered value as :" + value);
		test.log(Status.INFO, "Typing in : " + element + " entered value as :" + value);

	}
	
	public static void type(WebElement element, String value) {
		element.sendKeys(value);
		log.debug("Typing in : " + element + " entered value as :" + value);
		test.log(Status.INFO, "Typing in : " + element + " entered value as :" + value);

	}
	
	public static void quitBrowser() {

		driver.quit();

	}

}
