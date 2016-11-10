/**
 * 
 */
package com.lexmark.indus.automation;

import org.openqa.selenium.WebDriver;

/**
 * Abstract class contains the behavior of indus websites. Subclass
 * should implement its own behavior.
 * 
 * @author nitishb1989
 *
 */
public abstract class Website {

	protected String url;
	protected WebDriver driver;
	protected UserSession session;

	/**
	 * Instantiate an instance of website
	 * 
	 * @param url
	 *            The website url
	 */
	public Website(String url) {
		this.url = url;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Open website in following driver
	 * 
	 * @param driver
	 */
	public void open(WebDriver driver) {
		this.driver = driver;
		this.driver.navigate().to(url);
	}

	/**
	 * Indicate whether website has active session
	 * 
	 * @return true if has active session, false otherwise
	 */
	public boolean hasActiveSession() {
		return session != null;
	}

	/**
	 * Login into the website using given credential
	 * 
	 * @param user
	 * @param password
	 * @throws InterruptedException
	 */
	public abstract void login(String user, String password) throws InterruptedException;

	/**
	 * Logout from the website
	 * 
	 * @throws InterruptedException
	 */
	public abstract void logout() throws InterruptedException;

	/**
	 * Create a user session
	 * 
	 * @param user
	 */
	protected void createSession(String user) {
		session = new UserSession(user);
	}

	/**
	 * Create a user session
	 * 
	 * @param user
	 */
	protected void destroySession() {
		if (session != null) {
			session.destroy();
			session = null;
		}
	}

}
