package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	private WebDriver driver;
	private int timeout = 15;
	private By amazon = By.cssSelector("a.a-link-nav-icon");
	private By createYourAmazonAccount = By.id("createAccountSubmit");
	private By signInSubmit = By.id("signInSubmit");
	private By txt_emailOrMobilePhoneNumber= By.id("ap_email");
	private By chkBox_rememberMe = By.name("rememberMe");
	private By txt_password= By.id("ap_password");
	private By btn_Continue= By.id("continue");
	private By header_login = By.cssSelector(".a-spacing-small");

//  ========================================================================

	public LoginPage() {
	}

	public LoginPage(WebDriver driver) {
		this();
		this.driver = driver;
	}

	public LoginPage(WebDriver driver, int timeout) {
		this(driver);
		this.timeout = timeout;
	}

	public  WebElement lnk_amazon(){
		return driver.findElement(amazon);
	}

	public  WebElement txt_emailOrMobile(){
		return driver.findElement(txt_emailOrMobilePhoneNumber);
	}

	public  WebElement btn_continue(){
		return driver.findElement(btn_Continue);
	}

	public  WebElement txt_password(){
		return driver.findElement(txt_password);
	}
	public  WebElement btn_login(){
		return driver.findElement(signInSubmit);
	}
	public  WebElement header_login(){
		return driver.findElement(By.tagName("h1")).findElement(header_login);
	}
	/**
	 * Keep me signed in Check Box Instance
	 *
	 * @return remenberMe Check Box instance.
	 */
	public  WebElement chkBox_rememberMe(){
		return driver.findElement(chkBox_rememberMe);
	}
	public  WebElement lnk_createYourAmazonAccount(){
		return driver.findElement(createYourAmazonAccount);

	}

}
