package com.iheart.vertical.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.iheart.vertical.utils.WaitUtility;
import com.iheart.vertical.abstractLayer.*;
import com.iheart.vertical.utils.*;


public class WebNavigation extends Page implements Navigation{

	
	
	public static  void gotoPodcasts()
	{
		driver.findElement(By.cssSelector(".header-menu-main > li:nth-child(5) > a:nth-child(1)")).click();
		WaitUtility.sleep(2000);
	}
	
	public static  void gotoPage(String pageType)
	{
		String newURL = urlTo(pageType);
		
		driver.get(newURL);
		WaitUtility.sleep(3000);
	}
	
	private static  String urlTo(String pageType)
	{   String newURL= "";
		
		String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
		
		
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    switch (pageType) {
        case "liveRadio":
        	newURL = part1 + "//" + part2 + "/live/country/US/" ;
            break;
        case "artistRadio":
        	newURL = part1 + "//" + part2 + "/artist/" ;
        	break;
        case "perfectFor":
        	newURL = part1 + "//" + part2 + "/perfect-for/" ;
        	break;
        case "podcasts":
        	//newURL = part1 + "//" + part2 + "/show/" ;
        	newURL = part1 + "//" + part2 + "/show/station/Daily-Pulse/" ;
            break;
        case "profilePage":
        	newURL = part1 + "//" + part2 +"/my/" ;
            break;
        case "genresPage":
        	newURL = part1 + "//" + part2 + "/genre/" ;
            break;    
        case "myStations":
        	newURL = part1 + "//" + part2 + "/my/stations/" ;
            break; 
        case "forYou":
        	newURL = part1 + "//" + part2  ;
            break;     
        default:
        	newURL = currentURL;
            throw new IllegalArgumentException("Invalid Page Type: " + pageType);
    }
	    
		System.out.println("SEE new url:"  + newURL );
		
		return  newURL;
		
	}
	
	
	
	public void locateElements()
	{
		
	}
	
}
