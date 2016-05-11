package com.iheart.vertical.abstractLayer;

public interface MobilePlayer {
	
   void fullScreen();
   
   void collapse();

   //Thumbing down customizes your station without using a skip.
   void handleThumbDownPopUpForArtistStation();
    
   void setSleepTimer();
   
   void setAlarmClock();
   
   void exitApp();
   
   void logoutFromSetting();
   
   void dismissPossiblePopup();
   
   void dismissLikeIheartPopup();
   
}
