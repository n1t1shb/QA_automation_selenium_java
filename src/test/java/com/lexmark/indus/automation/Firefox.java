/**
 * 
 */
package com.lexmark.indus.automation;

import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Concrete implementation of {@link Browser} for Firefox
 * 
 * @author nitishb1989
 *
 */
public class Firefox extends Browser {

	public Firefox() {
		this.driver = new FirefoxDriver();
	}

}
