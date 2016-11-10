/**
 * 
 */
package com.lexmark.indus.automation;

import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Concrete implementation of {@link Browser} for Chrome
 * 
 * @author nitishb1989
 *
 */
public class IE extends Browser {

	public IE() {
		System.setProperty("webdriver.ie.driver", "lib/IEDriverServer.exe");
		this.driver = new InternetExplorerDriver();
	}

}
