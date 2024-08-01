package com.bblogautomation.tests;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.bblogautomation.pageobjects.*;
import com.bblogautomation.utilities.ReportLogger;

public class TC_BBLOG_FA_04 extends BaseClass {
	LoginTest lt = new LoginTest();

	// @Test(dependsOnMethods = { "loginIntoApp" })
	@Test
	public void unMarkFavFromFavArticles() throws InterruptedException {
		ReportLogger.info("Executing Test Case: TC_BBLOG_FA_04");
		HomePage hp = new HomePage(driver);
		ProfilePage pp = new ProfilePage(driver);
		if (hp.doesElementExists(hp.linkProfile)) {
			hp.navigateToProfile();
			pp.navigateToFavArticles();
			WebElement ownArtMarkedFav = getArticle("CurrentUser", "true", "");
			HashMap<String, String> before = new HashMap<String, String>();
			loadArticleDataIntoMap(ownArtMarkedFav, before);
			ReportLogger.info(MarkupHelper.createJsonCodeBlock(before));

			if (ownArtMarkedFav != null) {
				String unMarkArtTitle = before.get("title");
				hp.articleItems(ownArtMarkedFav, "FAVBTN").click();
				// Thread.sleep(1500);
				ReportLogger.info("Refreshing Fav Articles Screen");
				driver.navigate().refresh();
				pp.navigateToFavArticles();
				// Thread.sleep(1500);
				Validator(getArticle("CurrentUser", "true", unMarkArtTitle) == null,
						"Removed article: " + unMarkArtTitle + " from Favorited Articles Successfully",
						"Article: " + unMarkArtTitle + " still exists in Favorited Articles");
			} else {
				ReportLogger.fail("There are no own articles that were marked as favorite", getBase64Image());
				Assert.assertTrue(false);
			}

		}

	}
}
