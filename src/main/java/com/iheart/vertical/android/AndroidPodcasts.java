package com.iheart.vertical.android;

import com.iheart.vertical.utils.*;
import com.iheart.vertical.abstractLayer.*;

import java.util.List;
import java.util.ArrayList;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AndroidPodcasts extends Podcasts{
	
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/list_view")
	   private AndroidElement android_stationTiles;
	
	//SECOND layer:
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/tab_list_view")
	     private AndroidElement android_episodeTiles;
	
	public AndroidPodcasts()
	{
	   this(driver);
	}
	
	public AndroidPodcasts(WebDriver _driver)
	{
	   super(_driver);
	}
	
	
	public void handlePopup()
	{
		
	}
	
	public void locateElements()
	{  
	    stationTiles = this.android_stationTiles;
	    episodeTiles = this.android_episodeTiles;
	    
	    assignStationfindBy_mobile();
	    
	    findEpisodeBy = By.id("com.clearchannel.iheartradio.controller:id/event_layout");
		findPlayButtonBy = By.id("com.clearchannel.iheartradio.controller:id/play_iv");
		findEpisodeNameBy = By.id("com.clearchannel.iheartradio.controller:id/event_text");
		
	    
	}
	
	
}
