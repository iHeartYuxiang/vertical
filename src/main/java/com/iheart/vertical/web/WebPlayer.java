package com.iheart.vertical.web;

import com.iheart.vertical.utils.*;
import com.iheart.vertical.abstractLayer.*;
import com.iheart.vertical.utils.*;

import org.openqa.selenium.WebElement;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.runner.Description;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.JavascriptExecutor;



public class WebPlayer extends Player {
	
	/* How to distinguish player in the here from that at the bottom?
	 *   hero player:  css = '.hero-content .icon-play'
	 *   bottom player: css = '#bottom-fixed .icon-play'
	 *  
	 */
	
	
	/************ player in the hero, only appliable for web  *******/
	//meta data
	 @FindBy(css=".hero-content .player-station") public WebElement hero_stationName;
	 @FindBy(css=".hero-content .profile-art") public WebElement hero_stationLogo;
	 
	 @FindBy(css=".hero-content .icon-favorite-unfilled") WebElement hero_fav_icon;
	 @FindBy(css=".hero-content .icon-favorite-filled") WebElement hero_faved_icon;
	 
	 @FindBy(css=".hero-content .icon-stop") public WebElement hero_icon_stop;
	 @FindBy(css=".hero-content .icon-play") public WebElement hero_icon_play;
	 
	 @FindBy(css=".hero-content .station-now-playing") WebElement hero_stationNowPlaying;
	 @FindBy(css=".hero-content .station-now-playing-artist") WebElement hero_stationNowPlayingArtist;
	 
	 
	
	/************ BOTTOM PLAYER *******/

	//for web
		 //meta data
		 @FindBy(css="#bottom-fixed .player-station") public WebElement web_stationName;
		 @FindBy(css="#bottom-fixed .player-song") public WebElement web_songTrack;
		 @FindBy(css="#bottom-fixed .player-artist") public WebElement web_playerArtist;
		 @FindBy(css="#bottom-fixed .icon-more-horizontal") public WebElement web_more;
			
		    
			@FindBy(css="div.align-left:nth-child(3) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)") 
				public WebElement web_share;
			//Add to favorite
			@FindBy(css="#bottom-fixed .favorite") protected WebElement web_favorite;
			@FindBy(css="#bottom-fixed .icon-favorite-filled") protected WebElement web_icon_fav_filled;
			@FindBy(css="#bottom-fixed .icon-favorite-unfilled") protected WebElement web_icon_fav_unfilled;
			
			
			@FindBy(css=".dialog-title") public WebElement web_sharePageTitle;
			@FindBy(css="button._42ft:nth-child(2)") public WebElement web_shareOnFacebook;
			
			//Common for live radios and custom radios
			//thumbUp
			//#player > div.player-center > div > button:nth-child(2) > i
			
			
			@FindBy(css="button.medium:nth-child(2)") protected WebElement web_thumbUp_button;
			@FindBy(css="button.medium:nth-child(1)") protected WebElement web_thumbDown_button;
			
			@FindBy(css="#bottom-fixed .icon-thumb-up-unfilled") protected WebElement web_thumbUp_unfilled;
			@FindBy(css="#bottom-fixed .icon-thumb-up-filled") protected WebElement web_thumbUp_filled;
			 
		    @FindBy(css="#bottom-fixed .icon-thumb-down-unfilled") protected WebElement web_thumbDown_unfilled;
		    @FindBy(css="#bottom-fixed .icon-thumb-down-filled") protected WebElement web_thumbDown_filled;
			

		
		
		//GROWLS
			//@FindBy(css=".growls") public WebElement growls;
			@FindBy(css="#page-view-container > div > div.growls") public WebElement web_growls;
			
			@FindBy(css="#bottom-fixed .icon-skip") public WebElement web_icon_skip;
			@FindBy(css="button.btn:nth-child(4)") public WebElement web_icon_scan;
		
			//player buttons
			
			@FindBy(css="#bottom-fixed .icon-play") public WebElement web_icon_play;
		
			@FindBy(css="#bottom-fixed .icon-stop") public WebElement web_icon_stop;
			@FindBy(css="#bottom-fixed .icon-pause") public WebElement web_icon_pause;
			
			//info from sliders
			@FindBy(css="#bottom-fixed .player-duration-duration") public WebElement web_songDuration; //How long is the song?
			@FindBy(css="#bottom-fixed .player-duration-position") public WebElement web_songLapsed; //Has played for how long?
			
			
			
			@FindBy(css="#player > div.player-right.ui-on-dark > button:nth-child(2) > span:nth-child(3)") 
				public WebElement web_listenHistory;
			@FindBy(css="#player > div.player-right.ui-on-dark > button:nth-child(1) > span:nth-child(3)")
			    public WebElement web_myStations;
			
		   
	
	public WebPlayer()
	{
		this(driver);
	}
	public WebPlayer(WebDriver driver)
	{
		super(driver);
		
	}
	
	public void hero_play()
	{
		hero_icon_play.click();
		waitForPreroll();
	}
	
	public  void fullScreen()
	{
		
	}
	
	public  void handleThumbDownPopUpForArtistStation()
	{
		//do nothing
	}

	public  void handleGladYouLikeItPopup(){
		
	}
	
	public  void handleGladAfterFavorite()
	{
		
	}
		
	
	public  void handleUnFavConfirmation()
	{
		
	}
		
	
	public void locateElements()
	{
		stationName = this.web_stationName;
		 songTrack = this.web_songTrack;
		 playerArtist = this.web_playerArtist;
		
		 more = this.web_more;
		 favorite = this.web_icon_fav_unfilled;
		 share =this.web_share;
		  
		  thumbUp_button = this.web_thumbUp_button;
		  thumbDown_button = this.web_thumbDown_button;
		  
		  thumbUp_unfilled = this.web_thumbUp_unfilled;
		  thumbDown_unfilled = this.web_thumbDown_unfilled;
		  
		  thumbUp_filled = this.web_thumbUp_filled;
		  thumbDown_filled = this.web_thumbDown_filled;
		  
		  growls = this.web_growls;
		  
		  play = this.web_icon_play;
		 pause = this.web_icon_pause;
		 stop = this.web_icon_stop;
		 
		  skip = this.web_icon_skip;
		 scan = this.web_icon_scan;
		  
	//	 volumnIcon;
	//	 volumnBar;
		 songDuration = this.web_songDuration;
		 songLapsed = this.web_songLapsed;
		 
		
		// fullScreen;
	//	 collapseScreen;
			
		

		//FOR SHARE
		// public WebElement mail;
	}
	
	public  void handlePreRoll()
	{
		WaitUtility.sleep(45000);
	}
	
	
	public void back()
	{
		//do nothing
	}
	
	
	public  void dimsissRecommendation_skipLimit()
	{
		
	}
	
}
