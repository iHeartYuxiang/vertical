package com.iheart.vertical.android;


import com.iheart.vertical.utils.*;
import com.iheart.vertical.abstractLayer.*;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AndroidHome extends Home{

	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/indicator")
  		private AndroidElement android_subMenu;
	
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/tab_list_view")
		private AndroidElement android_stationTiles;
	
	public AndroidHome()
	{
	   this(driver);
	}
	
	public AndroidHome(WebDriver _driver)
	{
	   super(_driver);
	}
	  
	
	
	
	public void locateElements()
	{
		subMenu =  this.android_subMenu;
		stationTiles = this.android_stationTiles;
		chooseMenuBy = By.className("android.widget.TextView");
		chooseStationBy = By.className("android.widget.LinearLayout");
		
		chooseStationLogoBy = By.id("com.clearchannel.iheartradio.controller:id/station_logo");
		chooseStationNameBy = By.id("com.clearchannel.iheartradio.controller:id/event_text");
		chooseStationDescriptionBy = By.id("com.clearchannel.iheartradio.controller:id/event_sub_text");
		
		
	}
	
		
	
}
