package stackOverFlow.PageModels;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import stackOverFlow.WebDriverHelper.PageElement;
import stackOverFlow.WebDriverHelper.WebDriverManager;
import stackOverFlow.WebDriverHelper.WebPage;

public class StackOverFlowTagsPage extends WebPage{


	static PageElement identifier = new PageElement(By.cssSelector("input#tagfilter"));
	static PageElement txtTagsSearch = new PageElement(By.cssSelector("input#tagfilter"));
	static PageElement eleTagsList = new PageElement(By.cssSelector("table#tags-browser"));

	public StackOverFlowTagsPage() throws Exception {
		super(identifier);
	}


	public void filterTags(String tagName) throws Exception{

		txtTagsSearch.type(tagName);
		waitForStaleness(eleTagsList);
	}

	public Tags getMaxCurrentYearAskedTag(){

		Tags  maxCurrentYearTag = null;
		List<WebElement> findElements = WebDriverManager.getWebDriver().findElements(By.cssSelector("td.tag-cell"));

		for(WebElement ele : findElements){
			Tags tags = new Tags(ele);
			if(maxCurrentYearTag == null || maxCurrentYearTag.askedCurrentYearCount < tags.askedCurrentYearCount ){
				maxCurrentYearTag=tags;
			}
		}
		return maxCurrentYearTag;
	}

	static public class Tags{

		private String tagName;
		private int askedCurrentYearCount;

		public Tags(WebElement ele){

			tagName = ele.findElement(By.cssSelector("a.post-tag")).getText();
			try{
				askedCurrentYearCount= Integer.valueOf(ele.findElement(By.cssSelector("a[title~='365']")).getText().replaceAll("\\D",""));
			}catch(NoSuchElementException nse){
				askedCurrentYearCount=0;
			}
		
		}

		public String getTagName() {
			return tagName;
		}

		public void setTagName(String tagName) {
			this.tagName = tagName;
		}

		public int getAskedCurrentYearCount() {
			return askedCurrentYearCount;
		}

		public void setAskedCurrentYearCount(int askedCurrentYearCount) {
			this.askedCurrentYearCount = askedCurrentYearCount;
		}

	}

}
