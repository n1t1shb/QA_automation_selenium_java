/**
 * 
 */
package com.lexmark.indus.automation;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Abstract test module which contains all the methods required to run a web portal
 * test suite for indus. All your test cases should extend this class.
 * 
 * @author nitishb1989
 * 
 */

public abstract class BaseTestModule {

	protected static Browser browser;
	protected static Website website;

	@BeforeSuite
	@Parameters({ "browserType", "url", "user", "password", "siteType" })
	protected void beforeSuite(String browserType, @Optional String url, @Optional String user,
			@Optional String password, @Optional String siteType) throws InterruptedException {
		browser = BrowserFactory.getInstance().getBrowser(BrowserType.valueOf(browserType));
		browser.maximize();
		if (siteType != null && url != null) {
			website = WebsiteFactory.getInstance().getWebsite(WebsiteType.valueOf(siteType), url);
			browser.navigate(website);

			if (user != null && password != null) {
				website.login(user, password);
			}
		}
	}

	@AfterSuite
	protected void afterSuite() throws InterruptedException {
		if (website.hasActiveSession()) {
			website.logout();
		}
		browser.close();
	}

	@BeforeMethod
	protected void beforeTestMethod(Method test, Object[] params) throws InterruptedException {
		RequireLogin requireLogin = test.getDeclaredAnnotation(RequireLogin.class);
		if (requireLogin != null) {
			String url = requireLogin.url();
			String user = requireLogin.user();
			String password = requireLogin.password();
			WebsiteType websiteType = requireLogin.siteType();

			if (website != null) {
				if (website.hasActiveSession()) {
					// Has active session, so log out
					website.logout();
				}
				if (url != null && !url.trim().isEmpty() && !url.equalsIgnoreCase(website.getUrl())) {
					// User wants to login to different site
					website = WebsiteFactory.getInstance().getWebsite(websiteType, url);
					browser.navigate(website);
				}
				website.login(user, password);
			} else {
				// Create a new one and login, it indicates first login from
				// test method
				website = WebsiteFactory.getInstance().getWebsite(websiteType, url);
				browser.navigate(website);
				website.login(user, password);
			}
		}
	}

	@AfterMethod
	protected void afterTestMethod(Method test, Object[] params) throws InterruptedException {
		RequireLogout requireLogout = test.getDeclaredAnnotation(RequireLogout.class);
		if (requireLogout != null) {
			if (website != null) {
				if (website.hasActiveSession()) {
					// Has active session, so log out
					website.logout();
				}
			}
		}
	}

	/**
	 * Get the collection link in left pane
	 * 
	 * @return An instance of {@link By}
	 */
	protected By getCollectionLink() {
		return By.xpath("//div[@id='siteNavigation']/ul[2]/li/span[2]");
	}

	/**
	 * Utility method which provide explicit lock until grid loaded successfully
	 */
	protected void waitForGridLoading() {
		WebDriverWait waitForGridLoad = new WebDriverWait(browser.getDriver(), 50);
		waitForGridLoad.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@class='column-header-checkbox'][@type='checkbox']")));
	}

	/**
	 * Get search input element
	 * 
	 * @return An instance of {@link WebElement}
	 */
	protected WebElement getSearchInput() {
		WebElement searchInput = browser.getDriver().findElement(By.id("searchInput"));
		searchInput.clear();
		return searchInput;
	}

}
