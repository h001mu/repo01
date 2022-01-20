package contactTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.crm.comcast.objectrepository.ContactInformationPage;
import com.crm.comcast.objectrepository.Contactspage;
import com.crm.comcast.objectrepository.CreatingNewContactPage;
import com.crm.comcast.objectrepository.HomePage;
import com.crm.comcast.objectrepository.LoginPage;

import GenricUtilities.BaseClass;
import GenricUtilities.ExcelUtility;
import GenricUtilities.FileUtility;
import GenricUtilities.IPathConstants;
import GenricUtilities.JavaUtility;
import GenricUtilities.WebDriverUtility;

public class CreateContactTC_09Test extends BaseClass {


     @Test
     
     public void createContact() throws Throwable {
		
		FileUtility fUtil = new FileUtility();
		JavaUtility jUtil = new JavaUtility();
		ExcelUtility eUtil = new ExcelUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
	//get the data from property file	
	String url = fUtil.getpropertyFileData("url");
	String username = fUtil.getpropertyFileData("username");
	String password = fUtil.getpropertyFileData("password");
	String browserName = fUtil.getpropertyFileData("browser");
	//get the data from excel sheet
	String ExpectedcontactLastName = eUtil.getStringCellData("Sheet1", 1, 2);
	ExpectedcontactLastName = ExpectedcontactLastName+"randomNumber";
		//generate random number
		int randomNumber=jUtil.getRandomNumber();
		//how to use browser value and launch the browser
		WebDriver driver=null;
		if(browserName.equalsIgnoreCase("chrome")) {
		
		System.setProperty(IPathConstants.CHROME_KEY,IPathConstants.CHROME_PATH);
		 driver = new ChromeDriver();
		}else if (browserName.equals("firefox")) {
			System.setProperty(IPathConstants.FIREFOX_KEY,IPathConstants.FIREFOX_PATH);
		}else {
			System.out.println("browser is not supported");
		}
		driver.manage().window();
		
		wUtil.waitForPageLoad(driver);
		driver.get(url);
					//login to application
		LoginPage login = new LoginPage(driver);
				login.loginTOApplication(username, password);
		//createContacts
		HomePage homepage = new HomePage(driver);
				homepage.contactlink();
	 Contactspage conpage = new Contactspage(driver) ;
				conpage.createConImg();
				
				CreatingNewContactPage createcontactpage = new CreatingNewContactPage(driver);
				createcontactpage.CreateNewContact();
				
				ContactInformationPage contactinfo = new ContactInformationPage(driver);
				String actualContact = contactinfo.ConInformatext();
			
				
				//contact name verification
				if(ExpectedcontactLastName.contains(actualContact)) {
					System.out.println("contact name is  verified");
				}else
				{
					System.out.println("contact name not be verified ");
				}
				
	homepage.contactlink();

	}
}


