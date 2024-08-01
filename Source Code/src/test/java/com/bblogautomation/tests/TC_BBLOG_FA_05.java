package com.bblogautomation.tests;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.bblogautomation.pageobjects.*;
import com.bblogautomation.utilities.ReportLogger;

public class TC_BBLOG_FA_05 extends BaseClass {
	LoginTest lt = new LoginTest();

	// @Test(dependsOnMethods = { "loginIntoApp" })
	@Test
	public void unMarkFavFromFavArticles() throws InterruptedException {
		ReportLogger.info("Executing Test Case: TC_BBLOG_FA_05");
		HomePage hp = new HomePage(driver);
		ArticlePage ap = new ArticlePage(driver);
		ProfilePage pp = new ProfilePage(driver);
		if (hp.doesElementExists(hp.linkProfile)) {

			String tagToBeSelected = hp.getTagName(hp.divTags);
			hp.getTagElement(tagToBeSelected).click();
			Validator(hp.getNavElement(tagToBeSelected) != null,
					"Nav Tab " + tagToBeSelected + " selected successfully",
					"Problem in selecting Nav Tab #" + tagToBeSelected);

			WebElement artToBeSelected = getArticle("OtherUser", "true", "");
			HashMap<String, String> before = new HashMap<String, String>();
			loadArticleDataIntoMap(artToBeSelected, before);
			ReportLogger.info(MarkupHelper.createJsonCodeBlock(before));

			if (artToBeSelected != null) {
				String artNameofUnmarked = before.get("title"); // getArticleInfo(artToBeSelected, "TITLE");
				int prevFavCount = Integer.parseInt(getArticleInfo(artToBeSelected, "FAVCOUNT"));
				hp.articleItems(artToBeSelected, "TITLE").click();

				ap.clickFavBtnInArticle();

				int crntFavCount = Integer.parseInt(getArticleInfo(ap.btnFavPost, "APFAVCOUNT"));
				// Favorite Button Count Validation
				Validator(crntFavCount < prevFavCount,
						"Favorite count decreased from " + prevFavCount + " to " + crntFavCount,
						"Error in Favorite count. Current Fav Count = " + crntFavCount + " .Previous Fav Count = "
								+ prevFavCount);

				hp.navigateToProfile();

				pp.navigateToFavArticles();
				Validator(getArticle("OtherUser", "true", artNameofUnmarked) == null,
						"Removed Article: " + artNameofUnmarked + " from Fav Articles Successfully",
						"Article: " + artNameofUnmarked + " still present in Favorited Articles");

			}

		}
	}

}
