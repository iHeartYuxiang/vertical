package com.iheart.vertical.android;


import com.iheart.vertical.abstractLayer.*;
import com.iheart.vertical.utils.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;


public class AndroidRoyaltyReport {

	static HashMap<String, String> eventLog;
	static int eventCount = 1; 
	
	private AndroidNavigation navigation;
	 private AndroidLogin loginPage;
	 private AndroidPlayer player;
	 private AndroidHome home;
	 private AndroidLiveRadio liveRadio;
	 private AndroidArtistRadio artistRadio;
	 private AndroidPodcasts podcasts;
	 private AndroidPerfectFor perfectFor;
	
	 //private AndroidDriver driver;
	  
	 
	 public AndroidRoyaltyReport(AndroidDriver driver)
	 {
		 eventLog = new LinkedHashMap<String, String>();
	       
         eventLog.put("Login account:" + Page.getUserName() + "/" + Page.getPassword(), "Android");
         eventLog.put("Step Actions:" , "");
         
         loginPage = new AndroidLogin(driver);
  	     navigation = new AndroidNavigation(driver);
  	   
  	     player = new AndroidPlayer(driver);
         Page.setPlayer(player);
         
         home = new AndroidHome(driver);
         liveRadio = new AndroidLiveRadio(driver);
         artistRadio = new AndroidArtistRadio(driver);
         podcasts = new AndroidPodcasts(driver);
         perfectFor = new AndroidPerfectFor(driver);
	 }
	 
	 
    
    public void testRoyalty_liveRadio() throws Exception
    {   
        
    	 loginPage.setLocation("10013");
         loginPage.login();
         logEvent("Log in with an iHeartRadio account");
        
        
        logEvent("play a live station from For you");
        
        Page.search("102.3");
        
     
        //scan for a few times
        logEvent("Scan the station couple of times (> 15 sec )");
         player.doScan();
         

         //listen to 2 songs without skipping
         logEvent("Listen to 2 songs to end without skipping.");
         //Since live radio doesn't provide song duraion info, simply wait for 12 minutes here
        // WaitUtility.sleep(12*60*1000);
         WaitUtility.sleep(3*1000);
         
         player.collapse();
         Page.goBack();
         
    
       
        System.out.println("liveRadio section is done.");
          
    }    
	  
	

	
     public void testRoyalty_podcasts() throws Exception
     {   
        
        loginPage.setLocation("10013");
         loginPage.login();
         
        logEvent("podCasts");
         navigation.gotoPage("podcasts");
         podcasts.playAskDave();
         
         player.fullScreen();//just to bring it to full screeen
         
         logEvent("Thumb down");
         player.doThumbDown("podcasts");
         logEvent("skip");
         player.doSkip();
         
         logEvent("Thumb up");
         player.doThumbUp("podcasts");
        
         player.collapse();
        
         Page.goBack();
         
         System.out.println("podCasts section is done.");
        
         logEvent("Live station from statino near you");
         navigation.gotoPage("liveRadio");
         liveRadio.playStationByIndex(1);
         player.collapse();
         
         
         
         Page.goBack();
         logEvent("Exit app");
         navigation.gotoPage("exit");
        
    
       System.out.println("Podcasts part is done.");
	        
     }    
	
	
	 
     public void testRoyalty_perfectFor() throws Exception
     {
	    
         loginPage.setLocation("10013");
         loginPage.login();
         
        
         navigation.gotoPage("perfectFor");
         logEvent("play live from perfect for");
         perfectFor.playStationByIndex(2);//change it to 2 to by pass SCAN bug for club jam hits
         
         player.fullScreen();//just to bring it to full screeen
         logEvent("scan");
         player.doScan();
         player.collapse();
         perfectFor.back();
         logEvent("change activity - live sation");
         logEvent("play live station from another activity");
         perfectFor.playStationByIndex(2);
         
         
         //Play customer station from Perfect for? How do i know it is a customer station?
         Page.goBack();
         navigation.gotoPage("perfectFor");
         logEvent("play custom station from perfect for");
         Page.search("josh groban");
        
         logEvent("Skip a song(> 15 sec)");
         player.doSkip();
         //WaitUtility.sleep(15000);
         WaitUtility.sleep(2000);
         logEvent("Thumb Up");
         player.doThumbUp("artist");
         logEvent("Skip  a song(> 15 sec)");
         player.doSkip();
         WaitUtility.sleep(2000);
         logEvent("thumb down");
         player.doThumbDown("artist");
         
         logEvent("Skip some the song(> 15 sec)");
         player.doSkip();
       //  WaitUtility.sleep(15000);
         player.collapse();
         Page.goBack();
       
         
         System.out.println("PerfectFor section is done.");
         
     }
	
