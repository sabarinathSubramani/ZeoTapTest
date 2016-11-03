package stackOverFlow.WebDriverHelper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class WebDriverManager {

	private static WebDriver webDriver;

	private static void createWebDriver(WebBrowser browser){


		switch(browser){
		case Chrome:{
			Capabilities cap = DesiredCapabilities.chrome();
			webDriver = new ChromeDriver(cap);
			break;
		}
		

		case FireFox:{
			Capabilities cap = DesiredCapabilities.firefox();
			webDriver = new FirefoxDriver(cap);
			break;
		}
		case IE:{
			Capabilities cap = DesiredCapabilities.internetExplorer();
			webDriver = new InternetExplorerDriver(cap);
			break;
		}

		}
}
	
	public static  WebDriver getWebDriver(WebBrowser browser){
		if(webDriver==null)
			createWebDriver(browser);
		return webDriver;
	}
	
	public static  WebDriver getWebDriver(){
		return webDriver;
	}
}
