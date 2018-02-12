package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	private int timeout;
	private WebDriver driver;

	public HomePage() {
    }

    public HomePage(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public HomePage(WebDriver driver, int timeout) {
        this(driver);
        this.timeout = timeout;
    }
    
	private By amazon = By.cssSelector("a.nav-logo-link");
	private By amazonLogo = By.id("nav-logo");
	private By lnk_helloSignInYourOrders = By.id("nav-link-yourAccount");


	public  WebElement lnk_Amazon(){
		return driver.findElement(amazon);

	}

	public  WebElement logo_Amazon(){
		return driver.findElement(amazonLogo);
		

	}

	public  WebElement lnk_helloSignInYourOrders(){
		return driver.findElement(lnk_helloSignInYourOrders);
		

	}

}
