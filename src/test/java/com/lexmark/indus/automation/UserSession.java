/**
 * 
 */
package com.lexmark.indus.automation;

/**
 * The POJO to represent user session
 * 
 * @author nitishb1989
 *
 */
public class UserSession {

	private String user;

	public UserSession(String user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Destroy the session
	 */
	public void destroy() {
		this.user = null;
	}
}
