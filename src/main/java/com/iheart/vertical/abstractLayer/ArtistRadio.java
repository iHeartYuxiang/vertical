package com.iheart.vertical.abstractLayer;



import java.util.List;

import com.iheart.vertical.utils.WaitUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;




public abstract class ArtistRadio extends Page {

   public WebElement genres; 
   public By findGenreBy;
	
   public WebElement stationTiles; 
   public By findStationBy;
   
   public WebElement firstStation;
   public WebElement stationPlaying;
   
   
  
   //for create station
   public WebElement stationPlay;
   
	    
    public ArtistRadio()
    {
    	this(driver);
    }
    
    public ArtistRadio(WebDriver driver)
    {
    	super(driver);
    }

    
    
    public void createAstation()
    {
         player.songTrack.click();
         WaitUtility.sleep(1000);
         stationPlay.click();
         waitForPreroll();
         
    }
    
   public void chooseGenreByIndex(int index)
   {
	   List<WebElement>  options = genres.findElements(findGenreBy);
       System.out.println("See chosen genre:" + options.get(index).getText() );
       options.get(index).click();
   }
    
   public void chooseStationByIndex(int index)
   {   //Artist radio page takes extra logn time to load sometimes.
	   WaitUtility.sleep(5000);
	   List<WebElement>  options = stationTiles.findElements(findStationBy);
       System.out.println("See  STATION count:" + options.size());
       options.get(index).click();
       
       //handle possible pre-roll
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
   
   public void playStationByIndex(int index)
   {
	   chooseStationByIndex(index);
   }
   
   
}
