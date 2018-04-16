package com.testing.XeroMavenWindows;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import junit.framework.Assert;

public class ModuleXero extends ReUsableMethodsXero {

	public static WebDriver driver;
	public static Properties prop;
	public static String status="true";
	
	public static void loadProperty()
	{
		try
		{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/utility/config.properties");
			prop.load(ip);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void SetExtendReport()
	{
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReports/NewReport.html");
	    extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Host Name", "Training");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Sindhuja");
		
		htmlReporter.config().setDocumentTitle("extent reports");
		htmlReporter.config().setReportName("xero functional testing reports");
	}
	
	public static void Lauch_Browser(String browser)
	{
		if(browser.equalsIgnoreCase("FF"))
		{
			String driverPath="./src/test/java/utility/geckodriver";
			System.setProperty("webdriver.gecko.driver", driverPath);
			
			driver=new FirefoxDriver();
			logger.log(Status.INFO,"Firefox browser is launched..");
			
		}
		if(browser.equalsIgnoreCase("Chrome"))
		{
			String driverPath="./src/test/java/utility/chromedriverwin.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			
			driver=new ChromeDriver();
			logger.log(Status.INFO,"Chrome browser is launched..");

			//System.out.println("Chrome browser is launched..");
		}
		if(browser.equalsIgnoreCase("Opera"))
		{
			String cur_dir = System.getProperty("user.dir");
			String driverPath = cur_dir + "/src/test/java/com/testing/utility/operadriver";
			
			System.setProperty("webdriver.opera.driver", driverPath);
			driver=new OperaDriver();
		
			logger.log(Status.INFO,"Opera browser is launched..");

			//System.out.println("Opera browser is launched..");
		}
	}
	
	public static void launchUrl()
	{
		String url=prop.getProperty("url");
		driver.get(url);
		
		logger.log(Status.INFO,"Xero application is launched..");
	}
	
	
	public static void loginToXero(String userName,String passWord)
	{
		
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		//enter UN in username field
		WebElement un=driver.findElement(By.xpath("//*[@id='email']"));
		enterText(un,userName,"UserName");
		
		//enter PWD in password field
		WebElement pwd=driver.findElement(By.xpath("//*[@id='password']"));
		enterText(pwd,passWord,"PassWord");
	    
	    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));

