package com.iheart.vertical.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.iheart.vertical.abstractLayer.*;
import com.iheart.vertical.utils.*;

import java.util.List;
import java.util.ArrayList;



public class WebMyStations extends MyStations{
	
	@FindBy(css=".station-tiles") private WebElement stationTiles;
	
	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
			private WebElement web_chosenCustomerStation;
		
	@FindBy(css="#main > div:nth-child(2) > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > div")
    private WebElement web_stationToDelete;
	
	@FindBy(css="#bottom-fixed .icon-play") public WebElement web_icon_play;

	   public WebMyStations()
	   {   this(driver);
		  
	   } 
	   
	   public WebMyStations(WebDriver driver)
	   {   super(driver);
	     
	   } 
	   
	   
	   public void playLiveRadio()
	    {
		   playStation("live");
	    }
	    
	    public void playPodcast()
	    {
	    	playStation("talk-show");
	    }
	    
	    public void playArtistRadio()
	    {
	    	playStation("artist");
	    }
	
	    private void playStation(String type)
	    {
	    	List<WebElement> radios = stationTiles.findElements(By.className(type));
		       System.out.println(type + " radios count:" + radios.size());
		      if (radios.size() <1 )
		      {
		    	  System.out.println("No such type of staion under my stations. Search one and play..");
		    	  if (type.equals("talk-show"))
		    		  Page.search("ask dave");
		    	  else if (type.equals("artist"))
		    		  Page.search("ADELE");
		    	  else 
		    		  Page.search("z100");
		      }
		      
		       for(WebElement radio: radios)
		       {  
	    	       radio.click();
	    	       WaitUtility.sleep(500);
	    	       makeItPlay();
		    	   WaitUtility.sleep(1000);
		    	   break;
		       }
		       
		      waitForPreroll();
	    }
	    
	    public void makeItPlay()
		{   
		    try{

			    web_icon_play.isDisplayed();
		    	web_icon_play.click();

		    }catch(Exception e)

		    {  System.out.println("Music is playing. ");
		    	return;
		    }
		    
		   
		    
		}
	    
	   
		    
	    public void deleteFirstStation(String type)
	    {
	    	List<WebElement> radios = stationTiles.findElements(By.className(type));
		       System.out.println(type + " radios count:" + radios.size());
		      if (radios.size() <1 )
		      {
		    	  System.out.println("No such a radio in this page. Do nothing.");
		    	  return;
		      }
		      
		      
		       for(WebElement radio: radios)
		       {   
		    	   deleteAstation(radio.findElement(By.className("mask")));
		    	   break;
		       }
	    }    
	    
	    
	public void locateElements()
	{
		stationToDelete = this.web_stationToDelete;
		
	}

	
}
