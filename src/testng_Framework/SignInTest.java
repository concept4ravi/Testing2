package testng_Framework;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

// import HomePage.java,LoginPage.java
import pageObjects.*;

public class SignInTest extends RequiredData{
	private WebDriver driver;
	private HomePage hp;
	private LoginPage lp;
	private UserLoggedInPage uli;
	public String Pwindow ;
	Boolean flag;
	@BeforeTest
	@Parameters({"browser"})
	public void setBrowser(String browser) {
		if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",firefoxDriverLocation); 
			driver = new FirefoxDriver();
		}else if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",chromeDriverLocation);
			driver = new ChromeDriver();
		}
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		uli = new UserLoggedInPage(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	@Test
	public void HomePage() {
		driver.get(baseUrl);
		try {

			flag=hp.logo_Amazon().isDisplayed();

		}catch(Exception e) {
			flag=false;
			System.out.println("B");
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(flag, "Unable to access "+baseUrl);
	}
	@Test(dependsOnMethods="HomePage")
	public void openSignInPage() throws InterruptedException {
		hp.lnk_helloSignInYourOrders().click();
		verifySignInPage();
	}
	@Test(dataProvider = "Login",dependsOnMethods="openSignInPage")
	public void signIn(String uname,String password) throws InterruptedException {
		verifySignInPage();
		try {
			flag=lp.txt_emailOrMobile().isDisplayed();

		}catch(Exception e) {
			flag=false;
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(flag, "Email id field not available");
		lp.txt_emailOrMobile().click();
		lp.txt_emailOrMobile().clear();
		lp.txt_emailOrMobile().sendKeys(uname);
		lp.btn_continue().click();
		try {
			flag=lp.txt_password().isDisplayed();

		}catch(Exception e) {
			flag=false;
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(flag, "Password field not available");
		lp.txt_password().click();
		lp.txt_password().clear();
		lp.txt_password().sendKeys(password);
		lp.btn_login().click();
		Thread.sleep(1000);
		WebElement link = driver.findElement(By.id("nav-link-yourAccount"));
		boolean b = link.findElement(By.cssSelector(".nav-line-1")).getText().contains("Hello");
		Assert.assertTrue(b,"Login Failed for Username "+uname+" with Password '"+password+"'");
		System.out.println("Username "+uname+" with Password '"+password+"' is Successfully Logged In");
		signOut();
	}

	public void signOut() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(hp.lnk_helloSignInYourOrders()).perform();
		Thread.sleep(2000);
		uli.lnk_signOut().click();
	}
	
	public void verifySignInPage() {
		currUrl = driver.getCurrentUrl();
		try {
			flag=currUrl.contains("ap/signin")?true:false;

		}catch(Exception e) {
			flag=false;
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(flag, "Unable to access Sign In Page");
	}

}





