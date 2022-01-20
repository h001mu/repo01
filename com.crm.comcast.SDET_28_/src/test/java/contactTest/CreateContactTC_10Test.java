package contactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.comcast.objectrepository.ContactInformationPage;
import com.crm.comcast.objectrepository.Contactspage;
import com.crm.comcast.objectrepository.CreatingNewContactPage;
import com.crm.comcast.objectrepository.HomePage;
import com.crm.comcast.objectrepository.LoginPage;

import GenricUtilities.ExcelUtility;
import GenricUtilities.FileUtility;
import GenricUtilities.IPathConstants;
import GenricUtilities.JavaUtility;
import GenricUtilities.WebDriverUtility;

public class CreateContactTC_10Test {

	public static void main(String[] args) throws Throwable {
		FileUtility fUtil = new FileUtility();
		JavaUtility jUtil = new JavaUtility();
		ExcelUtility eUtil = new ExcelUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		//get the data from the property file
		String url = fUtil.getpropertyFileData("url");
		String username = fUtil.getpropertyFileData("username");
		String password = fUtil.getpropertyFileData("password");
		String browserName = fUtil.getpropertyFileData("browser");
		int randomNumber = jUtil.getRandomNumber();
		//get the data from the excel sheet
		String ExpectedcontactLastName01 = eUtil.getStringCellData("sheet1",1,2);
		ExpectedcontactLastName01=ExpectedcontactLastName01+randomNumber;
		//launch the browser
		WebDriver driver=null;
		if(browserName.equals("Chrome")) {
				System.setProperty(IPathConstants.CHROME_KEY, IPathConstants.CHROME_PATH);
					driver=new ChromeDriver();
		}else if(browserName.equals("Firefox")) {
			System.setProperty(IPathConstants.FIREFOX_KEY, IPathConstants.FIREFOX_PATH);
		}else {
			System.out.println("browser is not supported");
		}
			
		
		driver.manage().window().maximize();
		wUtil.waitForPageLoad(driver);
		driver.get(url);
		//login to application
		LoginPage login = new LoginPage(driver);
		login.loginTOApplication(username, password);
		//click on contacts module
		HomePage homepage = new HomePage(driver);
		homepage.contactlink();
		//click on create Contacts module
		Contactspage conpage = new Contactspage(driver);
		conpage.createConImg();
		//click on createNewContact
		CreatingNewContactPage createcontactPage = new CreatingNewContactPage(driver);
		createcontactPage.CreateNewContact();
		//click on contactinfornmation page
		ContactInformationPage contactinformationpage = new ContactInformationPage(driver);
		String actualcontact = contactinformationpage.ConInformatext();
		//contact name verification
		if(ExpectedcontactLastName01.contains(actualcontact)) {
			System.out.println("contcact name is verified");
		}else{
			System.out.println("contact name is not verified");
		}
		
		homepage.contactlink();

		
	

	}

}