	    //click login button
	    WebElement login=(WebElement) driver.findElement(By.xpath("//*[@id='submitButton']"));
		clickEvent(login,"LoginButton");
	
	}
	
	public static void logOut() throws InterruptedException
	{
		// //*[@id='xero-nav']/div[2]/div[1]/div[2]/a
		
		// //*[@id='xero-nav']/div[2]/div[1]/div[2]/div/ul/li[3]/a
		
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		//click profile field
		WebElement profileLk=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[2]/a"));
		clickEvent(profileLk,"profileLink");
		
		Thread.sleep(4000);
		//click logout button
		WebElement logoutBt=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[2]/div/ul/li[3]/a"));
		clickEvent(logoutBt,"logoutButton");
		
	}
	
	
	public static void goToProfile() throws InterruptedException, AWTException
	{
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		//click profile field
		WebElement profileLk=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[2]/a"));
		clickEvent(profileLk,"profileLink");
		
		Thread.sleep(4000);
		//click profile button
		WebElement profileBt=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[2]/div/ul/li[1]/a"));
		clickEvent(profileBt,"profileButton");
		
		Thread.sleep(4000);
		WebElement uploadBt=driver.findElement(By.xpath("//*[@id='button-1041']"));
		clickEvent(uploadBt,"uploadImageButton");
		
		
		String img=prop.getProperty("imgpath");
		Thread.sleep(4000);
		
		WebElement imgPath=driver.findElement(By.xpath("//*[@id='filefield-1174-button']"));
		imgPath.click();
		//imgPath.sendKeys("/Users/sindhu/Documents/testingfiles/simplelock.jpg");
//*[@id='filefield-1216-button']
		
		//button .//*[@id='filefield-1174-button'] (types in bottom)
		
		Thread.sleep(4000);
		File file = new File(img);
		fileUploadFromComputer(file,"FileUpload");
		
		
		//WebElement imgPath=driver.findElement(By.xpath("//div[text()='No file selected']"));
		//WebElement imgPath=driver.findElement(By.xpath("//*[@id='display-1203']"));
		//imgPath.sendKeys("/Users/sindhu/Documents/testingfiles/simplelock.jpg");
		//enterText(imgPath,img,"ImagePath");
		Thread.sleep(4000);
		WebElement upload=driver.findElement(By.xpath("//*[@id='button-1178']"));
		clickEvent(upload,"uploadButton");
		
	}
	
	
	public static void addNewOrganizationTrial() throws InterruptedException
	{
	
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		//click my xezo menu
		WebElement myZeroMenu=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/h2"));
		clickEvent(myZeroMenu,"myZeroMenu");
		
		Thread.sleep(4000);
		//click xero button
		WebElement myZero=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/div/div/a"));
		clickEvent(myZero,"myZero");
		
		Thread.sleep(4000);
		//click addOrganization button
		//WebElement addOrg=driver.findElement(By.xpath("//*[@id='ext-gen1042']"));
		WebElement addOrg=driver.findElement(By.xpath("//*[contains(@href,'/!xkcD/Organisation/Setup')]"));
		clickEvent(addOrg,"addOrganization");
		
		String orgNewName=prop.getProperty("neworg_name");
		Thread.sleep(4000);
		WebElement orgName=driver.findElement(By.xpath("//*[@id='text-1022-inputEl']"));
		enterText(orgName,orgNewName,"OrganizationName");
		
		
		//Thread.sleep(4000);
	
		//WebElement timeZone=driver.findElement(By.xpath("//*[@id='ext-gen1100']"));
		//timeZone.click();
		
		//WebElement usTime=driver.findElement(By.xpath("//li[text()='(UTC-08:00) Pacific Time (US & Canada)’]"));
		//WebElement usTime=driver.findElement(By.xpath("//li[text()='(UTC-08:00) Pacific Time (US & Canada)']"));
		//*[@id='cmbTimeZone-boundlist-listEl']/ul/li[129]/span[2]
		//Actions action=new Actions(driver);
		
		//action.moveToElement(usTime).build().perform();
		/*
		WebElement timeZone=driver.findElement(By.xpath("//*[@id='cmbTimeZone-inputEl']"));
		timeZone.click();
		Thread.sleep(2000);
		timeZone.sendKeys("(UTC-08:00) Pacific Time (US & Canada)");
		//enterText(timeZone,"(UTC-08:00) Pacific Time (US & Canada)","timeZone");
		Thread.sleep(4000);
		Keyboard keyboard=((HasInputDevices)driver).getKeyboard();

		keyboard.pressKey(Keys.ENTER);
		// need to select country,timezone from dropdown
		*/
		Thread.sleep(4000);
		String orgDo=prop.getProperty("neworg_do");
		WebElement orgWork=driver.findElement(By.xpath("//*[@id='industrysearchcombofield-1025-inputEl']"));
		clickEvent(orgWork,"orgWork");
		enterText(orgWork,orgDo,"OrganizationDo");
		
		//keyboard.pressKey(Keys.ENTER);
		//*[@id='simplebutton-1035']
		
		Thread.sleep(4000);
		WebElement startTrial=driver.findElement(By.xpath("//*[@id='simplebutton-1035']"));
		clickEvent(startTrial,"startTrialButton");
		
	}
	
	public static void addNewOrganizationPaid() throws InterruptedException
	{
	
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		Thread.sleep(4000);
		//click my xezo menu
		WebElement myZeroMenu=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/h2"));
		clickEvent(myZeroMenu,"myZeroMenu");
		
		Thread.sleep(4000);
		//click xero button
		WebElement myZero=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/div/div/a"));
		clickEvent(myZero,"myZero");
		
		Thread.sleep(4000);
		//click addOrganization button
		WebElement addOrg=driver.findElement(By.xpath("//*[contains(@href,'/!xkcD/Organisation/Setup')]"));
		clickEvent(addOrg,"addOrganization");
		
		String orgNewName=prop.getProperty("neworg_name");
		Thread.sleep(4000);
		WebElement orgName=driver.findElement(By.xpath("//*[@id='text-1022-inputEl']"));
		enterText(orgName,orgNewName,"OrganizationName");
		
		
		// need to select country,timezone from dropdown
		Thread.sleep(4000);
		String orgDo=prop.getProperty("neworg_do");
		WebElement orgWork=driver.findElement(By.xpath("//*[@id='industrysearchcombofield-1025-inputEl']"));
		clickEvent(orgWork,"orgWork");
		enterText(orgWork,orgDo,"OrganizationDo");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='wizardHeader']")).click();
		//Keyboard keyboard=((HasInputDevices)driver).getKeyboard();

		//keyboard.pressKey(Keys.ENTER);
		
		
		//*[@id='simplebutton-1035']
		Thread.sleep(4000);
		WebElement paidTrial=driver.findElement(By.xpath("//*[@id='simplebutton-1036']"));
		clickEvent(paidTrial,"BuyNowButton");
		
		Thread.sleep(4000);
		
	}
	
	
	public static void addNewOrganizationQuickBooks() throws InterruptedException
	{

		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		//click my xezo menu
		WebElement myZeroMenu=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/h2"));
		clickEvent(myZeroMenu,"myZeroMenu");
		
		Thread.sleep(4000);
		//click xero button
		WebElement myZero=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/div/div/a"));
		clickEvent(myZero,"myZero");
		
		Thread.sleep(4000);
		//click addOrganization button
		WebElement addOrg=driver.findElement(By.xpath("//*[@id='ext-gen1043']"));
		clickEvent(addOrg,"addOrganization");
		
		String orgNewName=prop.getProperty("neworg_name");
		Thread.sleep(4000);
		WebElement orgName=driver.findElement(By.xpath("//*[@id='text-1022-inputEl']"));
		enterText(orgName,orgNewName,"OrganizationName");
		
		
		// need to select country,timezone from dropdown
		Thread.sleep(4000);
		String orgDo=prop.getProperty("neworg_do");
		WebElement orgWork=driver.findElement(By.xpath("//*[@id='industrysearchcombofield-1025-inputEl']"));
		clickEvent(orgWork,"orgWork");
		enterText(orgWork,orgDo,"OrganizationDo");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='wizardHeader']")).click();
		
		//*[@id='simplebutton-1035']
		//Thread.sleep(2000);
		WebElement quickBooksLk=driver.findElement(By.xpath("//*[@id='conversionLink']"));
		clickEvent(quickBooksLk,"quickBooksLink");
		
		WebElement convertCheckBx=driver.findElement(By.xpath("//*[@id='conversionCheckbox-inputEl']"));
		clickEvent(convertCheckBx,"convertCheckBox");
		
		WebElement continueBt=driver.findElement(By.xpath("//*[@id='simplebutton-1036']"));
		clickEvent(continueBt,"continueButton");
	
		
		Thread.sleep(4000);
		
	}
	
	public static void chooseStarterPlan() throws InterruptedException
	{
	
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		Thread.sleep(4000);
		//click starter radio button
		WebElement starterBt=driver.findElement(By.xpath("//*[@id='PRODUCTOPTION/ORG/SOLO']/div[1]/label"));
		clickEvent(starterBt,"starterButton");
		
		Thread.sleep(4000);
		//click starter radio button
		WebElement continueBillingBt=driver.findElement(By.xpath("//*[@id='frmMain']/div/div[2]/div/main/div[10]/button"));
		clickEvent(continueBillingBt,"continueBillingButton");
				
		String streetName=prop.getProperty("streetname");
		String cityName=prop.getProperty("cityname");
		String stateName=prop.getProperty("state");
		String zipcode=prop.getProperty("zipcode");
	
				
		WebElement street=driver.findElement(By.xpath("//*[@id='POAddress']"));
		enterText(street,streetName,"StreetName");
		
		WebElement city=driver.findElement(By.xpath("//*[@id='POCity']"));
		enterText(city,cityName,"cityName");
		
		WebElement state=driver.findElement(By.xpath("//*[@id='PORegionDropdown']"));
		selectDropDownValue(state,stateName,"stateName");
		
		WebElement zip=driver.findElement(By.xpath("//*[@id='POPostalCode']"));
		enterText(zip,zipcode,"zipCode");
		
		WebElement continuePayBt=driver.findElement(By.xpath("//*[@id='frmMain']/div/div/div/main/div[3]/div/div[2]/div/button"));
		clickEvent(continuePayBt,"continuePayButton");
			
		
	}
		
	public static void chooseStandardPlan() throws InterruptedException
	{
	
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		Thread.sleep(4000);
		//click standard radio button
		WebElement standardBt=driver.findElement(By.xpath("//*[@id='PRODUCTOPTION/ORG/STANDARD']/div[1]/label"));
		clickEvent(standardBt,"standardButton");
		
		Thread.sleep(4000);
		//click billing button
		WebElement continueBillingBt=driver.findElement(By.xpath("//*[@id='frmMain']/div/div[2]/div/main/div[10]/button"));
		clickEvent(continueBillingBt,"continueBillingButton");
				
		String streetName=prop.getProperty("streetname");
		String cityName=prop.getProperty("cityname");
		String stateName=prop.getProperty("state");
		String zipcode=prop.getProperty("zipcode");
	
				
		WebElement street=driver.findElement(By.xpath("//*[@id='POAddress']"));
		enterText(street,streetName,"StreetName");
		
		WebElement city=driver.findElement(By.xpath("//*[@id='POCity']"));
		enterText(city,cityName,"cityName");
		
		WebElement state=driver.findElement(By.xpath("//*[@id='PORegionDropdown']"));
		selectDropDownValue(state,stateName,"stateName");
		
		WebElement zip=driver.findElement(By.xpath("//*[@id='POPostalCode']"));
		enterText(zip,zipcode,"zipCode");
		
		WebElement continuePayBt=driver.findElement(By.xpath("//*[@id='frmMain']/div/div/div/main/div[3]/div/div[2]/div/button"));
		clickEvent(continuePayBt,"continuePayButton");
			
		
	}
		
	public static void choosePremiumPlan() throws InterruptedException
	{
	
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		Thread.sleep(4000);
		//click premium radio button
		WebElement premiumdBt=driver.findElement(By.xpath("//*[@id='PRODUCTOPTION/ORG/PRO']/div[1]/label"));
		clickEvent(premiumdBt,"premiumdButton");
		
		Thread.sleep(4000);
		//click billing button
		WebElement continueBillingBt=driver.findElement(By.xpath("//*[@id='frmMain']/div/div[2]/div/main/div[10]/button"));
		clickEvent(continueBillingBt,"continueBillingButton");
				
		String streetName=prop.getProperty("streetname");
		String cityName=prop.getProperty("cityname");
		String stateName=prop.getProperty("state");
		String zipcode=prop.getProperty("zipcode");
	
				
		WebElement street=driver.findElement(By.xpath("//*[@id='POAddress']"));
		enterText(street,streetName,"StreetName");
		
		WebElement city=driver.findElement(By.xpath("//*[@id='POCity']"));
		enterText(city,cityName,"cityName");
		
		WebElement state=driver.findElement(By.xpath("//*[@id='PORegionDropdown']"));
		selectDropDownValue(state,stateName,"stateName");
		
		WebElement zip=driver.findElement(By.xpath("//*[@id='POPostalCode']"));
		enterText(zip,zipcode,"zipCode");
		
		WebElement continuePayBt=driver.findElement(By.xpath("//*[@id='frmMain']/div/div/div/main/div[3]/div/div[2]/div/button"));
		clickEvent(continuePayBt,"continuePayButton");
			
		
	}
	
	public static void Close_Browser()
	{
		driver.quit();
		System.out.println("Browser closed..");
	}
	
	public static void forgotPassword(String userName) throws InterruptedException
	{
	
		String url=prop.getProperty("url");
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
		WebElement forgotPwdLk=driver.findElement(By.xpath("//*[@id='contentTop']/div[2]/div[1]/a"));
		clickEvent(forgotPwdLk,"ForgotPasswordLink");
		
		String actValue=driver.getTitle().trim();
		String expValue=prop.getProperty("exptitle_forgotpassword");
		
		Assert.assertEquals(expValue, actValue);
		logger.log(Status.INFO, "Pass: ForgotPassowrd Page is Displayed");

		
		WebElement un=driver.findElement(By.xpath("//*[@id='UserName']"));
		enterText(un,userName,"UserName");
		
		WebElement sendLk=driver.findElement(By.xpath("//*[@id='submitButton']/a"));
		clickEvent(sendLk,"SendLink");
	

	}
	
	
	public static void signUpToXDC(String firstName, String lastName, String email, String phoneNo, String country) throws InterruptedException
	{
		String url=prop.getProperty("mainurl");
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

		//free trail click html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a
		WebElement freeTrialLk=driver.findElement(By.xpath("html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
		clickEvent(freeTrialLk,"FreeTrialLink");
		
		//verify title
		
		// first name html/body/div[6]/main/div[1]/div/div/form/div[2]/label/input
		WebElement firstNm=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[2]/label/input"));
		enterText(firstNm,firstName,"FirstName");
		
		
		//last name html/body/div[6]/main/div[1]/div/div/form/div[3]/label/input
		WebElement lastNm=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[3]/label/input"));
		enterText(lastNm,lastName,"LastName");
		
		
		//email html/body/div[6]/main/div[1]/div/div/form/div[4]/label/input
		WebElement emailId=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[4]/label/input"));
		enterText(emailId,email,"Email");
		
		
		//phone number html/body/div[6]/main/div[1]/div/div/form/div[5]/label/input
		WebElement phone=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[5]/label/input"));
		enterText(phone,phoneNo,"PhoneNo");
	
		//country dd html/body/div[6]/main/div[1]/div/div/form/div[6]/label/span/select
		WebElement countryDd=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[6]/label/span/select"));
		selectDropDownValue(countryDd,country,"Country");
		
		/*
		int size = driver.findElements(By.tagName("iframe")).size();
		System.out.println("size"+size);
	

		for(int i=0;i<size;i++)
		{
			driver.switchTo().frame(i);
			System.out.println("frame"+i);
			int total=driver.findElements(By.xpath("//*[@class='recaptcha-checkbox-checkmark']")).size();
			System.out.println(total);
			driver.switchTo().defaultContent();
			
		}
		*/
		driver.switchTo().frame(0);
		Thread.sleep(6000);
		try
		{
			WebElement checkbxRobot=driver.findElement(By.xpath("//*[@class='recaptcha-checkbox-checkmark']"));		
			selectCheckBox(checkbxRobot,"IamnotRobotCheckbocx");
		  	System.out.println("Pass:IamnotRobotCheckbocx is clicked");
	    }
	    catch(org.openqa.selenium.NoSuchElementException e)
	    {
			System.out.println("Fail: IamnotRobotCheckbocx is not clicked");

	    }
		// robot checkbox .//*[@id='recaptcha-anchor']/div[5]
		

		Thread.sleep(6000);
		driver.switchTo().defaultContent();

		
		//agree terms checkbox html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/input
		
		WebElement checkbxAgree=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/input"));
		selectCheckBox(checkbxAgree,"checkBoxAgree");
		
	}
	
	
	public static void signUpToXDCWithoutInput() throws InterruptedException
	{
		String url=prop.getProperty("mainurl");
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

		//free trail click html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a
		WebElement freeTrialLk=driver.findElement(By.xpath(" html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
		clickEvent(freeTrialLk,"FreeTrialLink");
		
		Thread.sleep(4000);
		WebElement getStartedBt=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[9]/span/button"));
		clickEvent(getStartedBt,"GetStartedButton");
		
	}
	
	public static void signUpToXDCTermPrivacyLink() throws InterruptedException
	{
		String url=prop.getProperty("mainurl");
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

		//free trail click html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a
		WebElement freeTrialLk=driver.findElement(By.xpath(" html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
		clickEvent(freeTrialLk,"FreeTrialLink");
		
		Thread.sleep(4000);
		
		WebElement termsLk=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[1]"));
		clickEvent(termsLk,"TermsOfUseLink");
		
		Thread.sleep(4000);
		
		String oldwindow=driver.getWindowHandle();
		 
	    Set<String> getAllWindows=driver.getWindowHandles();
	    String[] getWindow=getAllWindows.toArray(new String[getAllWindows.size()]);
	
	    driver.switchTo().window(getWindow[1]);
		
		String actValue=driver.getTitle().trim();
		System.out.println("terms"+actValue);
				
		String expValue=prop.getProperty("exptitle_terms");
		
		Assert.assertEquals(expValue, actValue);
		logger.log(Status.INFO, "Pass: Terms Page is Displayed");
		
		driver.switchTo().window(oldwindow);
				
		Thread.sleep(2000);
	    WebElement privacyLk=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[2]"));
		clickEvent(privacyLk,"PrivacylLink");
		
		Thread.sleep(4000);
		Set<String> getAllWindows2=driver.getWindowHandles();
	    String[] getWindow2=getAllWindows2.toArray(new String[getAllWindows.size()]);
	
	    driver.switchTo().window(getWindow2[1]);
		
	    Thread.sleep(2000);
		String actValue2=driver.getTitle().trim();
		System.out.println("privacy"+actValue2);
		
		String expValuePrivacy=prop.getProperty("exptitle_privacy");
		
		Assert.assertEquals(expValuePrivacy, actValue2);
		logger.log(Status.INFO, "Pass: Privacy Page is Displayed");
		
	}
	
	public static void signUpToXDCOfferLink() throws InterruptedException
	{
		String url=prop.getProperty("mainurl");
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

		//free trail click html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a
		WebElement freeTrialLk=driver.findElement(By.xpath(" html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
		clickEvent(freeTrialLk,"FreeTrialLink");
		
		Thread.sleep(4000);
		
		WebElement termsLk=driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[3]"));
		clickEvent(termsLk,"OffersLink");
		
		Thread.sleep(4000);
		
		String oldwindow=driver.getWindowHandle();
		 
	    Set<String> getAllWindows=driver.getWindowHandles();
	    String[] getWindow=getAllWindows.toArray(new String[getAllWindows.size()]);
	
	    driver.switchTo().window(getWindow[1]);
		
		String actValue=driver.getTitle().trim();
		System.out.println("offers"+actValue);
				
		String expValue=prop.getProperty("exptitle_offers");
		
		Assert.assertEquals(expValue, actValue);
		logger.log(Status.INFO, "Pass: Terms Page is Displayed");
		
	}
	
	public static void goToPurchases()
	{
		WebElement accounts=driver.findElement(By.xpath("//*[@id='Accounts']"));
		clickEvent(accounts,"AccountsTab");
		
		
		WebElement purchase=driver.findElement(By.linkText("Purchases"));
		clickEvent(purchase,"purchaseOption");

	}
	
	
	public static void signUpToXDCAccountantLk() throws InterruptedException
	{
		String url=prop.getProperty("mainurl");
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

		//free trail click html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a
		WebElement freeTrialLk=driver.findElement(By.xpath(" html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
		clickEvent(freeTrialLk,"FreeTrialLink");
		
		Thread.sleep(4000);
		
		WebElement acctLk=driver.findElement(By.xpath("html/body/div[6]/main/div[2]/div/div/div/p/a"));
		clickEvent(acctLk,"AccountantAndBookKeeperLink");
		
		Thread.sleep(4000);
		
		String actValue=driver.getTitle().trim();
		System.out.println("accountant"+actValue);
				
		String expValue=prop.getProperty("exptitle_accountant");
		
		Assert.assertEquals(expValue, actValue);
		logger.log(Status.INFO, "Pass: Terms Page is Displayed");
		
	}
	
	public static void checkAllTabs() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

	
		WebElement dashBoard=driver.findElement(By.xpath("//*[@id='Dashboard']"));
		clickEvent(dashBoard,"DashBoardTab");
		
		//verify dashBoard
		WebElement dashBoardEdit=driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/a"));
		String expValueDashboard=prop.getProperty("expvalue_dashboard");
		validateTextContext(dashBoardEdit,expValueDashboard,"dashBoardEdit");
		
		WebElement accounts=driver.findElement(By.xpath("//*[@id='Accounts']"));
		clickEvent(accounts,"AccountsTab");
		
		//verify Accounts
		 try{
			WebElement accountsTab=driver.findElement(By.linkText("Sales"));
	        System.out.println("isElementFound Accounts DD: true");

	        }
		 catch(org.openqa.selenium.NoSuchElementException e){
	            System.out.println("isElementFound Accounts DD: false");
	        }		
		
		WebElement reports=driver.findElement(By.xpath("//*[@id='Reports']"));
		clickEvent(reports,"ReportsTab");
		
		
		//verify reports
		try{
			WebElement reportsTab=driver.findElement(By.linkText("Balance Sheet"));
	        System.out.println("isElementFound reportsTab: true");

	        }
		 catch(org.openqa.selenium.NoSuchElementException e){
	            System.out.println("isElementFound reportsTab: false :");
	        }
		
		WebElement contacts=driver.findElement(By.xpath("//*[@id='Contacts']"));
		clickEvent(contacts,"ContactsTab");
		
		//verify contacts
		try{
			WebElement contactsTab=driver.findElement(By.linkText("Customers"));
	        System.out.println("isElementFound contactsTab: true");

	        }
		 catch(org.openqa.selenium.NoSuchElementException e){
	            System.out.println("isElementFound contactsTab: false :");
	        }
		
		WebElement settings=driver.findElement(By.xpath("//*[@id='Settings']"));
		clickEvent(settings,"SettingsTab");
		
		//verify settings
		
		try{
			WebElement settingsTab=driver.findElement(By.linkText("Payroll Settings"));
	        System.out.println("isElementFound settingsTab: true");

	        }
		 catch(org.openqa.selenium.NoSuchElementException e){
	            System.out.println("isElementFound settingsTab: false :");
	        }
		
		
		// .//*[@id='quicklaunchTab']
		WebElement newTab=driver.findElement(By.xpath("//*[@id='quicklaunchTab']"));
		clickEvent(newTab,"NewTab");
		
		//verify settings
		
		try{
			WebElement newTabValue=driver.findElement(By.linkText("Invoice"));
	        System.out.println("isElementFound newTabValue: true");

	        }
		 catch(org.openqa.selenium.NoSuchElementException e){
	            System.out.println("isElementFound newTabValue: false :");
	        }
		
		
		
		//files .//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[2]/a
		//.//*[@id='page_title']/div/h1[1]
		
		WebElement files=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[2]/a"));
		clickEvent(files,"filesTab");
		
		//verify files
		WebElement filesTitle=driver.findElement(By.xpath("//*[@id='page_title']/div/h1[1]"));
		String expFilesTitle=prop.getProperty("expfiles_title");
		Assert.assertEquals(expFilesTitle, filesTitle.getText());
		
		
		//notification .//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[3]/a
		//*[@id='NOTIFICATIONS']/button
		
		Thread.sleep(4000);
		WebElement notifications=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[3]/a"));
		clickEvent(notifications,"NotificationsTab");
		
		/*
		//verify notifications
		Thread.sleep(4000);
		int size = driver.findElements(By.tagName("iframe")).size();
		 List<WebElement> eles=driver.findElements(By.tagName("iframe"));
		 for(WebElement ele:eles)
			{
			 System.out.println(ele.getAttribute("id"));
			}
		System.out.println("size"+size);
	

		for(int i=0;i<size;i++)
		{
			driver.switchTo().frame(i);
			System.out.println("frame"+i);
			int total=driver.findElements(By.xpath("//*[@class='addon-panel__header']/ul/li[1]/button")).size();
			System.out.println(total);
			driver.switchTo().defaultContent();
			
		}
		*/
		//verify notifications
		//driver.switchTo().frame("post_office_modal_frame");
		try{
			//WebElement newTabValue=driver.findElement(By.xpath("//*[@id='NOTIFICATIONS']/button"));
	        System.out.println("isElementFound NotificationsTab: true");

	        }
		 catch(org.openqa.selenium.NoSuchElementException e){
	            System.out.println("isElementFound NotificationsTab: false :");
	        }
		
		//driver.switchTo().defaultContent();
		
		//search .//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[4]/a
		//*[@id='dropdown-panel']/div[1]/div[1]
		WebElement searchTab=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[4]/a"));
		clickEvent(searchTab,"SearchTab");
		
		//verify settings
		Thread.sleep(4000);
		int size2 = driver.findElements(By.tagName("iframe")).size();
		System.out.println("size2"+size2);
	
/*
		for(int i=0;i<size2;i++)
		{
			driver.switchTo().frame(i);
			System.out.println("frame"+i);
			int total=driver.findElements(By.xpath("//*[@id='dropdown-panel']/div[1]/div[1]")).size();
			System.out.println(total);
			driver.switchTo().defaultContent();
			
		}
		*/
		driver.switchTo().frame(3);
		try{
			WebElement SearchTabField=driver.findElement(By.xpath("//*[@id='dropdown-panel']/div[1]/div[1]"));
	        System.out.println("isElementFound SearchTabField: true");

	        }
		 catch(org.openqa.selenium.NoSuchElementException e){
	            System.out.println("isElementFound SearchTabField: false :");
	        }
		
		driver.switchTo().defaultContent();
		//help .//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[5]/a
		
		WebElement helpsTab=driver.findElement(By.xpath("//*[@id='xero-nav']/div[2]/div[2]/div[2]/ul/li[5]/a"));
		clickEvent(helpsTab,"HelpsTab");
		
		//verify help
		Thread.sleep(4000);
		try{
			WebElement helpsTabValue=driver.findElement(By.xpath("//*[@id='rt']/div[1]/h3"));
	        System.out.println("isElementFound helpsTabValue: true");

	        }
		 catch(org.openqa.selenium.NoSuchElementException e){
	            System.out.println("isElementFound helpsTabValue: false :");
	        }
		
		
	}
	
}
