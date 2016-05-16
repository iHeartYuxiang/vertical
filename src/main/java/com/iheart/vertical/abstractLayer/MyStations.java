package com.iheart.vertical.abstractLayer;


import java.util.List;

import com.iheart.vertical.utils.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class MyStations extends Page{
	
	 public WebElement chosenCustomerStation;
	
	 public WebElement stationToDelete;
	 
	    
	    
    public MyStations()
    {
    	this(driver);
    }
    
    public MyStations(WebDriver driver)
    {
    	super(driver);
    }
	 
    public void playCustomerStation()
    {
    	chosenCustomerStation.click();
    }
    
    
    public void deleteStation()
    {
    	int offset_x = stationToDelete.getLocation().getX();
    	int offset_y = stationToDelete.getLocation().getY();
    	
    	System.out.println("OFFSET:" + offset_x + "/" + offset_y);
    	
    	int width = stationToDelete.getSize().getWidth();
    	int height = stationToDelete.getSize().getHeight();
    	
    	System.out.println("dimension:" + width + "/" + height);
    	WebElement removeLink = driver.findElement(By.cssSelector("li.tile:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)"));
        WebElement hotCycle = driver.findElement(By.cssSelector(".favorites > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)"));
       
    	Actions builder = new Actions(driver);   
        builder.moveToElement(stationToDelete, (int)(width*0.95921450), (int)(height*0.93198992)).click().build().perform();
    	
    	
    }
    
    //Delete the first
    public void deleteAstation()
    {
    	Actions builder = new Actions(driver);
    	  

    	double ratioX = 0.95921450;
    	double ratioY = 0.93198992;
    	
    														
    	
        WebElement theBox = driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(3) > div:nth-child(1)"));
        
    	
    	int w = theBox.getSize().getWidth();
    	int  u = w / 2;
    	int x = theBox.getLocation().getX();
    	int   y = theBox.getLocation().getY();
    	int  centerX = x + u;
    	int centerY = y + u;
    	
    	//This is where the center of small cirle is
    	int  circleX = (int) ((x + w) * ratioX);
    	int circleY = (int) ((y + w) * ratioY);
    	
    	int clickX = circleX - centerX;
    	int clickY = circleY - centerY;
        System.out.println("clickX/Y:" + clickX + "/" + clickY);
    	
    	builder.moveToElement(theBox).build().perform();
    	WaitUtility.sleep(500);
    	builder.moveByOffset(clickX, clickY).click().build().perform();
    	WaitUtility.sleep(500);
    	WebElement removeButton = driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)"));
       	removeButton.click();
    	
    	WebElement confirm = driver.findElement(By.cssSelector("button.btn-primary:nth-child(2)"));
        
    	confirm.click();
    
    
    }
    
    
    
    //Delete the first one as of such a type
    public void deleteAstation(WebElement theStation)
    {
    	Actions builder = new Actions(driver);
    	  

    	double ratioX = 0.95921450;
    	double ratioY = 0.93198992;
        
    	
    	int w = theStation.getSize().getWidth();
    	int  u = w / 2;
    	int x = theStation.getLocation().getX();
    	int   y = theStation.getLocation().getY();
    	int  centerX = x + u;
    	int centerY = y + u;
    	int  circleX = (int) ((x + w) * ratioX);
    	int circleY = (int) ((y + w) * ratioY);
    	
    	int clickX = circleX - centerX;
    	int clickY = circleY - centerY;
        System.out.println("clickX/Y:" + clickX + "/" + clickY);
    	
    	builder.moveToElement(theStation).build().perform();
    	WaitUtility.sleep(500);
    	builder.moveByOffset(clickX, clickY).click().build().perform();
    	WaitUtility.sleep(500);
     	//WebElement removeButton = theStation.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)"));
    	
    	WebElement removeButton = theStation.findElement(By.cssSelector("[title='Remove']"));
    	removeButton.click();
    	
    	WebElement confirm = driver.findElement(By.cssSelector("button.btn-primary:nth-child(2)"));
        
    	confirm.click();
    
       System.out.println("Station is deleted."); 
    }
    

}
