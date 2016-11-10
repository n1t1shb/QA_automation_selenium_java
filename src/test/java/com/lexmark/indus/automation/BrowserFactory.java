/**
 * 
 */
package com.lexmark.indus.automation;

/**
 * Singleton factory class to instantiate {@link Browser}
 * 
 * @author nitishb1989
 *
 */
public class BrowserFactory {

	private static BrowserFactory _instance = new BrowserFactory();

	private BrowserFactory() {

	}

	/**
	 * 
	 * @return A singleton instance of {@link BrowserFactory}
	 */
	public static BrowserFactory getInstance() {
		return _instance;
	}

	/**
	 * Get an instance of {@link Browser} depending on given {@link BrowserType}
	 * 
	 * @param browserType
	 * @return An instance of {@link Browser}
	 */
	public Browser getBrowser(BrowserType browserType) {
		Browser browser = null;
		switch (browserType) {
		case Chrome:
			browser = new Chrome();
			break;
		case Firefox:
			browser = new Firefox();
			break;
		case IE:
			browser = new IE();
		default:
			break;
		}
		return browser;
	}

}
