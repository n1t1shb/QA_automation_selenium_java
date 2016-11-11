/**
 * 
 */
package com.lexmark.indus.automation;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/*For Report Generation*/
import java.awt.AWTException;
import java.io.IOException;
import org.testng.annotations.Listeners;
import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
/**end**/

/**
 * Abstract test module which contains all the methods required to run a web portal
 * test suite for indus. All your test cases should extend this class.
 * 
 * @author nitishb1989
 * 
 */

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
    MethodListener.class })
public abstract class BaseTestModule {

	 {
	    System.setProperty("atu.reporter.config", "D:/E2E/indus-automation/atu.properties");
	 }
	 
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
		// ATU Reports
        ATUReports.setWebDriver(browser.getDriver());
        ATUReports.indexPageDescription = "Nitish Bhattacharjee";
	}

	@AfterSuite
	protected void afterSuite() throws InterruptedException,AWTException, IOException {
		if (website.hasActiveSession()) {
			website.logout();
		}
		ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(
                ScreenshotOf.BROWSER_PAGE));
        ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(
                ScreenshotOf.DESKTOP));
        ATUReports.add("Warning Step", LogAs.WARNING,
                new CaptureScreen(ScreenshotOf.DESKTOP));
        ATUReports.add("Fail step", LogAs.FAILED, new CaptureScreen(
                ScreenshotOf.DESKTOP));
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

}
