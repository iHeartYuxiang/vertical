package com.iheart.vertical.android;

import com.iheart.vertical.utils.*;

import com.iheart.vertical.abstractLayer.*;

import io.appium.java_client.android.*;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.*;

public class AndroidLogin  extends Login{

	//Set location
	//@AndroidFindBy(xpath="//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]")
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/zipcode_promt_edit_text")  
	   public AndroidElement android_zip;
	
	//This xpath works for Galaxy, but not HTC or Google Nexus
	//@AndroidFindBy(xpath="//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[2]")
    @AndroidFindBy(id="android:id/button1")	
	    public AndroidElement android_setLocation;
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]")
	   public AndroidElement android_skip;
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.Button[1]")
      public AndroidElement android_ok;
	
	//Login 
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[1]")
	       public AndroidElement android_loginButton_withSetLocation;
  
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[1]")
	    public AndroidElement android_loginButton_ifNoSetLocation;
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/login_button")
	   public AndroidElement android_loginButton;
	
	//xpath for the same element will change if pop up shows up before its presence
							//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]
	//@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]")
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/email")
	    public AndroidElement android_userName;
	
	//@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[2]")
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/password")  
	   public AndroidElement android_password;
	//@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]")
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/email_login")  
	   public AndroidElement android_login;
	
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
	   private AndroidElement lable;
	
	public AndroidLogin(WebDriver driver)
	{
		super(driver);
	}
	
	public void locateElements()
	{
		loginButton = this.android_loginButton;
		userName = this.android_userName;
		password = this.android_password;
		
		login = this.android_login;
	}
	
	
	public void login()
	{
	   super.login();
	   //dismiss 'Share station?' popup
	   
	   dismissPopup();
	   //Dismiss "Welcome to My Favorites Radio"
	   dismissWelcomeFavPopup();
	  
	}
	
	private void dismissWelcomeFavPopup()
	{  
		try {
		    driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/welcome_save")).click();
		    WaitUtility.sleep(500);
		    
		    driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/confirm_btn")).click();
		}catch(Exception e)
		{
			
		}
	}
	
	public void setLocation()
    {   //skip
		setLocation(null);
    }
	
	
    public void setLocation(String zip)
    {   
    	try{
    		System.out.println("Label:" + lable.getText());
    	}catch(Exception e)
    	{
    		// set location is not popped up. return
    		return;
    	}
    	
     	if (zip == null)
        {	
	        android_skip.click();
	        android_ok.click();
        }else
        {
        	android_zip.sendKeys(zip);
        	android_setLocation.click();
        }
        WaitUtility.sleep(2000);
        
        
    }
    
    
    //handle pop up "Like iHeartRadio?"
    public void dismissPopup()
    {  try{
    	  WebElement dismiss = driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/dismiss_dialog"));
          dismiss.click();
	    }catch(Exception e)
	    {
	    	
	    }
    }
	
}
