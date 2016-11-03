package stackOverFlow.WebDriverHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/*
 * supports id, name, xpath locators only
 */
public class PageElement {

	private WebElement ele;
	private By by;

	public WebElement getWebElement(){
		return ele;
	}

	public PageElement(By eleLocator){

		by = eleLocator;
		//	initElement();
	}

	public void click(){
		initElement();
		ele.click();
	}

	public void type(String text){
		initElement();
		ele.sendKeys(text);
	}

	public String getText(){
		initElement();
		return ele.getText();
	}

	protected void initElement(){

		try{
			ele = WebDriverManager.getWebDriver().findElement(by);
		}catch(NoSuchElementException e){
			e.printStackTrace();
		}

	}

	public void setBy(By by) {
		this.by = by;
	}

	public By getBy() {
		return by;
	}
}
