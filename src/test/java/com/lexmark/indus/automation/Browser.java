/**
 * 
 */
package com.lexmark.indus.automation;

import org.openqa.selenium.WebDriver;

/**
 * The abstract class to represent browser instance opened by selenium web
 * driver.
 * 
 * @author nitishb1989
 *
 */
public abstract class Browser {

	protected WebDriver driver;

	/**
	 * @return the driver
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Maximize the browser window
	 */
	public void maximize() {
		this.driver.manage().window().maximize();
	}

	/**
	 * Navigate to given website
	 * 
	 * @param website
	 *          An instance of website
	 */
	public void navigate(Website website) {
		website.open(driver);
	}

	/**
	 * Close the browser
	 */
	public void close() {
		driver.close();
	}

}
