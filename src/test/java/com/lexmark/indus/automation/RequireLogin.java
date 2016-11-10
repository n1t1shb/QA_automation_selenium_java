/**
 * 
 */
package com.lexmark.indus.automation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation with your test method if you want to login into the
 * application before executing the test method.
 * 
 * @author nitishb1989
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequireLogin {

	String user() default "";

	String password() default "";

	String url() default "";

	WebsiteType siteType() default WebsiteType.Organizer;
}
