package com.bblogautomation.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import com.bblogautomation.pageobjects.*;

public class LoginTest extends BaseClass {


	@Test
	public static void loginBBlogTest() {
		try {
			System.out.println("Came into loginBBlogTest execution");
			if (driver.getTitle().equals("BBlog")) {
				// Log that we have entered bblog by entering server user name and password
				// successfully
				HomePage hp = new HomePage(driver);
				LoginPage lp = new LoginPage(driver);
				System.out.println("Trying to perform Sign in Test");
				// if (hp.doesSignInExists()) {
				if (hp.doesElementExists(hp.linkSignIn)) {
					System.out.println("Sign in Button is present");
					hp.clickSignIn();
					if (lp.isInLoginPage()) {
						System.out.println("Navigated to Login Page");
						lp.setUserName(blogEmail);
						Thread.sleep(3500);
						lp.setPassword(blogPassword);
						Thread.sleep(3500);
						lp.clickSignIn();
						Thread.sleep(3500);
					}

				}

				Assert.assertTrue(hp.doesElementExists(hp.linkNewPost), "Login Failed");

			}
		} catch (Exception e) {
           
		}
	}

//	@Test
//	public static void setTheStage() {
//		try {
//			if (hp.doesElementExists(hp.linkProfile)) {
//				// navigate to profile page
//				hp.navigateToProfile();
//				pp.navigateToMyArticles();
//				while (driver.findElements(By.tagName("app-article-list-item")).size() > 0) {
//					WebElement articleElem = driver.findElement(By.tagName("app-article-list-item"));
//					By titlePath = new ByChained(By.xpath(".//a[@class='preview-link']"), By.tagName("h1"));
//					articleElem.findElement(titlePath).click();
//					ap.clickDeleteBtnInArticle();
//					hp.navigateToProfile();
//					pp.navigateToMyArticles();
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}

}
