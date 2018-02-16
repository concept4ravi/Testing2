package testng_Framework;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import supportingJavaFiles.BaseClass;

public class SignInTest extends BaseClass{
	public String Pwindow ;
	static int i=1;
	Boolean flag;
	@Test
	public void HomePage() {
		
		driver.get(baseUrl);
//		Reporter.log("BaseUrl Open",true);
		try {

			flag=hp.logo_Amazon().isDisplayed();

		}catch(Exception e) {
			screenshoot();
			flag=false;
		}
		Assert.assertTrue(flag, "Unable to access "+baseUrl);
		
	}
	@Test(dependsOnMethods="HomePage")
	public void openSignInPage() throws InterruptedException {
		hp.lnk_helloSignInYourOrders().click();
//		Reporter.log("SignIn Clicked",true);
		verifySignInPage();
	}
	@Test(dataProvider = "Login",dependsOnMethods="openSignInPage")
	public void signIn(String uname,String password) throws InterruptedException {
		verifySignInPage();
		try {
			flag=lp.txt_emailOrMobile().isDisplayed();

		}catch(Exception e) {
			screenshoot();
			flag=false;
			System.out.println(e.getMessage());
		}
		Assert.assertTrue(flag, "Email id field not available");
		lp.txt_emailOrMobile().click();
		lp.txt_emailOrMobile().clear();
		lp.txt_emailOrMobile().sendKeys(uname);
//		Reporter.log("Email Entered",true);
		lp.btn_continue().click();
//		Reporter.log("Email Continue button clicked",true);
		try {
			flag=lp.txt_password().isDisplayed();

		}catch(Exception e) {
			screenshoot();
			flag=false;
			System.out.println(e.getMessage());
		}
		
		Assert.assertTrue(flag, "Password field not available");
		lp.txt_password().click();
		lp.txt_password().clear();
		lp.txt_password().sendKeys(password);
//		Reporter.log("Password Entered",true);
		lp.btn_login().click();
//		Reporter.log("Login Button Clicked",true);
		Thread.sleep(1000);
		WebElement link = driver.findElement(By.id("nav-link-yourAccount"));
		boolean b = link.findElement(By.cssSelector(".nav-line-1")).getText().contains("Hello");
		Assert.assertTrue(b,"Login Failed for Username "+uname+" with Password '"+password+"'");
//		Reporter.log("Username "+uname+" with Password '"+password+"' is Successfully Logged In",true);
		signOut();
//		Reporter.log("SignOut Success",true);
		
	}

	public void signOut() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(hp.lnk_helloSignInYourOrders()).perform();
		Thread.sleep(2000);
		try {
			flag=uli.lnk_signOut().isDisplayed();
		}catch(Exception e) {
			flag=false;
		}
		Assert.assertTrue(flag,"Unable to LogOut");
		uli.lnk_signOut().click();
	}

	public void verifySignInPage() throws InterruptedException {
		Thread.sleep(3000);
		currUrl = driver.getCurrentUrl();
		flag=currUrl.contains("ap/signin")?true:false;
		if(!flag) {
			screenshoot();
			Assert.assertTrue(flag, "Unable to access Sign In Page");
		}
	}
	
	public void screenshoot() {
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		 // now copy the  screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, new File("/home/intern/git/Testing2/AmazonAlt/screenshot/error"+i+".png"));
		++i;
		}
		 
		catch (IOException e)
		 {
		  System.out.println(e.getMessage());
		 
		 }
	}

}



