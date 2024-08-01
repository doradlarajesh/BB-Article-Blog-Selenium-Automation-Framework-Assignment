package com.bblogautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage{
WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 10), this);
	}
	
	
	@FindBy(xpath="//input[@placeholder='Username']")
	WebElement inputUserName;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement inputPassword;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btnSignIn;
	
	
	public boolean isInLoginPage()
	{
		return (driver.getCurrentUrl().indexOf("login") != -1);
	}
	
	public void setUserName(String uname)
	{
		inputUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		inputPassword.click();
		inputPassword.sendKeys(pwd);
	}
	
	public void clickSignIn()
	{
		btnSignIn.click();
	}	
}
