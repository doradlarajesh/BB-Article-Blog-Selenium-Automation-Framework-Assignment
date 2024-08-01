package com.bblogautomation.tests;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import java.util.List;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;

import com.bblogautomation.utilities.ReadConfig;
import com.bblogautomation.utilities.ReportLogger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	static ReadConfig readconfig = new ReadConfig();

	public String baseURL = readconfig.getConfigData("baseURL");
	public String svrUserName = readconfig.getConfigData("serverUserName");
	public String svrPassword = readconfig.getConfigData("serverPassword");
	public static String blogEmail = readconfig.getConfigData("email");
	public static String blogPassword = readconfig.getConfigData("password");
	public String blogUserName = readconfig.getConfigData("username");
	public static WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void setUp(String brwsr) {
		brwsr = brwsr.toUpperCase();
		switch (brwsr) {
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://" + svrUserName + ":" + svrPassword + "@" + baseURL);
		driver.manage().window().maximize();
		LoginTest.loginBBlogTest();
	}

	@AfterClass
	public void cleanUp() {
		driver.quit();
	}

	public static String getBase64Image() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	protected String generateRandoxText(int len) {
		return RandomStringUtils.randomAlphanumeric(len);

	}

	public static void Validator(Boolean condition, String PassMessage, String FailMessage) {
		if (condition) {
			ReportLogger.pass(PassMessage);
			Assert.assertTrue(true);
		} else {
			ReportLogger.fail(FailMessage, getBase64Image());
			Assert.assertTrue(false);
		}
	}

	// UserType: CurrentUser or OtherUser

	public WebElement getArticle(String userType, String isFavorited, String articleTitle) {
		WebElement returnArticle = null;
		try {
			String user = userType.equalsIgnoreCase("CurrentUser") ? blogUserName : "(.*)";
			Thread.sleep(2500);
			WebElement articleList = driver.findElement(By.tagName("app-article-list"));
			List<WebElement> allArticles = articleList.findElements(By.tagName("app-article-list-item"));
			for (WebElement eachArticle : allArticles) {
				if (user.equals(blogUserName)) {
					if (getArticleInfo(eachArticle, "author").matches(user)
							&& getArticleInfo(eachArticle, "favstatus").equalsIgnoreCase(isFavorited)) {
						if ((!articleTitle.equals("")) && (getArticleInfo(eachArticle, "title").equals(articleTitle))) {
							returnArticle = eachArticle;
							break;
						} else if (articleTitle.equals("")) {
							returnArticle = eachArticle;
							break;
						}
					}

				} else {
					if ((!getArticleInfo(eachArticle, "author").equals(blogUserName))
							&& getArticleInfo(eachArticle, "favstatus").equalsIgnoreCase(isFavorited)) {
						if (articleTitle.equals("")) {
							returnArticle = eachArticle;
							break;
						} else if (getArticleInfo(eachArticle, "title").equals(articleTitle)) {
							returnArticle = eachArticle;
							break;
						}
					}
				}
			}
			return returnArticle;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return returnArticle;
		}

	}

	public String getArticleInfo(WebElement article, String articleAttribute) {
		String articleInfo = "";
		try {
			Thread.sleep(1000);
			articleAttribute = articleAttribute.toUpperCase();

			switch (articleAttribute) {
			case "AUTHOR":
				articleInfo = article.findElement(By.xpath(".//a[@class='author']")).getText();
				break;

			case "FAVSTATUS":
				// when current user havent marked article as favorite we return false else true
				articleInfo = article.findElement(By.tagName("button")).getAttribute("class").contains("btn-outline")
						? "false"
						: "true";
				break;

			case "TITLE":
				By titlePath = new ByChained(By.xpath(".//a[@class='preview-link']"), By.tagName("h1"));
				articleInfo = article.findElement(titlePath).getText();
				break;

			case "FAVCOUNT":
				By favCountPath = new ByChained(By.tagName("button"), By.tagName("div"));
				articleInfo = article.findElement(favCountPath).getText();
				break;
			case "APFAVCOUNT":
				articleInfo = article.findElement(By.tagName("span")).getText().replaceAll("[()]", "");
				break;

			}
			return articleInfo;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return articleInfo;
		}
	}

	protected void clickFavBtnOfArticle(WebElement article) throws InterruptedException {
		// article.findElement(By.tagName("button")).click();
		new WebDriverWait(BaseClass.driver, 20)
				.until(ExpectedConditions.presenceOfNestedElementLocatedBy(article, By.tagName("button"))).click();
		Thread.sleep(100);
	}

	protected String generateArticleTitle() {
		String crntTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

		return ("By Raji @" + crntTimeStamp);
	}

	protected HashMap<String, String> generateArticleData() {
		HashMap<String, String> articleData = new HashMap<String, String>();
		articleData.put("artTitle", generateArticleTitle());
		articleData.put("artAbout", generateRandoxText(10));
		articleData.put("artDesc", generateRandoxText(60));
		articleData.put("artTag", "#" + generateRandoxText(4));
		return articleData;
	}

	protected HashMap<String, String> loadArticleDataIntoMap(WebElement article, HashMap<String, String> mapobj) {
		mapobj.put("author", getArticleInfo(article, "author"));
		mapobj.put("title", getArticleInfo(article, "title"));
		mapobj.put("favcount", getArticleInfo(article, "favcount"));
		mapobj.put("favstatus", getArticleInfo(article, "favstatus"));
		return mapobj;

	}
}
