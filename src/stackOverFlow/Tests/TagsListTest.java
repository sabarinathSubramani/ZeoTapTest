package stackOverFlow.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;



import stackOverFlow.PageModels.StackOverFlowHomePage;
import stackOverFlow.PageModels.StackOverFlowTagsPage;
import stackOverFlow.PageModels.StackOverFlowTagsPage.Tags;
import stackOverFlow.WebDriverHelper.WebDriverManager;

public class TagsListTest {

	@Test
	public void tagsListTest(){

		try {
			StackOverFlowHomePage homePage  = new StackOverFlowHomePage();
			StackOverFlowTagsPage tagsPage = homePage.navigateToTagsPage();
			tagsPage.filterTags("qa");
			Tags maxCurrentYearAskedTag = tagsPage.getMaxCurrentYearAskedTag();
			System.out.println(maxCurrentYearAskedTag.getTagName() +" - "+ maxCurrentYearAskedTag.getAskedCurrentYearCount());

		} catch (Exception e) {
			Assert.assertTrue(false, "test failed with following exception - "+ e.getMessage());
		}

	}

	@AfterMethod
	public void tearDown(){
		WebDriverManager.getWebDriver().quit();
	}


}
