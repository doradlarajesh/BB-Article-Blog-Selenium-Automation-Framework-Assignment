# BB-Article-Blog-Selenium-Framework-Assignment

 INTRODUCTION:
 This document deals with high information related to Automation Framework implemented for BBlog
 and how to run the test cases that were automated as part of Backbase QA assignment.
 FRAMEWORK INTRO:
 Automation of Bblog website is done by means of Selenium Framework using Java with integration of 
Maven, TestNG and Extent Reports.
 MODULE AND TESTCASES:
 Selected ‘Favorite Articles’ module for writing and automating the test cases. Test cases were written 
in excel (BBlog_FavArticle_TestCases.xlsx) and can be found in ‘Test Artifacts’ folder along with this 
documentation. 
HOWTORUNTESTCASES:
 Running of test cases can be done by following ways, 
1. Running via command line after changing directory to the folder where this project exists. 
(preferred  way) 
mvnclean install (or) mvn clean verify (or) mvn test
 2. Running each test class or testng.xml by right clicking in eclipse IDE and selecting “run as testNG
 test”
 REPORTS OF TESTS EXECUTED:
 Automatically after tests are executed a report which looks as below will be opened in your default 
browser. Integrated Extent Reports 5 and used their capabilities for generating reports with logs and 
screenshots in case of failed tests
