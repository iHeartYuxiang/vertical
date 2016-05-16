package com.iheart.vertical.abstractLayer;

import com.iheart.vertical.utils.*;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.*;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class Page {
	
	/* Generic page structure
	 *  1. Page header
	 *  2. Page View Container: filter, station tiles, stationLogo, stationName, artistName
	 *  3. Footer 
	 *  4. miniPlayer (web: id="bottom-fxed"; Android: com.clearchannel.iheartradio.controller:id/miniplayer_layout;IOS:      
	 * 
	 * 
	 * 
	 */
	
	public static final String ANDROID_RES_ID_PREFIX = "com.clearchannel.iheartradio.controller:id/";
	
	
	public WebElement header;
	public WebElement subMenu; //Usually contains filter, tiles
	   public WebElement filter; //or subMenu
	   
	   public WebElement stationTiles;
	   public By chooseStationBy;
	   public By choosePlayIconBy;//for web only
	   public By chooseStationLogoBy ;
	   public By chooseStationNameBy;
	   public By chooseStationDescriptionBy;
		  
	   
	  
	public WebElement footer;
	public WebElement miniPlayer;
	
 	
	public static WebDriver driver;
	
	static Player player;
	//Real device requires longer reponse time
	static boolean isRealDevice = false;
	Logger logger = Logger.getLogger(Page.class);
	
	static String browser ="";
	public static String mediaType = "web";//could be ios, android, and auto_ios, auto_android
//	static final String USER_NAME ="iheartrocks999@gmail.com";
	//This part shall be moved to run file
	static final String USER_NAME ="iheartrocks888@gmail.com";
	static final String PASSWORD ="iheart001";
	static final String FACEBOOK_USER_NAME = USER_NAME;
	static final String GOOGLE_USER_NAME = USER_NAME;
	
	//for ubiquitous Search function
	private static WebElement searchField;
	private static String searchTerm;
	private static WebElement searchButton;
	private static WebElement firstSearchItem;
	
	//Locate first Search Item here:
	
	@FindBy(css=".selected > div:nth-child(2) > p:nth-child(1) > a:nth-child(1)") private static WebElement web_firstSearchItem;
	
	public static final String screenshot_folder="iosScreenshots";
	public static StringBuffer errors = new StringBuffer(); 
	
	

	/*FOR ANDROID ONLY */
	@AndroidFindBy(id="com.clearchannel.iheartradio.controller:id/footer_ad_fragment") public AndroidElement footerAd;
	
	
	public Page()
	{  
		//PageFactory.initElements(driver, this);
		if (mediaType.equals("ios"))
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);	
		else if (mediaType.equalsIgnoreCase("android"))
		{	PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}else 
		{
			try{
				   PageFactory.initElements(driver, this);
				}catch(Exception e)
				{}
		}
	}
	
	public Page(WebDriver _driver)
	{   
		this.driver = _driver;
		
		//PageFactory.initElements(driver, this);
		if (mediaType.equals("ios") || mediaType.equals("android"))
		{	
			
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
		}else if (mediaType.equalsIgnoreCase("web"))
		{	try{
			   PageFactory.initElements(driver, this);
			}catch(Exception e)
			{}
		}
		
	   locateElements();
	}
	
	public abstract void locateElements();
	
	
	
	public static void search(String searchTerm)
	{   System.out.println("See  mediaType:" + mediaType);
	   if(mediaType.equals("ios"))
	   {   System.out.println("in ios section.");
		   searchButton = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]"));
		   searchField = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIASearchBar[1]"));
		  
		   searchButton.click();
	   }else if (mediaType.equals("web"))
	   {   //System.out.println("in WEB section.");
		   searchField = driver.findElement(By.cssSelector("#page-view-container > div > div.header > div.header-wrapper > div > div.header-right > form > div.form-group.ui-inline-block.search-input > input"));
		   searchField.clear();
		   searchField.sendKeys(searchTerm);
		   WaitUtility.sleep(1000);
		   
		    WebElement firstStationTitle = driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > section:nth-child(1) > ul > li > div > p.title > a"));
				   //resultList.findElements(By.className("title")).get(0);
		   firstStationTitle.click();
		   WaitUtility.sleep(1000);
		   try{
		     driver.findElement(By.className("hero-content")).findElement(By.className("icon-play")).click();
		   }catch(Exception e)
		   {
			   
		   }
		    WaitUtility.sleep(38000);//Wait for preroll
	   }else if (mediaType.equals("android"))
	   {   //   Upgrade to appium 1.5.2 makes  By.name not work, nor By.cssSelector
		   //First, check is here any popups?  Sometimes 'Recommended for You' popup shows up
		   dismissAllPopups();
		   
		   //Then, check whether or not editText field is there already
		   
		   try{
			   //Just to see whether or not text field is there.
			   searchField = driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/search_src_text"));
			   
			   //no need to click on searchButton
		   }catch(Exception e)
		   {
			   clickOnSearchButton();
		   }
		  
		   
		    //  handleGladPopup();
		       try{
		        searchField = driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/search_src_text"));
		    //sometimes a popup 'Glad you like it' would show. How to handle this? 
		     }catch(Exception e)
		     {
		    	 e.printStackTrace();
		    	//Here, sometimes 'Glad you like this station' box, Recommend popup  is popped out
			      
		    	 driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/search_src_text")).click();
		     }
		      
		    try {   
			  searchField.clear();
		    }catch(Exception e)
		    {
		    	dismissAllPopups();
		    }
		    
		    try{
			  searchField.sendKeys(searchTerm);
		    }catch(Exception e)
		    {
		    	dismissAllPopups();
		    	 searchField.sendKeys(searchTerm);
		    }
		    
		    //Sometimes the loading is very slow
			WaitUtility.sleep(5000);
			
			WebElement topHit ;
			try{
			 topHit= driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/best_match_section"));
			}catch(Exception e)
			{
				//in case that it takes long time to load
				WaitUtility.sleep(5000);
				dismissAllPopups();
				 topHit= driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/best_match_section"));
			}
			WebElement matchStation = topHit.findElement(By.id("com.clearchannel.iheartradio.controller:id/station_logo"));
			matchStation.click();
			
			//Shall wait a few second and handle possible 'Glad you like iheart' POPUp
			WaitUtility.sleep(2000);
			
			dismissAllPopups();
			
			//Then make sure that player is in full screen mode
			waitForPreroll();

			
	   }
	   
	   
		
	}
	
	//For MOBILE ONLY
	private static void clickOnSearchButton()
	{
		//searchButton has different xpath depending on which page it is in
		
		try{
			   //Depending on where the search occurs, user might doesn't need to click on search Button
			   String xpath= "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.TextView[1]";
			  try{
			     searchButton = driver.findElement(By.xpath(xpath));
			  }catch(Exception e)
			  {   xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.TextView[1]";
				  searchButton = driver.findElement(By.xpath(xpath));
			  }
			   //searchButton = driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/toolbar_actionbar")).findElement(By.cssSelector("[name='Search']"));
				
			   //searchButton = driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/toolbar_actionbar")).findElement(By.name("Search"));
			   searchButton.click();
		   }catch(Exception e)
		   {
			   e.printStackTrace();
			   
		   } 
		   
		   
	}
	
	public static void dismissAllPopups()
	{    
	   if (Page.mediaType.equals("web")) return;
	   WaitUtility.sleep(3000);
	   try{
		   dismiss(driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/dismiss_dialog")));
	   }catch(Exception e)
	   {
		   
	   }
		
	   try{
		   dismiss(driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/got_it")));
		// private AndroidElement gotItButton;
	   }catch(Exception e)
	   {
		   
	   }
	   try{
		   dismiss(driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/dismiss")));
		 //   private AndroidElement noThanksButton;
		   
		}catch(Exception e)
	   {
		   
	   }
	  
	   ////   private AndroidElement noThanksButton;
		// Recommended for You
	   //gLad you like it, wanna to share? 
	 //  shareButton
	   
	   
	}
	public static void dismiss(WebElement dismissButton)
	{
		try{
			dismissButton.click();
		}catch(Exception e)
		{
			
		}
	}
	
	private static void locateSearchRelatedElements()
	{
		if (mediaType.equals("web"))
		{	searchField = driver.findElement(By.cssSelector("#page-view-container > div > div.header > div.header-wrapper > div > div.header-right > form > div.form-group.ui-inline-block.search-input > input"));
		 
			firstSearchItem = web_firstSearchItem;
		}
	}
	
	
	public boolean  isElementPresent(WebElement element)
	{
		 try{
			  System.out.println("see element:" +  element.getText());
			   return true;
		   }catch(Exception e)
		   {  // e.printStackTrace();
			   return false;
		   }
	}
	
	public static void setBrowser(String _browser)
	{
		browser = _browser;
	}
	
	
	
	//The popup: Like iHeartRadio? Let us know!
	public void likeIheart()
	{
		
	}
	
	public static void setDriver(WebDriver _driver)
	{
		driver = _driver;
	}
	
	public static void setPlayer(Player _player)
	{
		player = _player;
	}
	
	public static Player  getPlayer()
	{
		return player ;
	}
	
	
	public static void setIsRealDevice(boolean isOrNot)
	{
		isRealDevice = isOrNot;
	}
	
	public static boolean getIsRealDevice()
	{
		return isRealDevice ;
	}
	
	
	
	public static void waitForPreroll()
	{
		WaitUtility.sleep(38000);
	}
	
	public static StringBuffer getErrors()
	{
		return errors;
	}
	
	public void handleError(String msg, String methodName) 
	{
		errors.append(msg);
		try{
		   Utils.takeScreenshot( driver,  methodName);
		}catch(Exception e)
		{
			System.out.println("Exception is thrown taking screenshot.");
		}
	}
	
	
	public String  switchWindow()
	{
		//Switch to new tab where the sign-up is
		String winHandleBefore = driver.getWindowHandle();
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}	
		return winHandleBefore;
	}
	
	//go back till nav_icon shows
	public static void goBack()
	{   
		if (mediaType.equals("android"))
		{  
			WebElement actionBar = driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/toolbar_actionbar_container"));
	        
		   WebElement backButton = actionBar.findElement(By.className("android.widget.ImageButton"));
		    
		
		   
		    while (!isNavIconShown()) 
		    {
			   backButton.click();
			   
			}  ;
		}
	}
	
	
	private static boolean isNavIconShown()
	{
		/*   Check elements on actionbar
		 * 1. if EditText, no
		 * 2. any TextView? if not falling into the categories, no
		 * 3. any imageView? cannot be 'Clear Query'
		 */
		
		
		WebElement actionBar = driver.findElement(By.id("com.clearchannel.iheartradio.controller:id/toolbar_actionbar_container"));
		
        
        List<WebElement>  editTexts = actionBar.findElements(By.className("android.widget.EditText"));
		if (editTexts.size() >0)  return false;
			

		 List<WebElement> textViews = actionBar.findElements(By.className("android.widget.TextView"));
         System.out.println("Text COUNT:" + textViews.size() );
         if (textViews.size() >0)
         {	 
             String text = textViews.get(0).getText();
             System.out.println("see text:" + text);
             if (text.equals("Live Radio") ||text.equals("Artist Radio") ||text.equals("Podcasts") ||text.equals("Perfect For"))
            	 return true;
         }

         List<WebElement> images = actionBar.findElements(By.className("android.widget.ImageView"));
         System.out.println("IMAGES  COUNT:" + images.size() );
         
         if (images.size() == 1)
         {	 
        	String  imageName = (images.get(0)).getAttribute("name");
            System.out.println("imageName:" + imageName);
            if (!imageName.contains("Clear"))
            	return true;
         }
         
		return false;
	}
	
	
	public void realDeviceWait(int seconds)
	{
		if (isRealDevice)
			WaitUtility.sleep(seconds);
	}
	
	public static void clearErrors()
	{
		errors.setLength(0);
	}
	
	public static String getUserName()
	{
		return USER_NAME;
	}
	
	public static String getPassword()
	{
		return PASSWORD;
	}
	
	//for mobile
    public void  assignStationfindBy_mobile()
    {
       chooseStationLogoBy = By.id("com.clearchannel.iheartradio.controller:id/station_logo");
  	   chooseStationNameBy = By.id("com.clearchannel.iheartradio.controller:id/event_text");
  	   chooseStationDescriptionBy = By.id("com.clearchannel.iheartradio.controller:id/event_sub_text");
    	
    }
    
    public static void takeScreenshot(WebDriver driver, String testMethod) throws Exception 
    {      
 	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    			Date date = new Date();
    			//System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
 	       String screenshotName = testMethod + dateFormat.format(date) + ".png";
 	       System.out.println("See screenshotName:" + screenshotName);
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with name "screenshot.png"
            FileUtils.copyFile(scrFile, new File(screenshotName));
            System.out.println("Screenshot is taken.");
    }
    
    
    public static void scroll( WebElement from, WebElement to)
	{
		// Using Touch Action Classes
		TouchAction tAction=new TouchAction((MobileDriver)driver);
		int startx = from.getLocation().getX();
		int starty = from.getLocation().getY();
		int endx = to.getLocation().getX();
		int endy = to.getLocation().getY();
		System.out.println(startx + " ::::::: " + starty + " ::::::: " + endx +  " ::::::: " +	endy);

		//First tap on the screen and swipe it right using moveTo function
		tAction.press(startx+20,starty+20).moveTo(endx+20,endy+20).release().perform(); 
		WaitUtility.sleep(1000);
		
		//Second tap on the screen and swipe it left using moveTo function
		tAction.press(endx+20,endy+20).moveTo(startx+20,starty+20).release().perform();
		WaitUtility.sleep(1000);
	}
    
}
