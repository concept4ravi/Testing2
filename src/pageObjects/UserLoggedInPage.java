
package pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class UserLoggedInPage {
	private By lnk_signOut = By.cssSelector("#nav-item-signout-sa > span.nav-text");
	private WebDriver driver;
	private int timeout = 15;
	
	public UserLoggedInPage() {
    }

    public UserLoggedInPage(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public UserLoggedInPage(WebDriver driver, int timeout) {
        this(driver);
        this.timeout = timeout;
        
    }
	
	public  WebElement lnk_signOut(){
		return driver.findElement(lnk_signOut);
	}
}
