package com.iheart.vertical.android;



import com.iheart.vertical.utils.*;
import com.iheart.vertical.abstractLayer.*;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AndroidPerfectFor extends PerfectFor{
    
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/toolbar_actionbar_container")
		private AndroidElement android_header;
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/tab_list_view")
	  	private AndroidElement android_activityList;
	
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/playlist_list_view")
  	private AndroidElement android_playList;
	
	
	public AndroidPerfectFor()
	{
	   this(driver);
	}
	
	public AndroidPerfectFor(WebDriver _driver)
	{
	   super(_driver);
	}
	
	// public void playLive(By byForActivity, By byForPlayList)
	  
	 public void back()
	 {    //Appium 1.5 doesn't support By.name By.cssSelector any more.
		 //header.findElement(By.name("Navigate up")).click();
		 
		 header.findElement(By.className("android.widget.ImageButton")).click();
		// header.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.ImageButton[1]")).click();
	 }
	
	 public void dismissPopup()
	 {
		 android_header.findElement(By.className("android.widget.TextView")).click();
	 }
	 
	public void locateElements()
	{   
		header = this.android_header;
		activityList =  this.android_activityList;
		findActivityBy = By.className("android.widget.LinearLayout");
	
		playList = this.android_playList;
		//findPlayListBy = By.className("android.widget.ListView");//android.widget.ImageView
		findPlayListBy= By.className("android.widget.LinearLayout");
	}
	
	
	
}
