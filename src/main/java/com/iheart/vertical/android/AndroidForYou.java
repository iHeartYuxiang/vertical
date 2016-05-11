package com.iheart.vertical.android;


import com.iheart.vertical.utils.*;
import com.iheart.vertical.abstractLayer.*;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AndroidForYou extends ForYou{
	
	

	@AndroidFindBy(xpath="//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[2]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ImageView[1]")  
		public  AndroidElement android_firstStation;
	

	public AndroidForYou()
	{
	   this(driver);
	}
	
	public AndroidForYou(WebDriver _driver)
	{
	   super(_driver);
	}
	
	
	public void locateElements()
	{  
		//to be done
	}

}
