package com.bblogautomation.tests;

import java.util.HashMap;

import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.bblogautomation.pageobjects.*;
import com.bblogautomation.utilities.ReportLogger;

public class TC_BBLOG_FA_03 extends BaseClass {
	LoginTest lt = new LoginTest();

	// @Test(dependsOnMethods = { "loginIntoApp" })
	@Test
	public void markFavFromTagFeed() throws InterruptedException {
		ReportLogger.info("Executing Test Case: TC_BBLOG_FA_03");
		HomePage hp = new HomePage(driver);
		ArticlePage ap = new ArticlePage(driver);
		ProfilePage pp = new ProfilePage(driver);

		// Selecting a Tag Item
		hp.linkGlobalFeed.click();
		ReportLogger.info("Navigated to Global Feed");
		String tagToBeSelected = hp.getTagName(hp.divTags);
		ReportLogger.info("Selecting Tag: " + tagToBeSelected);
		hp.getTagElement(tagToBeSelected).click();

		// Validating whether Nav tab selected successfully or not
		Validator(hp.getNavElement(tagToBeSelected) != null, "Nav Tab " + tagToBeSelected + " selected successfully",
				"Problem in selecting Nav Tab #" + tagToBeSelected);

		// Identifying and selecting an article that is created by other user and not
		// marked as favorite
		WebElement artToBeSelected = getArticle("OtherUser", "false", "");
		HashMap<String, String> before = new HashMap<String, String>();
		loadArticleDataIntoMap(artToBeSelected, before);
		ReportLogger.info(MarkupHelper.createJsonCodeBlock(before));
		String artTitle = before.get("title");
		int beforeFavCount = Integer.parseInt(before.get("favcount"));
		ReportLogger.info("Marking favorite of article " + artTitle);

		// Opening the article and marking article as favorite
		hp.articleItems(artToBeSelected, "TITLE").click();
		ap.btnFavPost.click();

		// Validating whether favorite marked article is getting reflected in Favorited
		// Articles
		hp.navigateToProfile();
		pp.navigateToFavArticles();
		WebElement artMarkedFav = getArticle("OtherUser", "true", artTitle);
		HashMap<String, String> after = new HashMap<String, String>();
		loadArticleDataIntoMap(artMarkedFav, after);
		ReportLogger.info(MarkupHelper.createJsonCodeBlock(after));
		int afterFavCount = Integer.parseInt(after.get("favcount"));

		Validator(artMarkedFav != null, "Article reflected in Favorited Articles",
				"Article not reflected in Favorited Articles");

		// Favorite button count validation
		Validator(afterFavCount > beforeFavCount,
				"Favorite count increased from " + beforeFavCount + " to " + afterFavCount,
				"Discrepancy in Fav Count beforeFavCount :" + beforeFavCount + "afterFavCount :" + afterFavCount);

	}

}
