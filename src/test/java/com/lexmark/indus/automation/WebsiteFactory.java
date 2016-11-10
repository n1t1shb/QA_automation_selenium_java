/**
 * 
 */
package com.lexmark.indus.automation;

/**
 * Singleton factory class to instantiate {@link Website}
 * 
 * @author nitishb1989
 *
 */
public class WebsiteFactory {

	private static WebsiteFactory _instance = new WebsiteFactory();

	private WebsiteFactory() {

	}

	/**
	 * 
	 * @return A singleton instance of {@link WebsiteFactory}
	 */
	public static WebsiteFactory getInstance() {
		return _instance;
	}

	/**
	 * Get an instance of website by type and url
	 * 
	 * @param websiteType
	 * @param url
	 * @return An instance of {@link Website}
	 */
	public Website getWebsite(WebsiteType websiteType, String url) {
		Website site = null;
		switch (websiteType) {
		case Organizer:
			site = new Organizer(url);
			break;
		case ActionManager:
			site = new ActionManager(url);
		default:
			break;
		}
		return site;
	}

}
