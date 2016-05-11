package com.iheart.vertical.web;


import com.iheart.vertical.utils.*;
import com.iheart.vertical.abstractLayer.*;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebPerfectFor extends PerfectFor {
	@FindBy(css=".filters > div:nth-child(1) > div:nth-child(1) > select:nth-child(2)")
  	    private WebElement web_activityList;

	@FindBy(css="station-tiles")
		private WebElement web_playList;
	
	
	public WebPerfectFor()
	{
	   this(driver);
	}
	
	public WebPerfectFor(WebDriver _driver)
	{
	   super(_driver);
	}
	
	// public void playLive(By byForActivity, By byForPlayList)
	  
	      
	
	public void locateElements()
	{
		activityList =  this.web_activityList;
		findActivityBy = By.tagName("option");
	
		playList = this.web_playList;
		findPlayListBy = By.className("icon-play");
	}

}
