package stackOverFlow.WebDriverHelper;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebPage {

	private WebDriver webDriver;
	private WebBrowser browser;
	private PageElement pageIdentifier;

	public WebPage(WebBrowser webBroser, PageElement ele) throws Exception{
		browser = webBroser;
		pageIdentifier = ele;
		initWebDriver();
	}
	public WebPage(WebBrowser webBroser) throws Exception{
		browser = webBroser;
		pageIdentifier = null;
		initWebDriver();
	}

	public WebPage(WebBrowser webBroser, PageElement ele, String Url) throws Exception{
		browser = webBroser;
		pageIdentifier = ele;
		initWebDriver();
	}


	public WebPage(PageElement ele, String url) throws Exception{
		browser = null;
		pageIdentifier = ele;
		initWebDriver();
		open(url);
	}



	public WebPage(PageElement identifier) throws Exception{
		pageIdentifier = identifier;
		initWebDriver();
		if(pageIdentifier!=null){
			pageIdentifier.initElement();
			waitforElementtoLoad(pageIdentifier.getBy());
		}
	}

	public WebPage() throws Exception{
		pageIdentifier = null;
		initWebDriver();
		if(pageIdentifier!=null){
			pageIdentifier.initElement();
			waitforElementtoLoad(pageIdentifier.getBy());
		}
	}


	private void initWebDriver() throws Exception{
		try{
			webDriver = WebDriverManager.getWebDriver(browser==null?WebBrowser.FireFox:browser);
		}catch(Exception e){
			throw new Exception("unable to create webdriver", e);
		}
	}

	public void open(String url) throws Exception{
		if(url == null)
			throw new Exception("url cannot be null");

		try{
			webDriver.navigate().to(new URL(url));
		}catch(Exception e){
			throw new Exception("Unable to open the url provided. please check the url provided",e);
		}
		waitForPageToLoad();
		
		if(pageIdentifier!=null){
			pageIdentifier.initElement();
			waitforElementtoLoad(pageIdentifier.getBy());
		}

	}

	
	public void waitForPageToLoad(){
		
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		wait.until((ExpectedCondition<Boolean>) webDriver -> {

            String documentState = (String)((JavascriptExecutor) webDriver).executeScript("return document.readyState;");
            if(documentState.equalsIgnoreCase("complete"))
                return true;
            else
                return false;
        });
		
	}
	
	private void waitforElementtoLoad(By by) throws Exception{

		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
		}catch(TimeoutException exe){
			throw new Exception("page identifier element not found. This is not the desired page", exe);
		}
	}

	public void waitforElementtoLoad(PageElement ele) throws Exception{

		ele.initElement();
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(ele.getBy()));
		}catch(TimeoutException exe){
			throw new Exception("page identifier element not found. This is not the desired page", exe);
		}
	}

	public void waitforElementtobeVisible(PageElement ele) throws Exception{

		try{
			ele.initElement();
		}catch(NoSuchElementException e){

		}
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele.getBy()));
		}catch(TimeoutException exe){
			throw new Exception("page identifier element not found. This is not the desired page", exe);
		}
	}
	public void waitForElementtoDisappear(PageElement ele) throws Exception{
		ele.initElement();		
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ele.getBy()));

	}
	
	
	public void waitForStaleness(PageElement ele) throws Exception{
		ele.initElement();
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		wait.until(ExpectedConditions.stalenessOf(ele.getWebElement()));
	}
	
	  public void waitForAJAXCallsToComplete() {
	        (new WebDriverWait(webDriver, 30)).until((ExpectedCondition<Boolean>) d -> {
	            JavascriptExecutor js = (JavascriptExecutor) d;
	            return (Boolean)js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
	        });
	    }


	public void close(){
		webDriver.close();
	}
}
