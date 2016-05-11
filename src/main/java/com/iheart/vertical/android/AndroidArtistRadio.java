package com.iheart.vertical.android;

import com.iheart.vertical.utils.*;
import com.iheart.vertical.abstractLayer.*;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AndroidArtistRadio extends ArtistRadio
{
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/tab_list_view")
  			private AndroidElement android_stationTiles;

	

	public AndroidArtistRadio()
	{
	   this(driver);
	}
	
	public AndroidArtistRadio(WebDriver _driver)
	{
	   super(_driver);
	}
	
	
	public void locateElements()
	{  
		stationTiles =  this.android_stationTiles;
		findStationBy = By.id("com.clearchannel.iheartradio.controller:id/station_logo");
		
	}

	
	   
}


