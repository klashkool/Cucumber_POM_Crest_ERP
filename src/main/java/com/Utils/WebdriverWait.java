package com.Utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WebdriverWait extends Base {


	public static WebElement findElement(String locator, String path) {
		WebDriverWait wait = new WebDriverWait(Base.driver, 50);
		switch (locator) {
		case "id":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(path)));
				return driver.findElement(By.id(path));

		case "link":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(path)));
			return driver.findElement(By.linkText(path));

		case "xpath":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
			return driver.findElement(By.xpath(path));

		case "name":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(path)));
			return driver.findElement(By.name(path));
		}
		return null;
	}

	public static List<WebElement> findElements(String locator, String path) {

		WebDriverWait wait = new WebDriverWait(Base.driver, 50);
		switch (locator) {
		case "xpath":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
			return driver.findElements(By.xpath(path));

		case "id":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(path)));
			return driver.findElements(By.id(path));

		case "link":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(path)));
			return driver.findElements(By.linkText(path));

		case "name":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(path)));
			return driver.findElements(By.name(path));
		}
		return null;
	}

}
