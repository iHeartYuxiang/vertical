package com.iheart.vertical.web;


import com.iheart.vertical.abstractLayer.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebArtistRadio extends ArtistRadio{
	
	 @FindBy(css=".station-tiles")  public WebElement web_stationTiles;
	 @FindBy(css=".filters > div:nth-child(1) > div:nth-child(1) > select:nth-child(2)") public WebElement web_genres;
	
     @FindBy(css=".profile-player .icon-play") public WebElement web_stationPlay;
	
	 public WebArtistRadio()
	   {   this(driver);
		  
	   } 
	   
	   public WebArtistRadio(WebDriver driver)
	   {   super(driver);
	     
	   } 
	   
	   
	   public void locateElements()
		{   
		    stationPlay = this.web_stationPlay;
		    
		    stationTiles =  this.web_stationTiles;
			findStationBy = By.className(".icon-play");
		
			genres = this.web_genres;
			findGenreBy= By.tagName("option");
			 
		}

}
