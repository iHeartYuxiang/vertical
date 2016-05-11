package com.iheart.vertical.abstractLayer;


import java.util.List;
import java.util.ArrayList;

import com.iheart.vertical.utils.*;
import com.iheart.vertical.abstractLayer.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import com.iheart.vertical.utils.WaitUtility;

public abstract class Podcasts extends Page{
   
	public WebElement episodeTiles;
	public By findEpisodeBy, findPlayButtonBy, findEpisodeNameBy;
	
	 public Podcasts()
    {
    	this(driver);
    }
    
    public Podcasts(WebDriver driver)
    {
    	super(driver);
    }

    //first layer
    public void choosePodByIndex(int index)
    {   
    	 List<WebElement>  options = new ArrayList<WebElement>();
    	if (Page.mediaType.equals("web"))
    	   options = stationTiles.findElements(choosePlayIconBy);
    	else
    		 options = stationTiles.findElements(chooseStationNameBy);
        options.get(index).click();
    }
    
    public void playEpisodeByIndex(int index)
    {  
    	List<WebElement>  episodes;
    	try{
    		episodes = episodeTiles.findElements(findPlayButtonBy);
    	}catch(Exception e)
    	{
    		//Retry it once just in case the loading is slow
    		WaitUtility.sleep(2000);
    		episodes = episodeTiles.findElements(findPlayButtonBy);
    	}
        episodes.get(index).click();
    }
    
    
    //Majorly for mobile
    public void playAskDave()
	{ 
		search("ask dave");
		playEpisodeByIndex(1);
		WaitUtility.sleep(2000);
		
		if (!Page.mediaType.equals("web"))
       {	   
	       try{
	         Page.dismissAllPopups();
	       }catch(Exception e)
	       {
	    	   
	       }
       }
		
		
	}
	
}
