package com.iheart.vertical.abstractLayer;

import com.iheart.vertical.utils.*;

import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class Player extends Page{
	
	
	public WebElement stationPlaying;
	
	public WebElement dismissButton; //For popupS
	
	/************ player in the hero, only appliable for web  *******/
	
	
	/************ mini player, only appliable for mobile  *******/
	public WebElement  mini_playPause;
	public WebElement  mini_trackName;
	public WebElement  mini_artist;
	public WebElement  mini_thumbDown;
	public WebElement  mini_thumbUp;
	
	
	
	/***********  bottom player ************/
	
	public  WebElement stationName;
	public WebElement songTrack;
	public WebElement playerArtist;
	
	//info from sliders
	public WebElement songDuration; //How long is the song?
	public WebElement songLapsed; //Has played for how long?
	
	
	public WebElement more;
	  public WebElement favorite;
	  public WebElement share;
	  
	 public  WebElement thumbUp_button; 
	 public  WebElement thumbDown_button; 
		
	 public WebElement thumbUp_unfilled;
	 public WebElement thumbDown_unfilled;
	 
	 public WebElement thumbUp_filled;
	 public WebElement thumbDown_filled;
	 
	 
	
	 public WebElement play;
	 public WebElement pause;
	 public WebElement stop;
	 
	 
	  public WebElement skip;
	  public WebElement scan;
	  
	
	 public WebElement volumnIcon;
	  public WebElement volumnBar;
	
	  public WebElement fullScreen;
	  public WebElement collapseScreen;
		
	
	  public WebElement growls;
	  
	
	//FOR SHARE
	 public WebElement mail;
    
	
	public Player()
	{
		super();
	}
	
	public Player(WebDriver _driver)
	{
		super(_driver);   
		setPlayer(this);
	}
	
	public void play()
	{  WaitUtility.sleep(5000);
	   	play.click();
		waitForPreroll();
	}
	 
	
	
	//Why only 2 elements?
	private void verfiyCommonIcons(String callingMethod)
	{
		if(!isElementPresent(stationName))
		{    errors.append("No station name is displayed.");
		}
		
		if(!isElementPresent(songTrack))
		{    errors.append("No sound track name is displayed.");
		}
		
		if(!isElementPresent(play))
			   errors.append("Play icon is not displayed.");
		
		/*
		if(!isElementPresent(scan) )
			   errors.append("Scan icon is not displayed.");
		*/
		
		if(!isElementPresent(more))
			   errors.append(".... is not displayed.");
		
		
		if(!isElementPresent(thumbUp_filled) || isElementPresent(thumbUp_unfilled))
			   errors.append("No Thumb Up icon is displayed.");
		
		if(!isElementPresent(thumbDown_filled) || isElementPresent(thumbDown_unfilled))
			   errors.append("No Thumb Down icon is displayed.");
		
		
		//if(!isElementPresent(more))
		//	   errors.append(".... is not displayed.");
		
		if(errors.length() > 1)
			handleError("", callingMethod);
	}
	
	public void doSkip(String type)
	{   fullScreen();
	
		String currentTrack, nowPlaying;
		currentTrack = getNowPlaying(type);
		
	    //skip.tap(1, 1);
		skip.click();
	    WaitUtility.sleep(2000);
	    nowPlaying = getNowPlaying(type);
	    System.out.println("before/after:" + currentTrack + "/" + nowPlaying);
	    //Verify new episode is playing
	    if (currentTrack.equals(nowPlaying))
	    	handleError("Skip is not working.", "doSkipFor" + type);
	}
	
	
	
	
	public void doShare()
	{  
		if (Page.mediaType.equals("web"))
	     	more.click();
		
		share.click();
	
	}   
	
	
	//default to live Radio
	public void doThumbUp()
	{
		doThumbUp("liveRadio");
	}
	
	
	public void doThumbUp(String stationType)
	{    waitForPreroll();
		 dismissAllPopups();
		//Sometimes the thumbUp button is disabled, keep scan(At most 10 times though to avoid hang) until thumbUpiCON is enabled.
		int count = 0; 
		
		//Try a little bit more
		while(isThumbUpDisabled() && count < 3)
		{	System.out.println("thumbUp button is disabled. Scan or skip now..");
		    try{ 
		       if(stationType.contains("live"))	
			       scan.click();
		       else 
		    	   skip.click();
		    }catch(Exception e)
		    {   
		    	
		    }
			count++;
			WaitUtility.sleep(3000);
		}
		
		//if it is still disabled, return 
		if(isThumbUpDisabled()) return;
		
		//If this is thumbUp before, double-click
		if (isThumbUpDone())
		{	thumbUp_button.click();
		     WaitUtility.sleep(1000);
			//FOR IOS there is popups
		     //Sometimes 'Like iheartRadio?" pops up
		     if (!Page.mediaType.equals("web"))
		     {	dismissAllPopups();
		     }
		}

	    try{ 
	       thumbUp_button.click();
	    }catch(Exception e)
	    {
	    	//In case it is not full screen mode
	    	mini_thumbUp.click();
	    	fullScreen(); //in case there are skip, scan etc.. going on in the next step
	    }
		//Glad you like it!  We'll let our DJs know.
	  //  verifyGrowls();	
		//Sometimes 'Like iheartRadio?" pops up
		if (!Page.mediaType.equals("web") )
		{	
			dismissAllPopups();
			
			//handleSharePrompt();
			
		}
	}
	
	//public abstract void handleSharePrompt();
	
	//this needs to be tested
	private boolean isThumbUpDisabled()
	{   
		String disableValue ="";
		try{
		    disableValue =  thumbUp_button.getAttribute("disabled");
			
		}catch(Exception e)
		{
			
		}
		
		if (disableValue == null) return false;
		
		return disableValue.equals("true");
	
	}
	
	private boolean isThumbDownDisabled()
	{   String disableValue ="";
		try{
		    disableValue =  thumbDown_button.getAttribute("disabled");
			  
		}catch(Exception e)
		{
			
		}
		if (disableValue == null) return false;
		
		return disableValue.equals("true");
	}
	
	
	//This is for live radio
	public void doThumbDown()
	{
		doThumbDown("liveRadio");
		
	}
	
	
	public void verifyGrowls()
	{   if (Page.mediaType.equals("android"))
		   return;
	
		String response = getGrowls();
		System.out.println("See  growls:" + response);
		
		
	//	if (! response.contains("heard enough"))
	//		handleError("Thump Down is not working properly.", "doThumbDown");
		
	}
	
	
	public String getGrowls()
	{
		int count = 0;
	    String growlsText ="";
	    do {
	    	growlsText = growls.getText();
		    System.out.println("See growls:" + growlsText);
		    if (growlsText!= null && growlsText.length() > 1)
		    	break;
		    count++;
		    WaitUtility.sleep(500);
	    }while (count <3 && growlsText.length() < 3);
	    
	    return growlsText;
	}
	
	public void doThumbDown(String stationType)
	{   waitForPreroll();
		dismissAllPopups();
		//Sometimes the thumbUp button is disabled, keep scan(At most 10 times though to avoid hang) until thumbUpiCON is enabled.
		int count = 0; 
		
		//Try a little bit more
		while(isThumbDownDisabled() && count < 3)
		{	System.out.println("thumbDown button is disabled. Scan or skip now..");
		    try{
		       if(stationType.contains("live"))	
			      scan.click();
		       else
		    	   skip.click();
		    }catch(Exception e)
		    {   
		    	
		    }
			count++;
			WaitUtility.sleep(3000);
		}
		
		//if it is still disabled, return 
		if(isThumbDownDisabled()) return;
		
		//If this is thumbUp before, double-click
		if (isThumbDownDone())
		{	thumbDown_button.click();
		     WaitUtility.sleep(1000);
		   //Sometimes 'Like iheartRadio?" pops up
		     if (Page.mediaType.equals("ios"))
		    	 handleGladYouLikeItPopup();
		     
		     if (Page.mediaType.equals("android"))
		    	 handleThumbDownPopUpForArtistStation();
			
		}
		
		
		try{
	    	thumbDown_button.click();
		}catch(Exception e)
		{
			//In case that player is still loading
		    WaitUtility.sleep(5000);
		    try{
		       thumbDown_button.click();
		    }catch(Exception ex)
		    {
		    	// in case it is not full screen mode
		    	mini_thumbDown.click();
		    	fullScreen(); //in case there are skip, scan etc.. going on in the next step
		    }
		}
		WaitUtility.sleep(5000);
		if (!Page.mediaType.equals("web") )
			handleThumbDownPopUpForArtistStation();
														
		
		
	}
	
	private boolean isThumbUpDone()
	{
		try{
		   thumbUp_filled.getAttribute("class");
		   return true;
		   
		}catch(Exception e)
		{
		    return false;	
		}
	}
	
	private boolean isThumbDownDone()
	{
		try{
		   thumbDown_filled.getAttribute("class");
		   return true;
		   
		}catch(Exception e)
		{
		    return false;	
		}
	}
	
	public void doFavorite()
	{   //if faved before, its value is 1;
		if (isFavDone()) //unfav it
		{
			favorite.click();
			WaitUtility.sleep(1000);
			handleUnFavConfirmation();
		}
		WaitUtility.sleep(1000);
		favorite.click();
		WaitUtility.sleep(500);

		handleGladAfterFavorite();
		  
	}
	
	//Are you sure you want to delete this preset?
	public abstract void handleUnFavConfirmation();
	
	
	
	private boolean isFavDone()
	{
		boolean isDone = false;
		
	    try{
	    	isDone = favorite.getAttribute("value").equals("1");
	    }catch(Exception e)
	    {
	    	
	    }
	    
	    return isDone;
	}
	
	public void doScan()
	{  /*
	      Need to handle the case when audio commercial is on.
	
	    */
		//first, need to check whether or not Scan button is disabled. 
		
		//many times, song track info is missing from bottom player
		String currentSong = "";
		try{
		   currentSong = songTrack.getText();
		   System.out.println("Current Song:" + currentSong);
		}catch(Exception e)
		{
			System.out.println("Song track info is missing..");
		}
	
		try{
	    	scan.click(); //Scan button could be greyed out when commercial is on? 
		}catch(Exception e)
		{   
			WaitUtility.sleep(15000);
			scan.click();
		}
		waitForPreroll(); 
		//Verify that new song is playing 
	    if (!currentSong.equals(""))
	    {	
			String newSong = "";
			try {
				//in case song track info is not available;
				newSong = songTrack.getText();
			}catch(Exception e)
			{
				
			}
			
		    if (!newSong.equals("") && newSong.equals(currentSong))
				handleError("Scan is not working.", "doScan");
	    }   
		
	}
	
	public void doSkip()
	{   
		//check if player is not in full screen mode, make it fullscreen first
		try{
		   if (mini_thumbUp.isDisplayed())
			   fullScreen();
		}catch(Exception e)
		{
			//Good. in FULL SCREEN MODE already
		}
		
		//Some radio doesn't have this field, such as Yoga music radio.
		String currentSong = "";
		try{
			currentSong = songTrack.getText();
		}catch(Exception e)
		{
			System.out.println("No song track info is provided.");
		}
		try{
		   skip.click();
		}catch(Exception e)
		{
			// in case of popup
			WaitUtility.sleep(5000);//Give it some time
			dismissAllPopups();
			skip.click();
		}
		WaitUtility.sleep(5000);
		
		//handle possible 'Skip limit is reached' ISSUE?
		try{
		    if (!Page.mediaType.equals("web"))
		    	dimsissRecommendation_skipLimit();
		    return; 
		}catch(Exception e)
		{
			
		}
		waitForPreroll();//N
		
		//Verify that new song is playing 
		if  (!currentSong.equals(""))
		{	
			String newSong = songTrack.getText();
			System.out.println("currentSong/NextSong:" + currentSong + "/" + newSong);
			if (newSong.equals(currentSong))
				handleError("Skip is not working.", "doSkip");
		}	
		
		
		//Need to handle possible popups like 'Recommended for You' 
		
		dismissAllPopups();
			
	}
	
	
	public void play_pause()
	{
		mini_playPause.click();
	}
	
	
	
	public void pauseAndResume(String type)
	{   WebElement theOne;
	    play.click();
		//verify it is paused
	    if(!play.getAttribute("name").contains("play"))
	    	errors.append("Station playing is not paused.");
	    
	    play.click();
	    //verify it is resumed
	    if(!play.getAttribute("name").contains("pause"))
	    	errors.append("Station playing is not RESUMED.");
	    
	}
	
	public boolean isPlaying(String type)
	{  
		boolean isPlaying = false;
		
		WebElement theOne;
	    
	  //verify that it is playing: Get its attribute: class shall be 'pause'
	    String klasses = play.getAttribute("name");
	    System.out.println("See playbutton classes:" + klasses);
	    if (klasses.contains("pause") || klasses.contains("stop"))
	    	isPlaying = true;
		
	    return isPlaying;
	}
	
	private String getNowPlaying(String type)
	{  
	    return songTrack.getText();
	}
	
	public void makeSureItIsPlaying()
	{   
	    try{

		    play.isDisplayed();
	    	play.click();

	    }catch(Exception e)

	    {  // System.out.println("Music is playing. ");
	    	return;
	    }
	    
	    handlePreRoll();
	    
	}
	
	
	 public void play2SongsInArow()
	 {
	    	/*
	    	 * What's the song playing now?
	    	 * Wait for 5 minutes, is a new song playing? If yes, wait for another 5 minutes?
	    	 * if not, wait for another 2 seconds? 
	    	 * 
	    	 * How to make sure that it is just 2 songs?
	    	 */
	    	String currentSong = songTrack.getText();
	    	String _songDuration = songDuration.getText();
	    	System.out.println("songDuration:" + _songDuration);
	        //WaitUtility.sleep(convertToMillisecond(_songDuration));
	    	WaitUtility.sleep(5000);//Just to see whehter or not this will make driver crush go
	    	
	        //This is really weired, but here I see popup show up
	        System.out.println("Next song is about to play");
	        WaitUtility.sleep(1000);
	        Page.dismissAllPopups();
	        
	      //  System.out.println("Done dismissing all popups.");
	       
	        //Check song again: 
	        String nextSong = songTrack.getText();
	        System.out.println("see current/next song:" + currentSong + "/" + nextSong);
	       //HERe: something is really weired: The player simply won't get to the next song
	        Page.dismissAllPopups();
	        
	        if (currentSong.equals(nextSong))
	        {   
	        	WaitUtility.sleep(2000);
	        }
	        
	        
	        //Wait till second song is done. What about audio commerial handling?
	        _songDuration = songDuration.getText();
	    	System.out.println("next songDuration:" + _songDuration);
	       // WaitUtility.sleep(convertToMillisecond(_songDuration));
	    	WaitUtility.sleep(5000);//Just to see whehter or not this will make driver crush go
	    	
	    	
	    }
	    
	    //duration format: 4:46
	    private long convertToMillisecond(String duration)
	    {
	    	int minutes = Integer.parseInt(duration.split(":")[0]);
	    	int seconds = Integer.parseInt(duration.split(":")[1]);
	    	
	    	long millis = (minutes * 60 * 1000 ) +  (seconds * 1000);
	    	System.out.println("See songduration in milli:" + millis);
	    	return millis;
	    }
	    
	    
	public boolean isCommercialOn()
	{   //if audio commercial is on, thumbup/down button is disabled. 
		
		return isThumbUpDisabled();
	}
	
	public String getStationPlaying()
	{
		return stationPlaying.getText();
	}
	
	//to be done
	public abstract void handlePreRoll();
	
	
	//Thumbing down customizes your station without using a skip.
	public abstract void handleThumbDownPopUpForArtistStation();
	
	//This happens when you thumbup a already thumbuped song track
	public abstract void handleGladYouLikeItPopup();
	
	
	public abstract void handleGladAfterFavorite();
	
	public abstract void dimsissRecommendation_skipLimit();
	
    public abstract void fullScreen();
	
	
	
}
