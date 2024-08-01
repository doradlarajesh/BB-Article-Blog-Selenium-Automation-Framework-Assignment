package com.bblogautomation.pageobjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bblogautomation.tests.BaseClass;

public class NewPostPage extends BasePage{

	WebDriver driver;

	public NewPostPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 10), this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Article Title' and @type='text']")
	 WebElement txtBoxTitle;
	
	@FindBy(xpath = "//input[contains(@placeholder,'about?')]")
	 WebElement txtBoxAbout;
	
	@FindBy(xpath = "//textarea[contains(@placeholder,'markdown')]")
	 WebElement txtBoxDescription;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Tags')]")
	 WebElement txtBoxTags;
	
	@FindBy(xpath = "//button[contains(text(),'Publish Article')]")
	 WebElement btnPublish;
	
	public boolean createArticle(HashMap<String,String> dataToEnter) throws InterruptedException
	{
		new WebDriverWait(BaseClass.driver,20).until(
				ExpectedConditions.elementToBeClickable(txtBoxTitle)).click();
		//txtBoxTitle.click();
		txtBoxTitle.sendKeys(dataToEnter.get("artTitle"));
		txtBoxAbout.click();
		txtBoxAbout.sendKeys(dataToEnter.get("artAbout"));
		txtBoxDescription.click();
		txtBoxDescription.sendKeys(dataToEnter.get("artDesc"));
		txtBoxTags.click();
		txtBoxTags.sendKeys(dataToEnter.get("artTag"));
		Thread.sleep(1000);
		btnPublish.click();
		ArticlePage ap = new ArticlePage(driver);
		return (doesElementExists(ap.btnComment));
	}
	
}
