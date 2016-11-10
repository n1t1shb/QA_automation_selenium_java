/**
 * 
 */
package com.lexmark.indus.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Implementation of Action Manager website
 * 
 * @author nitishb1989
 *
 */
public class ActionManager extends Website {

	/**
	 * @param url
	 */
	public ActionManager(String url) {
		super(url);
	}

	/**
	 * @throws InterruptedException
	 * @see com.lexmark.automation.Website#login(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void login(String user, String password) throws InterruptedException {

		/*WebDriverWait waitForLandingPage = new WebDriverWait(driver, 60);
		waitForLandingPage.until(ExpectedConditions.visibilityOfElementLocated(By.className("anchor-button")));

		// go to the login page
		driver.findElement(By.className("anchor-button")).click();
		WebDriverWait waitForLoginScreen = new WebDriverWait(driver, 60);
		waitForLoginScreen.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));

		// Key in user name and password

		// Login successful, create a session
		createSession(user);*/

	}

	/**
	 * @throws InterruptedException
	 * @see com.lexmark.automation.Website#logout()
	 */
	@Override
	public void logout() throws InterruptedException {
		/*Thread.sleep(2000);

		WebDriverWait waitForIcon = new WebDriverWait(driver, 60);
		waitForIcon.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='/assets/banners/-logo.png']")));

		WebElement accountDropDown = driver.findElement(By.xpath("//a[contains(text(),'Account')]"));
		Actions actionMouseHover = new Actions(driver);
		actionMouseHover.moveToElement(accountDropDown).click().perform();
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		// Logout successful, destroy the session
		destroySession();*/

	}

}
