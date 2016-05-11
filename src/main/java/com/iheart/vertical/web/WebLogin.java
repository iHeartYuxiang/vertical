package com.iheart.vertical.web;


import com.iheart.vertical.abstractLayer.*;
import com.iheart.vertical.utils.WaitUtility;

import org.openqa.selenium.WebElement;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;



public class WebLogin extends Login {
	
	//for web
    @FindBy(css=".icon-account") public WebElement web_loginButton;
	@FindBy(css = "input[name='username']") public WebElement web_userName;
	@FindBy(css = "input[name='password']") public WebElement web_password;
	@FindBy(css = "#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > form > button") public WebElement web_login;
	
	
	
	
	//SignedAccount DROP-DOWN
	//@FindBy(css="div.dropdown-trigger:nth-child(2) > button:nth-child(1)") public WebElement signedAccount;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(1) > button:nth-child(1)") public WebElement signedAccount;
	
	@FindBy(css="//*[@id='page-view-container']/div/div[1]/div[2]/div/div[2]/div/button/span")  public WebElement signedAccount_chrome;
	
	
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")
		public WebElement option_profile;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)")
		public WebElement option_myStations;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)")
		public WebElement  option_listenHistory;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1)")
		public WebElement  option_favoriteSongs;

	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(1)")
		public WebElement option_friends;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1)")
		public WebElement option_setting;
				 
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(7) > a:nth-child(1)")
	    public WebElement option_logout;
	
	@FindBy(css="#page-view-container > div > div.header > div.header-wrapper > div > div.header-right > div > button > span")  public WebElement option_logout_chrome;
	
 
    public WebLogin()
    {
    	this(driver);
    }
	
    public WebLogin(WebDriver driver)
    {
    	super(driver);
    	
    }
	
	public void locateElements()
	{
		loginButton = web_loginButton;
		userName = web_userName;
		password = web_password;
		login = web_login;
	}

	
	public void logout()
	{
		// limit try to 5 times
		int count = 0;
		boolean isOut = false;
		
		Actions action = new Actions(driver);
		do {
			
		    action = action.moveToElement(signedAccount);
			WaitUtility.sleep(200);
			try{
		    	action.moveToElement(option_logout).click().build().perform();
			}catch(Exception e)
			{
				
			}
			
			WaitUtility.sleep(500);
		
			count++;
		
		}while(count <10 && !isLoggedOut());	
	}
	
	private boolean isLoggedOut()
	{   
		boolean isOut = false;
		try{
			loginButton.getTagName();
			System.out.println("User is logged out.");
			isOut = true;
			
		}catch(Exception e)
		{
			isOut = false;
		}
		return isOut;
	}
	
	
	
	public void setLocation()
	{
		
	}
	   
	
}
