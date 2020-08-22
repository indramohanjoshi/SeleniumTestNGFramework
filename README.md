# SeleniumTestNGFramework
* This is an automation testing framework build upon **Selenium** and **TestNG** frameworks.

# Prerequisites
* Java 8 or higher version <br/> 
* Maven - Build and packaging tool <br/>
* Git - For code repository <br/>
* Eclipse - IDE - Integrated development environment for development <br/>
* TestNG Eclipse plug-in - To Create or Convert TestNG test classes and Launch Test  classes or TestNG.xml<br/>
 * Allure binaries - https://docs.qameta.io/allure/ guide for installation


# Dependencies
* Selenium - For Automation framework.<br/>
* TestNG - For Testing framework.<br/>
* WebDriverManager - To manage all web drivers, without having driver.exe in class path.<br/>
* Allure - For Reporting framework.<br/>
* Spring - core, context, web-mvc, test  - For dependency management and context building.<br/>
* Logback and slf4j - For logging<br/>
* Apache POI - For excel read/write operations.<br/>

# Plugins
* Maven compiler plug-in - For code compilation and source code packaging<br/>
* Maven surefire plug-in - For executing test cases<br/>
* Allure maven plug-in - For test report Generation<br/>

# Code Structure
* src/main/java - reusable source code<br/>
* src/main/resources - resources i.e. external test data, logback.xml<br/>
* src/test/java - test classes<br/>
* src/test/resources - allure.properties, application.properties<br/>

# Goal
- **mvn clean test -DsuiteXmlFile=testng.xml**<br/>
	 To run testng.xml files from maven<br/>
- **mvn allure:serve** <br/>
	To serve allure report in browser. It needs Content delivery network to serve allure html report.
 


