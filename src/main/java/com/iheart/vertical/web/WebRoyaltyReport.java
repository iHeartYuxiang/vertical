package com.iheart.vertical.web;

import java.util.LinkedHashMap;

import com.iheart.vertical.abstractLayer.*;
import com.iheart.vertical.abstractLayer.*;

import com.iheart.vertical.utils.*;

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


public class WebRoyaltyReport {
	
	static HashMap<String, String> eventLog;
	static int eventCount = 1; 
	
	WebNavigation navigation;
	WebLogin loginPage;
	WebPlayer player;
	WebLiveRadio liveRadio;
	WebMyStations myStations;
	WebArtistRadio artistRadio;
	WebPodcasts podcasts;
	
	
	public WebRoyaltyReport(WebDriver driver)
	{
		 eventLog = new LinkedHashMap<String, String>();
	      
	      eventLog.put("Login account:" + Page.getUserName() + "/" + Page.getPassword(), "WEB");
	      eventLog.put("Step Actions:" , "");
	      

	 	 loginPage = new WebLogin(driver);
	      player = new WebPlayer(driver);
	      Page.setPlayer(player);
	      liveRadio = new WebLiveRadio(driver);
	      myStations = new WebMyStations(driver);
	      artistRadio = new WebArtistRadio(driver);
	      podcasts = new WebPodcasts(driver);
	}
	
	public void testRoyalty_live_artist_Radio() throws Exception   //part 1 
    {   
        
       
            loginPage.login();
            logEvent("login");
            
            
            logEvent("play a live station from For you");
              
            navigation.gotoPage("liveRadio");
            //liveRadio.playFirstStation();
            liveRadio.playStationByIndex(1);
            
          
            //scan for a few times
            logEvent("Scan the station couple of times (> 15 sec )");
	            
             player.doScan();
             WaitUtility.sleep(16000);
            
             logEvent("Scan the station couple of times (< 15 sec )");
             player.doScan();
             WaitUtility.sleep(5000);
            
            logEvent("Thumb up song on live station");
            player.doThumbUp();
            
            logEvent("Thumb down");
            player.doThumbDown();
            
           /****** Customer stations  ******/ 
            logEvent("Switch to custom station from perfect For");
            navigation.gotoPage("perfectFor");
            
            //Stream a customer station
            logEvent("Skip some song( > 15 sec)");
            Page.search("yoga music");
            player.doSkip();
            WaitUtility.sleep(16000);
            
            logEvent("Skip some song( < 15 sec)");
            player.doSkip();
            WaitUtility.sleep(6000);
            
            logEvent("Thumb UP ( < 15 sec)");
            player.doThumbUp();
            logEvent("Thumb down( > 15 sec)");
            player.doThumbDown();
            WaitUtility.sleep(16000);
            
            logEvent("Skip Song");
            player.doSkip();
            WaitUtility.sleep(6000);
            
            logEvent("Thumb Up");
            player.doThumbDown();
            
            //Click on song title to create station?
            logEvent("Click on Song title and create station");
            artistRadio.createAstation();
            
            
            //listen to 2 songs without skipping
            logEvent("Listen to 2 songs to end without skipping.");
            player.play2SongsInArow();
       
	  }     
		  
		  
		  
		 
    public void testRoyalty_podcasts() throws Exception
    {   
        
            loginPage.login();
            logEvent("Switch to live/talk - Elvis Duran Replay Channel");
	            
            Page.search("Elvis Duran Replay Channel");
           
           // WaitUtility.sleep(16000);
            logEvent("Scan live talk ( > 15 secs) ");
            player.doScan();
           
            //Now switch to podcasts
            logEvent("Swtich to Podcast");
            Page.search("Ask Dave");
             
            // comment this part off due to podcast V2 since skip and thumbUP/down behavior has changed for podcasts
            /* 
             
            logEvent("Skip some episodes(> 15 secs)");
            player.doSkip();
            WaitUtility.sleep(16000);
            
            logEvent("Skip some episodes(< 15 secs)");
            player.doSkip();
            WaitUtility.sleep(2000);
            
            logEvent("Thumb Up");
            player.doThumbUp();
            
            logEvent("Skip Episode");
            player.doSkip();
            WaitUtility.sleep(2000);
            
            logEvent("Thumb down episodes(> 15 secs)");
            player.doThumbDown();
            WaitUtility.sleep(16000);
            
            logEvent("Skip Episode");
            player.doSkip();
            WaitUtility.sleep(2000);
            
            logEvent("Skip Episode");
            player.doSkip();
            WaitUtility.sleep(2000);
            
            */
            
            //listen to 2 songs without skipping
            logEvent("Listen to 2 episodes without break");
            player.play2SongsInArow();
            
            logEvent("Switch to Live");
            navigation.gotoPage("liveRadio");
            logEvent("log out");
            loginPage.logout();
            WaitUtility.sleep(8000);
           
            logEvent("Log in again");
            loginPage.login();
            
            logEvent("Play Live station from MY STATIONS page");
            navigation.gotoPage("myStations");
            myStations.playLiveRadio();
            
        
       }    
	  
    public void testRoyalty_myStations() throws Exception
    {   
        
            loginPage.login();
            
            
            logEvent("Play Live station from MY STATIONS page");
            navigation.gotoPage("myStations");
            myStations.playLiveRadio();
            navigation.gotoPage("myStations");
            myStations.deleteAstation();
           // myStations.deleteFirstStation("live");
            
            logEvent("Delete Podcast from MY STATIONS page");
            navigation.gotoPage("myStations");
            myStations.playPodcast();
            navigation.gotoPage("myStations");
            myStations.deleteFirstStation("talk-show");
            
            logEvent("Play a custom station from MY STATIONS");
            navigation.gotoPage("myStations");
            myStations.playArtistRadio();
            
            logEvent("Delete station from MY STATIONS");
            navigation.gotoPage("myStations");
            myStations.deleteFirstStation("artist");
            
            logEvent("Play a custom station from 'For You' tab");
            navigation.gotoPage("forYou");
            myStations.playArtistRadio();
            
            logEvent("Play a live station from 'For You' tab");
            navigation.gotoPage("forYou");
            myStations.playLiveRadio();
            
            logEvent("Search Non O&O from 'Live stations' page (95.5PLJ) & Play Station");
            navigation.gotoPage("forYou");
            Page.search("95.5 PLJ");
            
          
       
    }
 
	
    public static void logEventToFile() throws Exception{
 	   //put all logged events into a file for late verification
 	    DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm");
			Date date = new Date();
		
 	   Utils.outputRoyaltyToExcel(eventLog, "eventLog_"+ dateFormat.format(date) + ".xls");
	 }  
	     
    public static Map getEventLog()
    {
    	return eventLog;
    }
    
		
    public static void logEvent(String eventName)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date();
		System.out.println(dateFormat.format(date) + "----" +eventName ); //2015/08/06 15:59
		eventLog.put(eventCount + ". " + eventName , dateFormat.format(date));
		eventCount++;
	}
    

		    
}
