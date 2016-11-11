# Indus QA Automation Framework

## Prerequisites
1. Install Java 8 and include in PATH
2. Install latest Apache Maven and include in PATH
3. Install Eclipse(Optional) for writing code

## Steps to run test suites
```
git clone git@github.com:nitishb1989/indus-automation.git
cd indus-automation
mvn test
```
## Note

The test output will be generated inside `test-output` directory.
The cutomized Report will be genetated in a directory as specified the user using atu.properties file.
This framework inturn uses free open source framework from ATU for report generating purposes.

Note: The suite XML files are included in `pom.xml` as input of testng runner. Please add/modify your suite files in `pom.xml`.

## Help

All framework classes are present in `com.lexmark.indus.automation` package. You should create separate package according to your test needs(e.g. `com.lexmark.indus.automation.organizer` etc.)

1. `BaseTestModule`: All your test case should `extend` this class.
2. `@RequireLogin`: This annotation can be used with your test method if you want login into specific site before executing this method.
3. `@RequireLogout`: This annotation can be used with your test method if you want logout from current site after executing this method.
4. `BrowserType`: A java enum class to represent supported browser type(Chrome, Firefox, Internet Explorer etc)
5. `WebsiteType`: A java enum class to represent indus team created site type(Organizer, Action Manager etc)

### Scenario 1: Suite login
If you want to login into one site using one credential and then run all your tests then refer `suite-login-testng.xml` where you will find login info in suite itself.

### Scenario 2: Test method login
If your test require login using different users/sites, then refer `method-login-testng.xml` where you will find login/logout info in test method using `@RequireLogin` and `@RequireLogout` annotations.

### Scenario 3: Hybrid login(both in suite and test methods)
If your test require hybrid login mode, for example, test should continue using a specific user, but require login as some other admin user time to time, then refer `suite-method-login-testng.xml`

### Testng
Please visit [testng official documentation](http://testng.org/doc/documentation-main.html) to know how to write tests using testng.

### Selenium webdriver
Please visit [selenium official documentation](http://www.seleniumhq.org/docs/) for more info.
