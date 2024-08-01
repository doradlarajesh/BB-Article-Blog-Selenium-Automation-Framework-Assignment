package com.bblogautomation.utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;

public final class ReportLogger {

	private ReportLogger() {}
	
	public static void pass(String message)
	{
		//ReportHelper.test.pass(message);
	      ReportHelper.test.pass(message);
	}
	
	public static void pass(String message, String base64Img)
	{
	      ReportHelper.test.pass(message,MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build());
	}
	
	public static void fail(String message, String base64Img)
	{
		ReportHelper.test.fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build());
	}
	
	public static void info(String message)
	{
		ReportHelper.test.info(message);
	}
	
	public static void info(Markup markup)
	{
		ReportHelper.test.info(markup);
	}
	
	public static void skip(String message)
	{
		ReportHelper.test.skip(message);	
	}
	
}
