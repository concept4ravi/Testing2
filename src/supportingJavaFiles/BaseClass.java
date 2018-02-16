package supportingJavaFiles;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.UserLoggedInPage;

public class BaseClass {
	protected WebDriver driver;
	protected HomePage hp;
	protected LoginPage lp;
	protected DriverFactory dFactory = new DriverFactory();
	protected UserLoggedInPage uli;
	protected final String baseUrl = "https://www.amazon.in";
	protected FileInputStream xlsxFile = null;
	protected Workbook wb = null;
	protected String loginTestDataFile = "/home/intern/git/Testing2/AmazonAlt/src/LoginTestData.xlsx";
	protected String Pwindow ;
	protected String sheetName = "Sheet1";
	
	protected String currUrl = null;
	@DataProvider(name = "Login")
	public Object[][] credentials() {
		try {
			xlsxFile = new FileInputStream(loginTestDataFile);
			wb = new XSSFWorkbook(xlsxFile);
		}catch(FileNotFoundException e) {
			System.out.println("File Not Found");
		}
		catch(IOException e) {
			System.out.println("Input Output");
		}


		XSSFSheet sheet = (XSSFSheet) wb.getSheet(sheetName);

		int i=0,j,maxRow=sheet.getLastRowNum();

		XSSFRow row;
		XSSFCell cell;
		Iterator<org.apache.poi.ss.usermodel.Row> rows = sheet.rowIterator();

		Object[][] obj = new Object[maxRow][2];
		rows = sheet.rowIterator();
		rows.next();
		while(rows.hasNext()) {
			j=0;
			row = (XSSFRow) rows.next();
			Iterator<org.apache.poi.ss.usermodel.Cell> cells = row.cellIterator();
			while(cells.hasNext()) {
				cell = (XSSFCell) cells.next();
				obj[i][j++] = cell.getStringCellValue();

			}
			++i;
		}

		try {
			xlsxFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;

	} 

	@BeforeClass
	@Parameters({"browser"})
	public void setBrowser(String browser) {
		driver = dFactory.getBrowser(browser);
		Reporter.log("Browser Opened",true);
		
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		uli = new UserLoggedInPage(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Reporter.log("Implicit Wait Set",true);
	}
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
		Reporter.log("Browser Terminated");
		
	}

}
