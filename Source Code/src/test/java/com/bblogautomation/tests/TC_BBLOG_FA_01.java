package com.bblogautomation.tests;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.bblogautomation.pageobjects.*;
import com.bblogautomation.utilities.ReportLogger;

public class TC_BBLOG_FA_01 extends BaseClass {

	// @Test(dependsOnMethods = { "loginIntoApp" })
	@Test
	public void markFavFromGlobalFeed() throws InterruptedException {
		
		ReportLogger.info("Executing Test Case: TC_BBLOG_FA_01");
		HomePage hp = new HomePage(driver);
		ProfilePage pp = new ProfilePage(driver);
		if (hp.doesElementExists(hp.linkProfile)) {
			//Navigating to GlobalFeed
			ReportLogger.info("Successfully logged in");
			hp.linkGlobalFeed.click();
			ReportLogger.info("Navigated to Global Feed");
			
			//selecting an article created by other user which is not marked as favorite
			WebElement otherUserArticle = getArticle("OtherUser", "false", "");
			HashMap<String, String> before = new HashMap<String,String>();
			loadArticleDataIntoMap(otherUserArticle, before);
			ReportLogger.info(MarkupHelper.createJsonCodeBlock(before));
            int beforeFavCount = Integer.parseInt(before.get("favcount"));
			
			//marking article as favorite
			clickFavBtnOfArticle(otherUserArticle);
			ReportLogger.info("Fav Button clicked");
			
			//going to Favorited Articles in Profile and validating whether above article is present or not
			hp.navigateToProfile();
			pp.navigateToFavArticles();
			WebElement articleMarkedFav = getArticle("OtherUser", "true", before.get("title"));
			HashMap<String, String> after = new HashMap<String,String>();
			loadArticleDataIntoMap(articleMarkedFav, after);
			int afterFavCount = Integer.parseInt(after.get("favcount"));
			ReportLogger.info(MarkupHelper.createJsonCodeBlock(after));
			
			//Validations:
			
			//Favorite count validation
			Validator(afterFavCount > beforeFavCount,"Favorite count increased from " + beforeFavCount + " to "+  afterFavCount,"Discrepancy in Fav Count");
			
			//Article listed in Favorited Articles Validations
			Validator(articleMarkedFav != null && Boolean.parseBoolean(after.get("favstatus")), "Favorite Marked Article is reflecting in Favorited Articles of Profile",
					"Favorite Marked Article is not reflecting in Favorited Articles of Profile");
			
			
		}

	}

}
