package stackOverFlow.PageModels;

import org.openqa.selenium.By;

import stackOverFlow.WebDriverHelper.PageElement;
import stackOverFlow.WebDriverHelper.WebPage;

public class StackOverFlowHomePage extends WebPage {

	public static PageElement identifier = new PageElement(By.cssSelector("div.hero-content"));
	
	public PageElement eleTags = new PageElement(By.cssSelector("a#nav-tags"));
	
	
	public StackOverFlowHomePage() throws Exception {
		super(identifier, "http://stackoverflow.com");
	}
	
	public StackOverFlowTagsPage navigateToTagsPage() throws Exception{
		eleTags.click();
		return new StackOverFlowTagsPage();
		
	}
	

}
