package supportingJavaFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	private WebDriver driver=null;
	private String firefoxDriverLocation =  "/home/intern/git/Testing2/AmazonAlt/Browser Driver/geckodriver";
	private String chromeDriverLocation = "/home/intern/git/Testing2/AmazonAlt/Browser Driver/chromedriver";
	public  WebDriver getBrowser(String browserType) {
		if (driver == null) {
			if (browserType.toLowerCase().equals("firefox")){
				System.setProperty("webdriver.gecko.driver",firefoxDriverLocation); 
				driver = new FirefoxDriver();
			} else if (browserType.toLowerCase().equals("chrome")){
				System.setProperty("webdriver.chrome.driver",chromeDriverLocation);
				driver = new ChromeDriver();

			}
		} 
		return driver;
	}
}
