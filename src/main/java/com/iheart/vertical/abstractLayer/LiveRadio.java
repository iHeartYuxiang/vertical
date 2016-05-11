package com.iheart.vertical.abstractLayer;

import com.iheart.vertical.utils.WaitUtility;
import com.iheart.vertical.utils.Utils;

import com.iheart.vertical.abstractLayer.Page;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public abstract class LiveRadio extends Page{
	
	//filterSection;
	
     // it is submenu
	    public WebElement country;
	    public WebElement city;
	    public WebElement genresDropDown;

	
	//  this shit shall be gone 
	public WebElement firstLive;
	 public  WebElement firstLiveTextLink;
	 public WebElement firstStationLabel;
	public WebElement stationLogo;
	    public WebElement station_playButton; //majorly for web
	    
	    public WebElement stationName;
	
	 
	    public WebElement firstStation;
	    public WebElement stationPlaying;
	 
	
	//FOR GLAD YOU LIKE IT POPUP
	public WebElement shareStation;
	
    
    
    public LiveRadio()
    {
    	this(driver);
    }
    
    public LiveRadio(WebDriver driver)
    {
    	super(driver);
    }
    
    public void playStationByIndex(int index)
    {
    	//Some times POPUP comes up 'Glad you like it...' simply click on top bar to get rid of it
    	
        List<WebElement>  lists = new ArrayList<WebElement>() ;
        try{
           lists = stationTiles.findElements(chooseStationBy);
        }catch(Exception e)
        {
        	if (!Page.mediaType.equals("web"))
            {
               Page.dismissAllPopups();
            }   
            //dismissPopups();
            lists = stationTiles.findElements(chooseStationBy);
        }
        lists.get(index).click();
        
        waitForPreroll();
        
        if (!Page.mediaType.equals("web"))
        {	   
 	       try{
 	         Page.dismissAllPopups();
 	       }catch(Exception e)
 	       {
 	    	   
 	       }
        }
        
       
    }
   
 //  public abstract void dismissPopup();
   
   public void playRandomStation()
   {
	   //Generate random number, and play that station
   }
   
  
   
	
	public void filterLiveStation()
	{   
		filterStation();
		//WebElement firstStationLabel = stationGrid.findElements(By.className(stationName_class)).get(0);
	//	WebElement firstStation = stationGrid.findElements(By.className(station_iconPlay_class)).get(0);
	    
		String chosenStation = firstStationLabel.getText();
		System.out.println("chosen station:" + chosenStation);
		firstStation.click();
		//Increase waiting time since sometime it is slow or it is just buffering
		WaitUtility.sleep(30000);//for commercial
		
		String playingStation = Page.getPlayer().getStationPlaying();
		System.out.println("station PLAYING:" + playingStation);
		
		if(player == null)
			System.out.println("Player is null");
		else
		    player.makeSureItIsPlaying();
		
		
		if (!chosenStation.equalsIgnoreCase(playingStation))
			handleError("Filter is not working.", "filterLiveStation");
	}
	
	
	
	private void filterStation()
	{   WaitUtility.sleep(1000);
		chooseCountry();
		WaitUtility.sleep(1000);
		//new Select(city).selectByIndex(3);
		chooseCity();
		//WaitUtility.sleep(2000);
		chooseGenre();
	}
	
	//Glad you like it shit. 
	public void handleGladPopup()
	{
		shareStation.click();
	}
	
	public abstract void chooseCountry();
	public abstract void chooseCity();
	public abstract void chooseGenre();
}
