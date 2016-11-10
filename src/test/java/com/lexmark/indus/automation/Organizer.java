/**
 * 
 */
package com.lexmark.indus.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Implementation of organizer website
 * 
 * @author nitishb1989
 *
 */
public class Organizer extends Website {

	/**
	 * @param url
	 */
	public Organizer(String url) {
		super(url);
	}

	/**
	 * @throws InterruptedException
	 * @see com.lexmark.automation.Website#login(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void login(String user, String password) throws InterruptedException {
		WebDriverWait waitForLoginScreen = new WebDriverWait(driver, 60);
		waitForLoginScreen.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtLogin")));
		// Key in user name and password
		driver.findElement(By.id("txtLogin")).sendKeys(user);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
		By icon = By.id("checkAll");
		WebDriverWait waitForHomeScreen = new WebDriverWait(driver, 30);
		waitForHomeScreen.until(ExpectedConditions.visibilityOfElementLocated(icon));
		Thread.sleep(2000);
		// Login successful, create a session
		createSession(user);
	}

	/**
	 * @throws InterruptedException
	 * @see com.lexmark.automation.Website#logout()
	 */
	@Override
	public void logout() throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait waitForIcon = new WebDriverWait(driver, 60);
		waitForIcon.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("checkAll")));
		driver.findElement(By.xpath("//button[@aria-label='User Menu']")).click();

		By logout = By.xpath("//span[@class='icon-log_off']");
		driver.findElement(logout).click();

		WebDriverWait waitForLoginScreen = new WebDriverWait(driver, 60);
		waitForLoginScreen.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtLogin")));
		// Logout successful, destroy the session
		destroySession();
	}

}
