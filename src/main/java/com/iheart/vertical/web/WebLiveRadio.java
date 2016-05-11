package com.iheart.vertical.web;

import com.iheart.vertical.abstractLayer.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebLiveRadio extends LiveRadio {

	
	@FindBy(css=".station-tiles")
	   private WebElement web_stationTiles;
	
	
	
     @FindBy(xpath="//*[@id='main']/div[2]/section/ul/li[1]/div/div[1]/a/div[3]/button/i") 
	   private WebElement web_firstLive;
	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")
    private WebElement web_firstLiveTextLink;
	
	//filter station
	@FindBy(css=".country-filter > div:nth-child(1) > select:nth-child(2)") public WebElement web_country;
	@FindBy(css=".market-filter > div:nth-child(1) > select:nth-child(2)") public WebElement web_city;
	@FindBy(css="div.form-group:nth-child(3) > div:nth-child(1) > select:nth-child(2)") public WebElement web_genresDropDown;
	@FindBy(css=".header-menu-main > li:nth-child(4) > a:nth-child(1)")  public WebElement web_genres;
	
	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)") public WebElement web_firstStation;
	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)") public WebElement web_firstStationLabel;
	@FindBy(css=".player-station") public WebElement web_stationPlaying;

	//AFTER SEARCH
	@FindBy(css=".selected > div:nth-child(2) > p:nth-child(1) > a:nth-child(1)") private WebElement web_firstSearchResult;
	
	

    //Volume
	@FindBy(css=".volume > button:nth-child(1)") public WebElement web_volumeButton;
	@FindBy(css="#player > div.player-center > div > div > button > i") public WebElement web_iconMute;
	

	
	   public WebLiveRadio()
	   {   this(driver);
		  
	   } 
	   
	   public WebLiveRadio(WebDriver driver)
	   {   super(driver);
	     
	   } 
	   
	
		/*
	public boolean isPlaying()
	{   boolean isPlaying = false;
		try{
			   driver.findElement(By.cssSelector("button.idle:nth-child(3)"));
		}catch(Exception e)
		{
			isPlaying = true;
		}
		System.out.println("isPlaying:" + isPlaying);
		return isPlaying;
	}
	
	*/
	
	public void chooseCountry()
	{ 
		new Select(country).selectByVisibleText("Mexico");
	}
	
	public void chooseCity()
	{
		new Select(driver.findElement(By.name("city"))).selectByIndex(1);
	}
	
	public void chooseGenre()
	{
		new Select(genresDropDown).deselectByIndex(2);
	}
	
	
	public void locateElements()
	{   
		stationTiles = this.web_stationTiles;
		choosePlayIconBy = By.className("icon-play");
		chooseStationBy = By.className("icon-play");
		
		firstLive = this.web_firstLive;
	    firstLiveTextLink = this.web_firstLiveTextLink;
	    
	    //for filter station
	    firstStationLabel = this.web_firstStationLabel;
	    country = this.web_country;
	    city = this.web_city;
	    genresDropDown = this.web_genresDropDown;
	    firstStation  = this.web_firstStation;
	    stationPlaying = this.web_stationPlaying;
	  
		 
	}
}
