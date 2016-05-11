package com.iheart.vertical.web;


import com.iheart.vertical.abstractLayer.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebPodcasts extends Podcasts{

	 @FindBy(css=".station-tiles")
	     public WebElement web_stationTiles;
	 
	
		
	 public WebPodcasts()
	   {   this(driver);
		  
	   } 
	   
	   public WebPodcasts(WebDriver driver)
	   {   super(driver);
	     
	   } 
	   
	   
	   public void locateElements()
		{   
		   // firstPod = this.web_firstPod;
		    
		    stationTiles = this.web_stationTiles;
		  //  episodeTiles = this.web_episodeTiles;
		    
		   // findEpisodeBy = By.id("com.clearchannel.iheartradio.controller:id/event_layout");
		    choosePlayIconBy = By.className("icon-play");
			chooseStationNameBy = By.className("title");
			
			//findEpisodeNameBy = By.id("com.clearchannel.iheartradio.controller:id/event_text");
			
			 
		}
}
