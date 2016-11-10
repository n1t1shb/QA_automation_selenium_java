/**
 * 
 */
package com.lexmark.indus.automation.organizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lexmark.indus.automation.BaseTestModule;

/**
 * @author nitishb1989
 *
 */
public class BasicORGTest2 extends BaseTestModule {
	private static final String COLLECTION_TO_BE_ADDED = "RegressionTestData_1";
	private static String targetCollName = "";

	@Test
	public void addUsingDropDown() throws InterruptedException {
		WebDriver driver = browser.getDriver();

		Thread.sleep(2000);
		driver.findElement(getCollectionLink()).click();
		this.waitForGridLoading();

		getSearchInput().sendKeys(COLLECTION_TO_BE_ADDED);
		this.waitForGridLoading();

		WebDriverWait waitForCheckbox = new WebDriverWait(driver, 60);
		waitForCheckbox.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@class='row-checkbox'][@type='checkbox']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='row-checkbox'][@type='checkbox']")).click();

		Select dropDown = new Select(driver.findElement(By.className("selected-items-options")));
		// Thread.sleep(5000);
		dropDown.selectByVisibleText("Add to collection");
		WebDriverWait waitForModalWindow = new WebDriverWait(driver, 60);

		driver.findElement(
				By.xpath("//button[@class='next-page btn btn-default change-page icon-arrow_east'][@type='button']"))
				.click();
		driver.findElement(By.xpath("//ul[@id='collections-list']/li[8]/span[2]")).click();

		targetCollName = driver.findElement(By.xpath("//ul[@id='collections-list']/li[8]/span[2]")).getText();
		System.out.println(targetCollName);

		By saveButton = By.xpath("//button[@class='primary']");
		waitForModalWindow.until(ExpectedConditions.elementToBeClickable(saveButton));
		Thread.sleep(2000);
		driver.findElement(saveButton).click();

		this.waitForGridLoading();
	}

	@Test(dependsOnMethods = { "addUsingDropDown" })
	public void validateIfCollectionAdded() throws InterruptedException {
		WebDriver driver = browser.getDriver();

		getSearchInput().clear();
		getSearchInput().sendKeys(targetCollName);
		this.waitForGridLoading();

		WebDriverWait waitForCheckbox = new WebDriverWait(driver, 60);
		waitForCheckbox.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@class='row-checkbox'][@type='checkbox']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='row-checkbox'][@type='checkbox']")).click();
		Thread.sleep(2000);
		/// -- jadu --
		String contentCollection = driver
				.findElement(By
						.xpath("//tbody[@id='collection-contents-wrapper']/tr/td[contains(text(), 'RegressionTestData_1')]"))
				.getText();
		System.out.println(contentCollection);
		Assert.assertEquals(contentCollection, COLLECTION_TO_BE_ADDED);
	}

}
