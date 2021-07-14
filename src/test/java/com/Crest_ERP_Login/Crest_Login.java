package com.Crest_ERP_Login;

import org.apache.log4j.Logger;

import com.Utils.Base;
import com.Utils.WebdriverWait;

public class Crest_Login extends Base {

	public static Logger log = Logger.getLogger(Crest_Login.class);

	public static void Login() {

		log.info("Logging in with valid credentials");

		// WebdriverWait.findElement("id", "details-button").click();
		// WebdriverWait.findElement("id", "proceed-link").click();

		WebdriverWait.findElement("id", "login-username").sendKeys(prop.getProperty("username"));
		WebdriverWait.findElement("id", "login-password").sendKeys(prop.getProperty("password"));
		WebdriverWait.findElement("xpath", "//*[@id='loginform']/div[3]/div/input").click();

	}

	public static void ML_login() {

		// WebdriverWait.findElement("id", "details-button").click();
		// WebdriverWait.findElement("id", "proceed-link").click();

		WebdriverWait.findElement("id", "login-username").sendKeys(prop.getProperty("MLusername"));
		WebdriverWait.findElement("id", "login-password").sendKeys(prop.getProperty("MLpassword"));
		WebdriverWait.findElement("xpath", "//*[@id='loginform']/div[3]/div/input").click();

	}

	public static void PurchaseExc_login() {

		WebdriverWait.findElement("xpath", "//*[@id='masthead']/ul/li[3]/ul/li[3]/img").click();
		WebdriverWait.findElement("link", "Logout").click();

		WebdriverWait.findElement("id", "login-username").sendKeys(prop.getProperty("username1"));
		WebdriverWait.findElement("id", "login-password").sendKeys(prop.getProperty("password1"));
		WebdriverWait.findElement("xpath", "//*[@id='loginform']/div[3]/div/input").click();

	}

	public static void PurchaseManager_login() {

		// WebdriverWait.findElement("xpath",
		// "//*[@id='masthead']/ul/li[3]/ul/li[2]/img").click();
		WebdriverWait.findElement("id", "profileImage").click();
		WebdriverWait.findElement("link", "Logout").click();

		WebdriverWait.findElement("id", "login-username").sendKeys(prop.getProperty("username2"));
		WebdriverWait.findElement("id", "login-password").sendKeys(prop.getProperty("password2"));
		WebdriverWait.findElement("xpath", "//*[@id='loginform']/div[3]/div/input").click();
	}

	public static void PurchaseVP_login() {

		// WebdriverWait.findElement("xpath",
		// "//*[@id='masthead']/ul/li[3]/ul/li[2]/img").click();
		WebdriverWait.findElement("id", "profileImage").click();
		WebdriverWait.findElement("link", "Logout").click();

		WebdriverWait.findElement("id", "login-username").sendKeys(prop.getProperty("username3"));
		WebdriverWait.findElement("id", "login-password").sendKeys(prop.getProperty("password3"));
		WebdriverWait.findElement("xpath", "//*[@id='loginform']/div[3]/div/input").click();
}

	public static void PurchaseCEO_login() {

		WebdriverWait.findElement("id", "profileImage").click();
		WebdriverWait.findElement("link", "Logout").click();

		WebdriverWait.findElement("id", "login-username").sendKeys(prop.getProperty("username4"));
		WebdriverWait.findElement("id", "login-password").sendKeys(prop.getProperty("password4"));
		WebdriverWait.findElement("xpath", "//*[@id='loginform']/div[3]/div/input").click();

	}
}