     public void testRoyalty_artistRadio() throws Exception
     {  
    	 loginPage.setLocation("10013");
         loginPage.login();
         
        
         navigation.gotoPage("artistRadio");
         logEvent("Play custom music from Artist radio");
         artistRadio.playStationByIndex(1);
         logEvent("listen to couple of songs to end with out skipping");
         player.fullScreen();
         player.play2SongsInArow();// Driver quits unexpectedly here in appium 5.1.2.
         logEvent("skip");
         player.doSkip();
         logEvent("pause");
         player.play_pause();
         
         WaitUtility.sleep(2000);
         logEvent("play");
         
         player.play_pause();
         logEvent("ThumbDown");
         player.doThumbDown("artist");
         player.collapse();
         Page.goBack();
    
        
         System.out.println("ArtistRadio section is Done.");   
     }    
	  
	  
     public void testRoyalty_myStations() throws Exception
     {   
        
    	 loginPage.setLocation("10013");
         loginPage.login();
         
        
         navigation.gotoPage("home");
         
         //custom
         logEvent("play custom station from My station");
         home.gotoSubMenu("My Stations");
         ///shit, how do i know that it is live or custom?
         //home.playStationByName("Josh");
         home.search("josh");
        // player.fullScreen();
         logEvent("skip > 15 sec");
         player.doSkip();
         WaitUtility.sleep(16000);
        // logEvent("share");
        // player.doShare();
         //here,  to dismiss the share popup first by clicking on action tool bar
       //  player.dismissPossiblePopup();
         
         logEvent("skip < 15 sec");
         player.doSkip();
         WaitUtility.sleep(2000);
         player.collapse();
         
         //podCast
         logEvent("podcasts from My Stations");
         //how the hack i KNOW WHICH IS PODCAST? sEARCH ONE to start with
         try{
            podcasts.playAskDave();
         }catch(Exception e )
         {
        	 e.printStackTrace();
         }
         player.fullScreen();//just to bring it to full screeen
         
         logEvent("Thumb up");
         player.doThumbUp("podcasts");
         
         logEvent("skip");
         player.doSkip();
         
         
         logEvent("Thumb down");
         player.doThumbDown("podcasts");
         player.collapse();
         
         /*********** live **********/
         Page.goBack();
         navigation.gotoPage("home");
         home.gotoSubMenu("My Stations");
         logEvent(" Live from my station");
      //   home.playStationByName("z100");
         Page.search("z100");
     
         logEvent("scan");
         player.doScan();
         logEvent("played live station from For you");
         player.collapse();
         home.gotoSubMenu("FOR YOU");
        // home.playStationByName("105.5");
         home.search("105.5");
         logEvent("custom station from home-for you");
        // home.playStationByName("Shawn");
         player.collapse();
         home.search("Shawn");
        // player.fullScreen();
         logEvent("thumb up");
         player.doThumbUp();
         logEvent(" play my favorite radio station");
         player.collapse();
         Page.search("Favorites");
         //player.fullScreen();
         logEvent("thumb up");
         player.doThumbUp();
         logEvent("skip");
         player.doSkip() ;
         logEvent("thumb down");
         player.doThumbDown();
        
         
         player.collapse();
         Page.goBack();
        
     
         System.out.println("MyStations Section is Done.");
     }     
	  

	
    public static void logEvent(String eventName)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date();
		System.out.println(dateFormat.format(date) + "------" + eventName ); //2015/08/06 15:59
		eventLog.put(eventCount + ". " + eventName , dateFormat.format(date));
		eventCount++;
	}
    
    
   public static Map getEventLog()
   {
   	return eventLog;
   }

   

   public void testScroll() throws Exception
   {   
      
      loginPage.setLocation("10013");
       loginPage.login();
       
      
    
       navigation.scrollDown();
       
   }
   
}
