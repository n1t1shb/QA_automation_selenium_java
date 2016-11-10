/**
 * 
 */
package com.lexmark.indus.automation;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Concrete implementation of {@link Browser} for Chrome
 * 
 * @author nitishb1989
 *
 */
public class Chrome extends Browser {

	public Chrome() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		this.driver = new ChromeDriver();
	}

}
