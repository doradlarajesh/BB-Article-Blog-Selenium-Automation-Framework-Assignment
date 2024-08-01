package com.bblogautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bblogautomation.tests.BaseClass;

public class ArticlePage extends BasePage{

	WebDriver driver;

	public ArticlePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 10), this);
	}
	
	@FindBy(xpath = "//button[contains(text(),'rite Post')]")
	public WebElement btnFavPost;
	
	@FindBy(xpath = "//button[contains(text(),'Comment')]")
	public WebElement btnComment;
	
	@FindBy(xpath = "//button[contains(text(),'Delete')]")
	public WebElement btnDelete;
	
	
	public void clickFavBtnInArticle()
	{
		try {
			Thread.sleep(1200);
			new WebDriverWait(BaseClass.driver,20).until(
					ExpectedConditions.elementToBeClickable(btnFavPost)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void clickDeleteBtnInArticle()
	{
		try {
			Thread.sleep(1200);
			new WebDriverWait(BaseClass.driver,20).until(
					ExpectedConditions.elementToBeClickable(btnDelete)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
