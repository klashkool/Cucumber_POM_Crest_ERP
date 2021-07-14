package com.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger log = Logger.getLogger(Base.class);

	public Base() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
							System.getProperty("user.dir") + "\\src\\test\\resources\\Config\\config.properties");

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		driver = null;
		System.out.println("Initializing Driver");
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			log.info("Launching the Browser");
			System.setProperty("webdriver.chrome.driver",
							"D:\\Projects\\Cucumber_POM_CREST_ERP\\ChromeDriver\\chromedriver.exe");
			// ChromeOptions options = new ChromeOptions();
			// options.addArguments("headless");
			// driver = new ChromeDriver(options);
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Projects\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='90%'");

		log.info("Lauching the URL");
		driver.get(prop.getProperty("url"));
		System.out.println(driver.getCurrentUrl());

	}

}
