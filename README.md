# BB Article Blog UI Automation Framework Assignment using Selenium

## **Introduction:**

This Automation Framework implemented for Bb Blogging website using Selenium + Java with integration of Maven, TestNG and Extent Reports in year 2020.

##**Module and Test Cases:**

Selected ‘Favorite Articles’ module/flow for writing and automating the test cases. Test cases were written in excel (BBlog_FavArticle_TestCases.xlsx) and can be found in ‘Test Artifacts’ folder along with this documentation.

##**Dependencies and Plugins Used:**

 - Selenium Java 
 - TestNG
 - Extent Reports
 - WebDriverManager 
 - Maven SureFirePlugin
 - Maven Compiler Plugin

##**Project Directory Info:**

The subsequent image show the basic structure of the folders and packages that is followed to design the project

![image](https://github.com/user-attachments/assets/15f5ba23-f530-4eb6-a9e4-c5519fc6e474)

##**How to Run Test Cases:**

 1. Running of test cases can be done by following ways, 1. Running via command line after changing directory to 			the folder where this project exists. (preferred way) 

     mvnclean install (or) mvn clean verify (or) mvn test

 2. Running each test class or testng.xml by right clicking in eclipse IDE and selecting “run as testNG test”

##**Reporting:** 

Automatically after tests are executed a report which looks as below will be opened in your default browser. Integrated Extent Reports 5 and used their capabilities for generating reports with logs and screenshots in case of failed tests

*Dashboard:*

![Screenshot 2024-08-03 192420](https://github.com/user-attachments/assets/86405c4a-269f-4c7a-ae46-2a05ceedc022)

*Individual Test Case Log along with screenshots in base64 format:*

![Screenshot 2024-08-03 192752](https://github.com/user-attachments/assets/4d93e0fa-436e-422f-8490-caac0ac067d7)

*Failed Test Case along with screenshots in base64 format:*

![Screenshot 2024-08-03 192909](https://github.com/user-attachments/assets/9af7dfba-f370-4a3d-afcf-c0e6385af45b)





