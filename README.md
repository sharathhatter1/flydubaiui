# Flydubai Assignment

Overview
This project showcases automated testing of web applications using Selenium WebDriver with Java. 
It employs the Page Object Model (POM) design pattern for maintaining a clear separation between test code and page-specific code.

Tech Stack
Selenium WebDriver: Automation testing tool for web applications.
Java: Programming language used for writing test scripts.
TestNG: Testing framework for organizing test cases and generating reports.
Page Object Model (POM): Design pattern for creating reusable and maintainable page objects.
Allure: Reporting framework for generating detailed test reports with rich visualizations.
Log4j2: Logging framework for capturing and managing logs during test execution.
Gradle: Build automation tool for managing project dependencies and building the project.

Prerequisite
Allure and Gradle needs to be installed.

Download the rar and open using any IDE(Intellij)
In the terminal, go to the project folder

Run Tests: Execute the test suite using the following command:
./gradlew :web:test -DRunType='smoke'

Generate Allure Report: After test execution, generate the Allure report by running the following command:
allure serve web/build/allure-results

Project Structure
src/main/java: Contains source code for page objects and utilities.
src/test/java: Contains test scripts organized in packages.
src/test/resources: Contains test data and configuration files.

Test Execution
Test cases are executed using TestNG framework.
Test data can be provided using external data sources like properties files.

Reporting
Test reports are generated using Allure framework.
Allure provides detailed and interactive reports with graphical representations of test results.

Logging
Log4j2 is used for logging purposes.
Logs are generated for each test case execution and can be found in the web/log folder under propertieslogs.log.

Enhancements
1. Data Source Flexibility:
   Integrate the framework with diverse data sources like Excel or a centralized repository for efficient test data management and improved scalability.

2. Parallel Testing:
   Implement parallel execution to run multiple tests concurrently, reducing overall test execution time.

3. Continuous Integration (CI):
   Integrate the framework with CI tools to automate testing, build, and deployment processes, ensuring consistent quality and faster delivery of software updates.

