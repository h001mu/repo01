package GenricUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.crm.comcast.objectrepository.HomePage;
import com.crm.comcast.objectrepository.LoginPage;

import bsh.util.ClassBrowser;

public class BaseClass {
	

	public WebDriver driver;

	public FileUtility fUtil=new FileUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	public JavaUtility jUtil=new JavaUtility();
	
	@BeforeSuite
	public void configBs() {
		//connect to db
		System.out.println("connect to dB");
		
	}
	@BeforeClass
	
	public void configBS() throws Throwable {
	String browser = fUtil.getpropertyFileData("browser");
	String url = fUtil.getpropertyFileData("url");

	if(browser.equals("Chrome")) {
		System.setProperty(IPathConstants.CHROME_KEY,IPathConstants.CHROME_PATH);
		driver = new ChromeDriver();
		}else if (browser.equals("firefox")) {
			System.setProperty(IPathConstants.FIREFOX_KEY,IPathConstants.FIREFOX_PATH);
		}else {
			System.out.println("browser is not supported");
	}
	driver.manage().window().maximize();
	wUtil.waitForPageLoad(driver);
	driver.get("url");
	}
	@BeforeMethod
	public void configbm() throws Throwable {
		fUtil.getpropertyFileData("username");
		fUtil.getpropertyFileData("password");
		LoginPage login = new LoginPage(driver);
		login.loginTOApplication("username","password");
		
	}
	
	
	@AfterMethod
	public void configAm() {
		HomePage homepage = new HomePage(driver);
		homepage.signout();
	}
		
		@AfterClass
		public void configAC() {
			driver.quit();
		}
			
			@AfterSuite
			public void configAS() {
				System.out.println("close the database connection");
			}
		
		
		
}

	
		
	
		
	

