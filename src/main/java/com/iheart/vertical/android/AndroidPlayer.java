package com.iheart.vertical.android;


import com.iheart.vertical.abstractLayer.*;
import com.iheart.vertical.utils.WaitUtility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import  org.openqa.selenium.*;

import java.util.List;
import java.util.ArrayList;

public class AndroidPlayer extends Player implements MobilePlayer {
	
	   //**************** for miniplayer ******//
	
	  @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/player_toolbar")
	     public AndroidElement android_player_toolbar;
	  
	  @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/playPauseProgress")
	  	public AndroidElement androidMini_playPause;

	  @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/miniplayer_top_text")
	  		public AndroidElement androidMini_trackName;
	  @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/miniplayer_bottom_text")
		public AndroidElement androidMini_artist;
	  
	   @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/miniplayer_thumbs_up")
	       public AndroidElement androidMini_thumbUp;
	   @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/miniplayer_thumbs_down")
	   		public AndroidElement androidMini_thumbDown;
   
	  
	
	   //*************  Full screen **********//
	   
	   
	   @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/player_toolbar")
	      public AndroidElement player_toolbar;
	  
	   //how to find each element on this toolbar?
	   private By findCollapseButtonBy = By.className("android.widget.ImageButton"); 
	   private By findStationNameBy = By.className("android.widget.TextView"); 
	   private By findShareButtonBy = By.name("Share station");
	   private By findMoreBy = By.name("More Options");
	   
	   
	   @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/toolbar_actionbar")
  		    public AndroidElement actionBar;
	   private By actionBar_findStationNameBy = By.className("android.widget.ImageView"); 
	   
	   //For share
	   @AndroidFindBy(id="android:id/select_dialog_listview") 
	   		private AndroidElement android_shareMenu;
	   /*
	    * 1. Messaging; 2: FaceBook; 3. BlueTooth; 4.Copy Link
	    */
	   private By findMenuOptionBy = By.className("android.widget.TextView");
	   
	   //share by messaging
	   @AndroidFindBy(id="com.android.mms:id/recipients_editor") 
	     	private AndroidElement  to;
	   @AndroidFindBy(id="com.android.mms:id/embedded_text_editor")
	   		private AndroidElement  msgText;
	   @AndroidFindBy(id="com.android.mms:id/button_with_counter")
  		private AndroidElement  send;
  
	   
	   //What song is playing? 
	   // //  com.clearchannel.iheartradio.controller:id/player_info_container  --> android.widget.TextView
	   @AndroidFindBy(id="android:id/text1")
			   private AndroidElement android_songTrack;
	   @AndroidFindBy(id="android:id/text2")
			   private AndroidElement android_artist;
			
	  //Song duration slide bar for artist radio and podcast
	   @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/progressView")
	       private AndroidElement android_progressViewBar;
	   @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/timeTotal")
			   private AndroidElement android_songDuration;
	   @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/timeElapsed")
			   private AndroidElement android_songLapsed;
			
	   
	  // com.clearchannel.iheartradio.controller:id/playPauseProgress
	   @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/player_play_pause_buffer")
		  private AndroidElement android_play;
		
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/button_player_next")	
			private AndroidElement android_skip;
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/dismiss")
		    private AndroidElement android_dismissButton;
	   	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/button_player_next")	
			public AndroidElement android_scan;
		
		//thumb up and thumb down
		
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/button_player_thumbup")
			private AndroidElement android_thumbUp_button, android_thumbUp_unfilled, android_thumbUp_filled;
		
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/button_player_thumbdown")
			private AndroidElement android_thumbDown_button, android_thumbDown_unfilled, android_thumbDown_filled;
		
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/dismiss_dialog")
		private AndroidElement android_dismissDialog;
		
		//for popup
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/subtitle") 
		    private AndroidElement thumbDownPopUpMsg;
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/got_it")
		 private AndroidElement gotItButton;
		
		//Another type of popup: Recommended for You
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/dismiss")
		    private AndroidElement noThanksButton;
		
		
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/ads_container")
			private AndroidElement android_growls;
		
		@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/button_player_favorite")
		   private AndroidElement android_favorite;

		//FOR SHARE
		@AndroidFindBy(name="Mail") public AndroidElement android_mail;
	    
		
	/*****   for Alarm Clock  ********/
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/on_off_btn")
	    private AndroidElement alarm_on_off_btn;
    @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/snooze_text")
    private AndroidElement alarm_snoozeText;
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/ok")
	    private AndroidElement alarm_ok;
	
	public AndroidPlayer(WebDriver driver)
	{
		super(driver);
	}

	
	
	public  void handleGladYouLikeItPopup()
	{
		try{
		   driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/dismiss_dialog")).click();
		}catch(Exception e)
		{
			
		}
	}
	
	
	
	public  void handleGladAfterFavorite(){
		
	}
	

	public  void handleUnFavConfirmation()
	{
		
	}
	
	
	//
	public void handleThumbDownPopUpForArtistStation()
	{
		try{
		  gotItButton.click();
		}catch(Exception e)
		{
			
		}
		
		try{
			  noThanksButton.click();
		}catch(Exception e)
		{
			
		}
	}
	
