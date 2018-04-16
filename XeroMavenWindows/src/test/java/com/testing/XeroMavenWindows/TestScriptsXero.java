package com.testing.XeroMavenWindows;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import junit.framework.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestScriptsXero extends ModuleXero{

	
	@BeforeTest
	public void BeforeTest()
	{
		loadProperty();
		SetExtendReport();
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=true)
	public void NavigatetoXero_ID01A(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			if(testCase.equals("NavigatetoXero_ID01A") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("NavigatetoXero_ID01A "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
	
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
				
				launchUrl();
				
				Thread.sleep(2000);
				
				loginToXero(userName,passWord);
	
				Thread.sleep(6000);
				String actValue=driver.getTitle().trim();
				String expValue=prop.getProperty("exptitle_home");
				
				Assert.assertTrue(actValue.contains(expValue));
				
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase NavigatetoXero_ID01A Passed", ExtentColor.GREEN));
	
				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void IncorrectPassword_ID01B(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("IncorrectPassword_ID01B")  && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("IncorrectPassword_ID01B "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
	
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("invalidpassword");
				
				launchUrl();
				Thread.sleep(2000);
				
				loginToXero(userName,passWord);
	
				String actValue=driver.findElement(By.xpath(".//*[@id='contentTop']/div[2]/div[1]/div[2]/p")).getText();
				String expValue=prop.getProperty("exp_error");
				
				Assert.assertEquals(expValue, actValue);
				
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase IncorrectPassword_ID01B Passed", ExtentColor.GREEN));
	
				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void IncorrectEmail_ID01C(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("IncorrectEmail_ID01C") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("IncorrectEmail_ID01C "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

				String userName=prop.getProperty("invalidusername");
				String passWord=prop.getProperty("password");
			
				launchUrl();
				Thread.sleep(2000);
			
				loginToXero(userName,passWord);

				String actValue=driver.findElement(By.xpath(".//*[@id='contentTop']/div[2]/div[1]/div[2]/p")).getText();
				String expValue=prop.getProperty("exp_error");
			
				Assert.assertEquals(expValue, actValue);
			
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase IncorrectEmail_ID01C Passed", ExtentColor.GREEN));

				Close_Browser();
			}
		}
	}
	
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void NavigateToXero_ID01D(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("NavigateToXero_ID01D") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("NavigateToXero_ID01D "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
				String userName=prop.getProperty("username");
			
				forgotPassword(userName);
				Thread.sleep(6000);
				String actValue=driver.findElement(By.xpath(".//*[@id='contentTop']/div/p[1]")).getText();
				actValue=actValue.replaceAll(System.lineSeparator(), "");
				System.out.println("actvalue"+actValue);
				String expValue=prop.getProperty("exp_message_forgotpassword");
			
				Assert.assertEquals(expValue, actValue);
			
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase NavigateToXero_ID01D Passed", ExtentColor.GREEN));
				
				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void signUpToXDC_ID02A(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("signUpToXDC_ID02A") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("signUpToXDC_ID02A "+browserType);

				Lauch_Browser(browserType);
			
			driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			String firstName,lastName,email,phoneNo,country;
			firstName=prop.getProperty("firstname");
			lastName=prop.getProperty("lastname");

			email=prop.getProperty("email");
			
			phoneNo=prop.getProperty("phone");
			
			country=prop.getProperty("country");


			signUpToXDC(firstName,lastName,email,phoneNo,country);
			
			Thread.sleep(6000);
			try
			{
			WebElement signUpSuccess=driver.findElement(By.xpath("html/body/main/div/div/div/div[1]/div/div/a"));
				  	System.out.println("Pass: signUpSuccess Window is opened");

		    }
		    catch(org.openqa.selenium.NoSuchElementException e)
		    {
				System.out.println("Fail: signUpSuccess Window is not opened");

		    }
			
			
			logger.log(Status.PASS, MarkupHelper.createLabel("testcase signUpToXDC_ID02A Passed", ExtentColor.GREEN));
			
			Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void signUpToXDC_ID02B(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("signUpToXDC_ID02B") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("signUpToXDC_ID02B "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
				signUpToXDCWithoutInput();
				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void signUpToXDC_ID02C(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("signUpToXDC_ID02B") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("signUpToXDC_ID02B "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
				signUpToXDCTermPrivacyLink();
			
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase signUpToXDC_ID02C Passed", ExtentColor.GREEN));

				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void signUpToXDC_ID02D(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("signUpToXDC_ID02D") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("signUpToXDC_ID02D "+browserType);

				Lauch_Browser(browserType);
			
			driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
			signUpToXDCOfferLink();
			
			
			logger.log(Status.PASS, MarkupHelper.createLabel("testcase signUpToXDC_ID02D Passed", ExtentColor.GREEN));

			Close_Browser();
			}
		}
	}
	
	

	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void signUpToXDC_ID02E(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("signUpToXDC_ID02E") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("signUpToXDC_ID02E "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
				signUpToXDCAccountantLk();
			
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase signUpToXDC_ID02E Passed", ExtentColor.GREEN));

				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void testAllTabsPage_ID03A(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("testAllTabsPage_ID03A") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("testAllTabsPage_ID03A "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
				String url=prop.getProperty("mainurl");
				driver.get(url);
			
				WebElement loginBt=driver.findElement(By.xpath("html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[2]/a"));
				clickEvent(loginBt,"LoginButton");
			
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
			
				loginToXero(userName,passWord);
			
				checkAllTabs();
			
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase testAllTabsPage_ID03A Passed", ExtentColor.GREEN));

				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void logoutFuctionality_ID04A(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("logoutFuctionality_ID04A") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("logoutFuctionality_ID04A "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
				String url=prop.getProperty("mainurl");
				driver.get(url);
			
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
			
				launchUrl();
				loginToXero(userName,passWord);
			
				//select logout 
				logOut();
				//verify logout
				Thread.sleep(6000);
				String actValue=driver.getTitle().trim();
				String expValue=prop.getProperty("exptitle_login");
				
				Assert.assertTrue(actValue.contains(expValue));
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase logoutFuctionality_ID04A Passed", ExtentColor.GREEN));

				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void uploadProfileImage_ID06A(String testCase,String browserType,String flag) throws InterruptedException, AWTException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("uploadProfileImage_ID06A") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("uploadProfileImage_ID06A "+browserType);
			
				Lauch_Browser(browserType);
				
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
			
				launchUrl();
			
				loginToXero(userName,passWord);
			
				goToProfile();
			
				//upload image
			
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase uploadProfileImage_ID06A Passed", ExtentColor.GREEN));

				Close_Browser();
			}
		}
	}
	

	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void addOrganizationTrial_ID08A(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("addOrganizationTrial_ID08A") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("addOrganizationTrial_ID08A "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
			
				launchUrl();
				loginToXero(userName,passWord);
			
			
				addNewOrganizationTrial();
			
				Thread.sleep(4000);
				String actValue=driver.findElement(By.xpath("//*[@id='root']/div/div/header/div/h1")).getText().trim();
				String expValue=prop.getProperty("neworg_name");
				Assert.assertEquals(expValue, actValue);
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase addOrganizationTrial_ID08A Passed", ExtentColor.GREEN));

				Close_Browser();
			}
		}
	}
	
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void addOrganizationPaid_ID08B(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("addOrganizationPaid_ID08B") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("addOrganizationPaid_ID08B "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
			
				launchUrl();
				loginToXero(userName,passWord);
			
			
				addNewOrganizationPaid();
			
				Thread.sleep(4000);
				System.out.println(driver.getTitle().trim());
				String actValue=driver.getTitle().trim();
				String expValue=prop.getProperty("expbilling_title");
				Assert.assertTrue(actValue.contains(expValue));
			
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase addOrganizationPaid_ID08B Passed", ExtentColor.GREEN));

				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void addOrganizationStarterPlan_ID08C(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("addOrganizationPaid_ID08B") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("addOrganizationPaid_ID08B "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
				
				launchUrl();
				loginToXero(userName,passWord);
				
				
				addNewOrganizationPaid();
				
				chooseStarterPlan();
				
				Thread.sleep(4000);
				System.out.println(driver.getTitle().trim());
				String actValue=driver.getTitle().trim();
				String expValue=prop.getProperty("exppayment_title");
				Assert.assertTrue(actValue.contains(expValue));
			
				
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase addOrganizationPaid_ID08B Passed", ExtentColor.GREEN));
	
				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void addOrganizationStandardPlan_ID08D(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("addOrganizationStandardPlan_ID08D") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("addOrganizationStandardPlan_ID08D "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
				
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
				
				launchUrl();
				loginToXero(userName,passWord);
				
				
				addNewOrganizationPaid();
				
				chooseStandardPlan();
				
				Thread.sleep(4000);
				System.out.println(driver.getTitle().trim());
				String actValue=driver.getTitle().trim();
				String expValue=prop.getProperty("exppayment_title");
				Assert.assertTrue(actValue.contains(expValue));
			
				
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase addOrganizationStandardPlan_ID08D Passed", ExtentColor.GREEN));
	
				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void addOrganizationPremiumPlan_ID08E(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("addOrganizationPremiumPlan_ID08E") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("addOrganizationPremiumPlan_ID08E "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
				
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
				
				launchUrl();
				loginToXero(userName,passWord);
				
				
				addNewOrganizationPaid();
				
				choosePremiumPlan();
				
				Thread.sleep(4000);
				System.out.println(driver.getTitle().trim());
				String actValue=driver.getTitle().trim();
				String expValue=prop.getProperty("exppayment_title");
				Assert.assertTrue(actValue.contains(expValue));
			
				
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase addOrganizationPremiumPlan_ID08E Passed", ExtentColor.GREEN));
	
				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void addOrganizationQuickBooks_ID08F(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("addOrganizationQuickBooks_ID08F") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("addOrganizationQuickBooks_ID08F "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
				
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
				
				launchUrl();
				loginToXero(userName,passWord);
				
				
				addNewOrganizationQuickBooks();
				
				Thread.sleep(4000);
				//System.out.println(driver.findElement(By.xpath("//*[@id='tbtext-1045']")).getText());
				String actValue=driver.findElement(By.xpath("//*[@id='tbtext-1045']")).getText();
				String expValue=prop.getProperty("expquickbookfile_header");
				Assert.assertTrue(actValue.contains(expValue));
			
				//*[@id='tbtext-1045']
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase addOrganizationQuickBooks_ID08F Passed", ExtentColor.GREEN));
	
				Close_Browser();
			}
		}
	}
	
	@Test(dataProvider="getDataFromExcel",enabled=false)
	public void checkBillingInfo_ID10(String testCase,String browserType,String flag) throws InterruptedException
	{
		if(testCase!=null && browserType!=null)
		{
			
			if(testCase.equals("addOrganizationStandardPlan_ID08D") && flag.equalsIgnoreCase("Y"))
			{
				logger=extent.createTest("addOrganizationStandardPlan_ID08D "+browserType);

				Lauch_Browser(browserType);
			
				driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
				
				String userName=prop.getProperty("username");
				String passWord=prop.getProperty("password");
				
				launchUrl();
				loginToXero(userName,passWord);
				Thread.sleep(4000);
				goToPurchases();
			

				Thread.sleep(4000);
				String actValue=driver.getTitle().trim();
				String expValue=prop.getProperty("exppurchase_title");
				Assert.assertTrue(actValue.contains(expValue));
			
				logger.log(Status.PASS, MarkupHelper.createLabel("testcase addOrganizationStandardPlan_ID08D Passed", ExtentColor.GREEN));
	
				Close_Browser();
			}
		}
	}
	
	@DataProvider
	public Object[][] getDataFromExcel() throws IOException
	{
		String cur_dir=System.getProperty("user.dir");
		String excelPath=cur_dir+"/src/test/java/utility/TestDataXero.xls";
		File file=new File(excelPath);
		
		FileInputStream fs=new FileInputStream(file);
		HSSFWorkbook wb=new HSSFWorkbook(fs);
		HSSFSheet sheet=wb.getSheet("Sheet1");
		
		int trow=sheet.getLastRowNum()+1;
		int tcol=sheet.getRow(0).getLastCellNum();
		
		System.out.println("rows"+trow);
		//System.out.println(str[i][j]);

		// assign data to 2 dim array
		String[][] str=new String[trow][tcol];
		for(int i=1;i<trow;i++)
		{
			for(int j=0;j<tcol;j++)
			{
				str[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				System.out.println("data");
				System.out.println(str[i][j]);
			}
		}
		
	  return str;
	}
	
	
	@AfterTest
	public void AfterTest()
	{
		extent.flush();
	}
}
