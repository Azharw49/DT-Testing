# DT-Testing
DT-Testing is a spring-boot cucumber BDD automation project devloped using components such as cucumber ,selenium webdriver ,testNG etc. The project is designed to generate cucumber report after the execution. 

Steps to run the project
----------------------------
Prerequisite : Require Java 1.8 and Maven to be installed on the machine. Environment variables has to be set.

Download the project into the local folder. Open cmd in the project root folder and type "mvn clean test"
The project will execute all the feature file present inside "resources/features" folder and a cucumber report will be generated after the execution in a new tab.
The report will be present in \target\cucumber-report-html\cucumber-html-reports\feature-overview.html.

Another approach (Applicable for Windows): Run the run.bat file inside the root directory of the project folder.

The project contains 2 feature files on which todoflow.feature and todobuttons.feature. The cucumber report generated will give a detailed view of the run.

ChromeDriver Versiob
