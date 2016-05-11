package com.iheart.vertical.abstractLayer;



import com.iheart.vertical.utils.WaitUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class ForYou extends Page{
	
   public WebElement firstStation;
   public WebElement stationPlaying;
   
   
  
   //for create station
   public WebElement stationPlay;
   
	    
    public ForYou()
    {
    	this(driver);
    }
    
    public ForYou(WebDriver driver)
    {
    	super(driver);
    }

	    
	    
}
