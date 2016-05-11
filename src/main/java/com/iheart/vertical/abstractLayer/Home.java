package com.iheart.vertical.abstractLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import com.iheart.vertical.utils.WaitUtility;

import java.util.List;
import java.util.ArrayList;


public abstract class Home extends Page{
	
	public WebElement subMenu;  
	public By chooseMenuBy;
	
	
	
	 public Home()
    {
    	this(driver);
    }
    
    public Home(WebDriver driver)
    {
    	super(driver);
    }

    
    public void gotoSubMenu(String pageName)
    {
    	List<WebElement>  options = subMenu.findElements(chooseMenuBy);
        for (WebElement option: options )
        {
        	if (option.getText().equalsIgnoreCase(pageName))
        	{
        		option.click();
        		break;
        	}
        }
    	
    }
    
    public void chooseMenuByIndex(int index)
    {
    	List<WebElement>  options = subMenu.findElements(chooseMenuBy);
        System.out.println("See chosen MENU:" + options.get(index).getText() );
        options.get(index).click();
    }
    
    
    
    
    public void playStationByIndex(int index)
    {
    	 
        List<WebElement>  stations = stationTiles.findElements(chooseStationNameBy);
        System.out.println("See chosen station:" + stations.get(index).getText() );
        stations.get(index).click();
        
    }
	    
    
    public void playStationByName(String stationName)
    {
    	
        List<WebElement>  stations ;
        try{
        	stations= stationTiles.findElements(chooseStationNameBy);
        }catch(Exception e)
        {
        	//just in case that loading is slow
        	WaitUtility.sleep(2000);
        	stations= stationTiles.findElements(chooseStationNameBy);
        }
        for (WebElement station: stations)
        {
        	System.out.println("See chosen station:" + station.getText() );
        	if (station.getText().contains(stationName))
        	{	
               station.click();
               break;
        	}  
        }
        
    }
	    
	
	
    public void locateElements()
    {
    	
    }
	

}
