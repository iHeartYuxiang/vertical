package com.iheart.vertical.web;



import com.iheart.vertical.abstractLayer.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebForYou extends ForYou{

	@FindBy(css=".station-tiles") private WebElement stationTiles;
	
	 public WebForYou()
	   {   this(driver);
		  
	   } 
	   
	   public WebForYou(WebDriver driver)
	   {   super(driver);
	     
	   } 
	   
	   
	   public void locateElements()
		{   
		   
			 
		}
}
