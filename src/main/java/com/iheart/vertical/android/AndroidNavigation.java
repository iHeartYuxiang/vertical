package com.iheart.vertical.android;

import com.iheart.vertical.abstractLayer.*;
import com.iheart.vertical.utils.WaitUtility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.*;


public class AndroidNavigation extends Page implements Navigation {
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.ImageButton[1]")
	public AndroidElement android_navIcon;
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.ImageButton[1]")  
		public AndroidElement android_navIcon_withoutSetLocation;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]") 
	    private AndroidElement android_home;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.TextView[1]")
	   public AndroidElement android_liveRadio;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]/android.widget.TextView[1]")
	  public AndroidElement android_artistRadio;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[5]/android.widget.TextView[1]")
		public AndroidElement android_podcasts;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[6]/android.widget.TextView[1]")
		public AndroidElement android_perfectFor;
	
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[7]/android.widget.TextView[1]")  
		public AndroidElement android_listeningHistory;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[8]/android.widget.TextView[1]")
		public AndroidElement android_alarm;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[9]/android.widget.TextView[1]")
		public AndroidElement android_sleepTimer;
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[10]/android.widget.TextView[1]")
		public AndroidElement android_settings;
						
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[11]/android.widget.TextView[1]")
	   public AndroidElement android_exitApp;
	
	public AndroidNavigation(AndroidDriver _driver)
	{
		super(_driver);
	}
	
	
    private void goto_direct(AndroidElement _page)
    {
    	android_navIcon.click();
    	
    	_page.click();
    	
    	//Sometimes the loading is slow
    	WaitUtility.sleep(5000);
    	
    }
    
    private void exitApp( )
    {   android_navIcon.click();
    	scrollDown();
    	android_exitApp.click();
    	
    }
    
   public void scrollDown()
   {   android_navIcon.click();
       WaitUtility.sleep(1000);
	  Page.scroll(android_home, android_artistRadio);
   }
    
    private void scrollDown_notYetImplemented()
    {
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
    }
	
	public  void gotoPage(String pageName)
	{
		 switch (pageName) {
	        case "liveRadio":
	        	goto_direct(android_liveRadio);
	            break;
	        case "artistRadio":
	        	goto_direct(android_artistRadio);
	        	break;
	        case "perfectFor":
	        	goto_direct(android_perfectFor);
	        	break;
	        case "podcasts":
	        	goto_direct(android_podcasts);
	            break;
	        case "home":
	        	goto_direct(android_home);
	            break;
	        case "forYou":  //same as home
	        	goto_direct(android_home);
	            break;
	        case "alarmClock":  //same as home
	        	goto_direct(android_alarm);
	            break;  
	        case "sleepTimer":  //same as home
	        	goto_direct(android_sleepTimer);
	            break;    
	        case "settings":  //same as home
	        	goto_direct(android_settings);
	            break;     
	        case "exit":  //same as home
	        	//goto_direct(android_exitApp);
	        	exitApp();
	            break;     
	        default:
	        	goto_direct(android_home);
	            throw new IllegalArgumentException("Page doesn't exist: " + pageName);
	    }
	}
	
    public void locateElements()
    {
    	
    	
    }
	
    
    
}
