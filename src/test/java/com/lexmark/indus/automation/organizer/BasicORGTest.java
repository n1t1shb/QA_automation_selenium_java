/**
 * 
 */
package com.lexmark.indus.automation.organizer;

/*import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;*/
import org.testng.annotations.Test;

import com.lexmark.indus.automation.BaseTestModule;

/**
 * @author nitishb1989
 *
 */
public class BasicORGTest extends BaseTestModule {
	

	@Test
	public void testmethod() throws InterruptedException {
		
		Thread.sleep(2000);
		
	}

	@Test(dependsOnMethods = { "testmethod" })
	public void testmethod2() throws InterruptedException {
		
		
	}

}
