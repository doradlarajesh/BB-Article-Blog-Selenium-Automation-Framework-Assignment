package com.bblogautomation.tests;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.bblogautomation.pageobjects.*;
import com.bblogautomation.utilities.ReportLogger;

public class TC_BBLOG_FA_02 extends BaseClass {

	LoginTest lt = new LoginTest();

	// @Test(dependsOnMethods = { "loginIntoApp" })
	@Test
	public void addArticlesAndMarkFavorite() throws InterruptedException {
		ReportLogger.info("Executing Test Case: TC_BBLOG_FA_02");
		HomePage hp = new HomePage(driver);
		ProfilePage pp = new ProfilePage(driver);
		NewPostPage np = new NewPostPage(driver);

		// Generating article Data at Run Time
		HashMap<String, String> TC2Data = generateArticleData();
		ReportLogger.info(MarkupHelper.createJsonCodeBlock(TC2Data));

		if (hp.doesElementExists(hp.linkProfile)) {

			// Creating two new articles with same data in every field
			for (int i = 0; i < 2; i++) {
				hp.linkNewPost.click();
				np.createArticle(TC2Data);
			}
			ReportLogger.info("Created two articles");

			// Navigating to My Articles in profile page and marking one article as favorite
			hp.navigateToProfile();
			pp.navigateToFavArticles();
			pp.navigateToMyArticles();
			WebElement myArticle = getArticle("CurrentUser", "false", TC2Data.get("artTitle"));
			int prevFavCount = Integer.parseInt(getArticleInfo(myArticle, "FAVCOUNT"));
			hp.articleItems(myArticle, "FAVBTN").click();
			WebElement favMarkedArticle = getArticle("CurrentUser", "true", TC2Data.get("artTitle"));
			int crntFavCount = Integer.parseInt(getArticleInfo(favMarkedArticle, "FAVCOUNT"));
			
			//Validations
			
			// Validating whether only one marked as favorite although data is same for two
			Validator((getArticle("CurrentUser", "false", TC2Data.get("artTitle")) != null) && (favMarkedArticle != null),
					"Although data of two articles are same, when one is marked as favorite only that is marked as favorite",
					"Some problem in marking own articles as favorites");
			
			// Validating Favorite Button count
			Validator((crntFavCount - prevFavCount == 1) || (crntFavCount > prevFavCount),
					"Favorite count increased from " + prevFavCount + " to " + crntFavCount,"Some discrepancy in Favorite count validation");
			
            //Validating article is present in Favorited Articles
			pp.navigateToFavArticles();
			Validator(getArticle("CurrentUser", "true", TC2Data.get("artTitle")) != null,
					"Favorite marked one is present in Favorited articles","Favorite marked article is not present in Favorited Articles");
		}

	}
}
