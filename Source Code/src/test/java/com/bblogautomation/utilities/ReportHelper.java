package com.bblogautomation.utilities;

import com.bblogautomation.tests.BaseClass;
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.testng.ISuite;
import org.testng.ISuiteListener;

import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportHelper implements ITestListener, ISuiteListener{
    public  static ExtentReports extent;
    public  static ExtentTest test;
    public  static String repName;
	
    @Override
    public void onStart(ISuite suite)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName="BBlog-Report-"+timeStamp+".html";
		
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+repName);
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("BBLOG Automation Report");
		spark.config().setReportName("Favorite Articles Test");
		System.out.println("Done with on start");
	}
    
    @Override
    public void onFinish(ISuite suite)
	{
		extent.flush();
		try {
			Desktop.getDesktop().browse(new File (System.getProperty("user.dir")+ "/test-output/"+repName).toURI());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @Override
	public void onTestStart(ITestResult result) {

    	test = extent.createTest(result.getName());

	}
    
    @Override
	public void onTestSuccess(ITestResult result) {
    	ReportLogger.pass(result.getMethod().getMethodName() +" is passed",BaseClass.getBase64Image() );
	}
    
    @Override
	public void onTestFailure(ITestResult result) {
    	ReportLogger.fail(result.getMethod().getMethodName() +" is failed",BaseClass.getBase64Image());
	}
    
    @Override
	public void onTestSkipped(ITestResult result) {
    	ReportLogger.skip(result.getMethod().getMethodName() +" is skipped");
	}
    
    
}
