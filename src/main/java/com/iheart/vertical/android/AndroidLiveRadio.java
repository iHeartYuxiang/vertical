package com.iheart.vertical.android;

import com.iheart.vertical.utils.*;
import com.iheart.vertical.abstractLayer.*;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AndroidLiveRadio extends LiveRadio {
	/*  main page structure
	id: com.clearchannel.iheartradio.controller:id/view_pager
	TYPE; android.support.v4.view.ViewPager   
				android.widget.GridView
				    android.widget.FrameLayout  //first station 
				         1. com.clearchannel.iheartradio.controller:id/station_logo  //THE BIG SQUARE ICON
				         2. com.clearchannel.iheartradio.controller:id/info  //i ICON
				         3. com.clearchannel.iheartradio.controller:id/event_text   //StationName
				         4. com.clearchannel.iheartradio.controller:id/event_sub_text  //Artist, or new york's best variety...
				
	*/
	
	/*  Live radio sub-menu:
	 * com.clearchannel.iheartradio.controller:id/indicator, then type: android.widget.TextView
	 */
	@AndroidFindBy(id = ANDROID_RES_ID_PREFIX + "indicator") public AndroidElement subMenu; //CITIS, stationS near you..
    By findSubMenuOptionBy = By.className("android.widget.TextView");
	
	 //Submenu:
    public AndroidElement  android_cities;
    public AndroidElement  stationsNearYou;
    public AndroidElement  musicAndEntertain;
     
    public AndroidElement chosenCity;
   
	
   
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/view_pager")
	   private AndroidElement android_stationTiles;
	/*
	By findStationBy = By.className("android.widget.RelativeLayout");
	By findStationlogoBy = By.id("com.clearchannel.iheartradio.controller:id/station_logo");
	By findStationInfoBy = By.id("com.clearchannel.iheartradio.controller:id/info");
	By findStationNameBy = By.id("com.clearchannel.iheartradio.controller:id/event_text");
	By findStationDescriptionBy  = By.id("com.clearchannel.iheartradio.controller:id/event_sub_text");
	*/
	
		 
    // This shall be removed 
	/*
	public  AndroidElement android_firstStation;

	public  AndroidElement android_firstStationLabel;

	public  AndroidElement android_stationPlaying;

	*/
	//for Glad you like it popup
   @AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/share_station")
      private AndroidElement android_shareStation;
   
	
	
	
	public AndroidLiveRadio()
	{
	   this(driver);
	}
	
	public AndroidLiveRadio(WebDriver _driver)
	{
	   super(_driver);
	}
	
	
	public void gotoCities()
	{
		gotoSubMenu(0);
	}
	
	public void gotoStationNearYou()
	{
		gotoSubMenu(1);
	}
	
	
	public void gotoMusicAndEntertain()
	{
		gotoSubMenu(2);
	}
	
	public void gotoSubMenu(int index)
	{
		android_cities = (AndroidElement) subMenu.findElements(findSubMenuOptionBy).get(index);
		android_cities.click();
		WaitUtility.sleep(2000);
	}
	
	public void chooseCountry()
	{
		//Do nothing
	}
	
	public void chooseCity()
	{
		//choose state, choose city
		gotoCities();
	    chosenCity = (AndroidElement)driver.findElements(By.id("com.clearchannel.iheartradio.controller:id/item_name")).get(5);

		chosenCity.click();
	}
	
	public void chooseGenre()
	{
		//DO NOTHING
	}
	
	public boolean isPlaying()
	{   boolean isPlaying = false;
		try{
			   driver.findElement(By.cssSelector("button.idle:nth-child(3)"));
		}catch(Exception e)
		{
			isPlaying = true;
		}
		System.out.println("isPlaying:" + isPlaying);
		return isPlaying;
	}
	
	
	public  void dismissPopup()
	{
		
	}
	   
	
	
	public void locateElements()
	{   
	    stationTiles = this.android_stationTiles;
	    chooseStationBy  =  By.className("android.widget.RelativeLayout");;
		 chooseStationLogoBy = By.id("com.clearchannel.iheartradio.controller:id/station_logo");;
		 chooseStationNameBy =  By.id("com.clearchannel.iheartradio.controller:id/event_text");;
		 chooseStationDescriptionBy = By.id("com.clearchannel.iheartradio.controller:id/event_sub_text");
		 
		 //for popups
		 shareStation = this.android_shareStation;
			
	
	}


}
