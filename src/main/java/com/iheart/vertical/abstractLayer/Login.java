package com.iheart.vertical.abstractLayer;

import com.iheart.vertical.utils.*;


import org.openqa.selenium.By;

import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.*;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public abstract class Login  extends Page{
		
    
	
    public WebElement loginButton, userName, password, login;
    
	
	public Login()
	{
		this(driver);
		
	}
	
	public Login(WebDriver driver)
	{
		super(driver);
		
	}
	
	
	
	//What about sign up? I guess that I will worry about that later.
	
	public void login()
	{ 
		login(USER_NAME, PASSWORD);
		
	}
	
	public void login(String _userName, String _password)
	{   
		try{
		   loginButton.getText();
		}catch(Exception e)
		{
			System.out.println("Already loggged in. Do nothing.");
			return;
		}
		
		int count = 0;    
		boolean isDone = false;
		do{
			
			try{
			   loginButton.click();
			   isDone = true;
			}catch(Exception e)
			{
				System.out.println("LOGIN button is not clicked. try again.");
			}
			WaitUtility.sleep(1500);
			
			count++;
		}while (count< 6 && !isDone);
	//	}while (count< 6 && !driver.getPageSource().contains("Don't have an account?"));//Certainly this is not appliable for mobile
		
		
		WaitUtility.sleep(1000);
    	userName.sendKeys(_userName);
    	WaitUtility.sleep(1000);
	    password.sendKeys(_password);
	
		login.click();
		
		WaitUtility.sleep(3000);
		
		
		
	}
	
	
	public abstract void setLocation();
   
}