	public void fullScreen_old()
	{   //if run into audio commercial, then there will be no aritst/track info
		try{
	    	androidMini_artist.click();
	    	WaitUtility.sleep(1000);
		}catch(Exception e)
		{   
			//e.printStackTrace();
			//This happens often because that it is still loading, or pre-roll is kicked
			WaitUtility.sleep(35000);
			androidMini_artist.click();
	    	WaitUtility.sleep(1000);
		}
	}
	
	public void fullScreen()
	{   
		//if it is full screen already, simply return
		if(isFullScreen()) 
		   return;
		
		//if run into audio commercial, then there will be no aritst info
		try{
			androidMini_trackName.click();
	    	WaitUtility.sleep(1000);
		}catch(Exception e)
		{   
			//e.printStackTrace();
			//This happens often because that it is still loading, or pre-roll is kicked
			WaitUtility.sleep(35000);
			androidMini_trackName.click();
	    	WaitUtility.sleep(1000);
		}
	}
	
	private boolean isFullScreen()
	{
		try{
			  player_toolbar.findElement(findCollapseButtonBy);
			  return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public void pauseMiniPlayer()
	{
		androidMini_playPause.click();
	}
	
	public void collapse()
	{
		
		try{
		  player_toolbar.findElement(findCollapseButtonBy).click();
		}catch(Exception e)
		{
			//if it is already collapsed, do nothing and simply return
		//Could also because there is popups
			return;
		}
		WaitUtility.sleep(2000);
	    dismissAllPopups();
	}

	//ovveriding the super one
	public String getGrowls()
	{
		return "";
	}
	
	public  void handlePreRoll()
	{
		//DO NOTHING since simulator doesn't have preroll
	}
	
	
	
	public void setSleepTimer()
	{   //Set 15 minutes
		driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/button1")).click();
	}
	
	public void sleepTimerOff()
	{
		driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/sleep_timer_cancel")).click();
	}
	   
    public   void setAlarmClock()
    {
    	alarm_on_off_btn.click();
    	chooseStationForAlarm();
    	alarm_ok.click();
    }
    
    private void chooseStationForAlarm()
    {
    	List<WebElement> stations = driver.findElements(By.id("com.clearchannel.iheartradio.controller:id/station_logo"));
    	
        stations.get(1).click();
    	
    }
	   
    
    public void snooze()
    {
    	alarm_snoozeText.click();
    	List<WebElement>  options = driver.findElements(By.id("android:id/text1"));
    	options.get(1).click();
    	
    }
    
    
    public void snoozeOff()
    {
    	alarm_snoozeText.click();
    	List<WebElement>  options = driver.findElements(By.id("android:id/text1"));
    	options.get(0).click();
    	
    }
    
	public   void exitApp()
	{
		
	}
	   
	public   void logoutFromSetting()
	{
		
	}
	
	public void doShare()
	{
		 share = this.player_toolbar.findElement(findShareButtonBy);
		 super.doShare();
	}
	
	
	public void dismissAllPossibelPopups()
	{
		dimsissRecommendation_skipLimit();
		dismissLikeIheartPopup();
		dismissPossiblePopup();
	}
	
	public void dismissPossiblePopup()
	{
		WebElement _stationName = actionBar.findElement(actionBar_findStationNameBy);
		_stationName.click();
	}
	
	public void dimsissRecommendation_skipLimit()
	{   WaitUtility.sleep(2000);
	   try{
			dismissButton = this.android_dismissButton;
			dismissButton.click();
	   }catch(Exception e)
	   {
		   
	   }
	}
	
	public void doThumbUp()
	{
		super.doThumbUp();
		dismissLikeIheartPopup();
	}
	
	public void dismissLikeIheartPopup(){
		WaitUtility.sleep(2000);
		try{
		  android_dismissDialog.click();
		}catch(Exception e)
		{
			
		}
	}
	
	
	public  void handleThumbDownPopup()
	{
		
	}
	
	
	public  void locateElements(){
		/**for mini player **/
		
		mini_playPause = this.androidMini_playPause;
		
		mini_thumbUp = this.androidMini_thumbUp;
		
		mini_thumbDown = this.androidMini_thumbDown;
			   
		// stationName = this.player_toolbar.findElement(findStationNameBy);
		 songTrack = this.android_songTrack;
		 playerArtist = this.android_artist;
		 
		 songDuration = this.android_songDuration;
		 songLapsed = this.android_songLapsed;
		
		
		   favorite = this.android_favorite;
	//	   share = this.player_toolbar.findElement(findShareButtonBy);  //lazy initiation
		  
		  
		  thumbUp_button = this.android_thumbUp_button;
		  thumbDown_button = this.android_thumbDown_button;
		  
		  thumbUp_unfilled = this.android_thumbUp_unfilled;
		  thumbDown_unfilled = this.android_thumbDown_unfilled;
		  
		  thumbUp_filled = this.android_thumbUp_filled;
		  thumbDown_filled = this.android_thumbDown_filled;
		  
		  growls = this.android_growls;
		 
		  play = this.android_play;
		 
		  skip = this.android_skip;
		 scan = this.android_scan;

	}
	
	
	
	
	
}
