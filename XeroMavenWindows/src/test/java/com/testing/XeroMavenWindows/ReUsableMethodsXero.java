package com.testing.XeroMavenWindows;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReUsableMethodsXero {
	
	public static ExtentTest logger;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;

	public static void fileUploadFromComputer(File obj, String objName) throws AWTException, InterruptedException
	{
		  
	    Robot robot=new Robot();
		
	    //click browse btn
	    StringSelection stringSelection=new StringSelection(obj.getAbsolutePath());
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	    
	 
	    Thread.sleep(1000);
		
	 // Cmd + Tab is needed since it launches a Java app and the browser looses focus
	    
	    robot.keyPress(KeyEvent.VK_META);
	    robot.keyPress(KeyEvent.VK_TAB);
	    

	    robot.keyRelease(KeyEvent.VK_META);
	    robot.keyRelease(KeyEvent.VK_TAB);
	     
	    Thread.sleep(1000);
	     
	    //Open Goto window
	     
	    robot.keyPress(KeyEvent.VK_META);
	    robot.keyPress(KeyEvent.VK_SHIFT);
	    robot.keyPress(KeyEvent.VK_G);
	    
	   
	    robot.keyRelease(KeyEvent.VK_META);
	    robot.keyRelease(KeyEvent.VK_SHIFT);
	    robot.keyRelease(KeyEvent.VK_G);
	     
	
	    Thread.sleep(1000);
	    
	    //Paste the clipboard value
	     
	    robot.keyPress(KeyEvent.VK_META);
	    robot.keyPress(KeyEvent.VK_V);
	    
	    robot.keyRelease(KeyEvent.VK_META);
	    robot.keyRelease(KeyEvent.VK_V);
	    
	    Thread.sleep(1000);
	    
	     
	    //Press Enter key to close the Goto window and Upload window
	     
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    
	
	    Thread.sleep(1000);
	    
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    
	    System.out.println("copy completed");
	}
	
	/*
	 * MethodName:clickEvent
	 * Brief Description : Click on Object
	 * Arguments: obj --> Object, objName --> Name of the object
	 * Created By: Sindhuja
	 * Creation Date: Apr 15 2018
	 * Modified By: Sindhuja
	 * Modified Date: Apr 15 2018
	 * */
	public static void clickEvent(WebElement obj, String objName) {
		// TODO Auto-generated method stub
		if(obj.isDisplayed())
		{
			obj.click();
			logger.log(Status.INFO,"Pass: "+objName+" is clicked");

		}
		else
		{
			logger.log(Status.INFO,"Fail: "+objName+" field does not exist please check your application");
			logger.log(Status.FAIL, MarkupHelper.createLabel("testcase failed", ExtentColor.RED));
		}
	}
	
	public static void validateTextContext(WebElement obj,String expectedTxtValue, String objName)
	{
	
		if(obj.isDisplayed())
		{
               String actText=obj.getText().trim();
			   if(expectedTxtValue.equals(actText))
				{
				   logger.log(Status.INFO,"Pass: "+expectedTxtValue+" is matched with actual text value");

				}
				else
				{
					System.out.println("Fail: "+expectedTxtValue+" is not matched with actual text value");
					logger.log(Status.INFO,"Fail: "+expectedTxtValue+" is not matched with actual text value");
					logger.log(Status.FAIL, MarkupHelper.createLabel("testcase failed", ExtentColor.RED));
				}
			
		}
		else
		{
			System.out.println("Fail: "+objName+" field does not exist please check your application");
			logger.log(Status.INFO,"Fail: "+objName+" field does not exist please check your application");
			logger.log(Status.FAIL, MarkupHelper.createLabel("testcase failed", ExtentColor.RED));
		}
	}
	
	
	public static void enterText(WebElement obj, String textValue, String objName) {
		// TODO Auto-generated method stub
		if(obj.isDisplayed())
		{
			obj.sendKeys(textValue);
			logger.log(Status.INFO,"Pass: "+textValue+" is entered in "+objName+"field.");
		}
		else
		{
			logger.log(Status.INFO,"Fail: "+objName+" field does not exist please check your application");
			logger.log(Status.FAIL, MarkupHelper.createLabel("testcase failed", ExtentColor.RED));
		}
	}
	
	public static void selectDropDownValue(WebElement obj,String ddVal,String objName)
	{
		if(obj.isDisplayed())
		{
			Select select=new Select(obj);
			
			select.selectByVisibleText(ddVal);
			logger.log(Status.INFO,"Pass:"+ddVal+"is selected in"+objName+"field.");			
		}
		else
		{
			logger.log(Status.INFO,"Fail: "+objName+" field does not exist please check your application");
			logger.log(Status.FAIL, MarkupHelper.createLabel("testcase failed", ExtentColor.RED));		}
	}
	
	public static void selectCheckBox(WebElement obj, String objName)
	{
		if(obj.isDisplayed())
		{
			if(obj.isSelected()==false)
			{
				obj.click();
				logger.log(Status.INFO,"Pass:"+objName+" is checked");			

			}
			else
			{
				logger.log(Status.INFO,"Pass:"+objName+" is already checked");			
			}
			
		}
		else
		{
			logger.log(Status.INFO,"Fail: "+objName+" field does not exist please check your application");
			logger.log(Status.FAIL, MarkupHelper.createLabel("testcase failed", ExtentColor.RED));				}
	}
}
