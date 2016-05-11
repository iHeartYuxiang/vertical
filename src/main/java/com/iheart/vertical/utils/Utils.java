package com.iheart.vertical.utils;

import com.iheart.vertical.abstractLayer.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFCell;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.CommandLine;;


public class Utils {
	
	public static WebDriver launchBrowser(String url, String browser)
	{       Page.setBrowser(browser);
			Page.mediaType = "web";
			WebDriver driver = createWebDriver(browser);
			
			driver.get(url);
			//WaitUtility.waitForAjax(driver);
			WaitUtility.sleep(1000);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			return driver;
	
	}
	
	
	
   public static WebDriver  createWebDriver(String browser) 
	
	{   WebDriver driver;
	
	    if (browser.equalsIgnoreCase("firefox"))
	
	        driver = new FirefoxDriver();
	
	    else if (browser.equalsIgnoreCase("chrome"))
	    {   //Set actual path to the driver file
	
	      // System.setProperty("webdriver.chrome.driver", "C:\\Users\\mmatos\\git\\lib\\chromedriver.exe");
	      //System.setProperty("webdriver.chrome.driver", "C:\\Users\\1111128\\git\\drivers\\chromedriver.exe");
	    	System.setProperty("webdriver.chrome.driver", "/Users/1111128/git/drivers/chromedriver");
	  	  
	  
		      ChromeOptions options = new ChromeOptions();
		      options.addArguments("test-type");
		      options.addArguments("--start-maximized");
		     
		      driver = new ChromeDriver(options);
		      
	      }else if (browser.equalsIgnoreCase("ie"))
	      {    //Set actual path to the driver file
	
	      System.setProperty("webdriver.ie.driver","C:\\Users\\1111128\\workspace\\drivers\\IEDriverServer.exe");
	
	      
	      driver = new InternetExplorerDriver();
	
	      }else 
	
	      {
	
	      System.out.println("Unknown browser.");
	
	      return null;
	
	      }
	
	      driver.manage().window().maximize();
	
	      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	      return driver;
	
	  }
	
	
	public  static  AndroidDriver launchAPPinAndroidPhone(String pathToBuild) throws Exception
	{
		AndroidDriver driver = null;
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	   
	    
	    capabilities.setCapability("device", "Android");
	   capabilities.setCapability("deviceName", "Android");
       	capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion", "4.4.2");
        capabilities.setCapability("app", pathToBuild);
       capabilities.setCapability("bundleID", "com.example.sample.id");
        capabilities.setCapability("appPackage", "com.clearchannel.iheartradio.controller");
        capabilities.setCapability("udid", "FA37KS910874");
        
      
        URL appiumUrl = null;
        try{
        	appiumUrl = new URL("http://localhost:4723/wd/hub");
        }
        catch(Exception e){
        	e.printStackTrace();
        	try{
        		appiumUrl = new URL("http://localhost:4723/wd/hub");
        	}catch(Exception mue){
        		mue.printStackTrace(); // Driver creation will fail
        	}
        }
        try{
        	driver = new AndroidDriver(appiumUrl, capabilities);
        }
        catch(Exception e ){
        	e.printStackTrace();
        	System.err.println("Could not start driver. Emulator or device may be unavailable. Appium may have disconnected or stopped. Sleeping 30 seconds to retry.");
        	
        }
		// We have our own timeouts, don't need this as much, reduced to half a second
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
	   
	   WaitUtility.sleep(5000);
	   return driver;
	}
	
	

	public  static  AndroidDriver launchAPPinGenymotion(String deviceName, String pathToBuild) throws Exception
	{
		 DesiredCapabilities capabilities = new DesiredCapabilities();
	
	     DefaultExecutor executor = new DefaultExecutor();
	     DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
	
	    // CommandLine launchEmul = new CommandLine("C:/Program Files/Genymobile/Genymotion/player");
	     CommandLine launchEmul = new CommandLine("/Users/1111128/.Genymobile/Genymotion/deployed");
	     launchEmul.addArgument("--vm-name");
	     launchEmul.addArgument("\""+deviceName+"\"");
	     executor.setExitValue(1);
	     executor.execute(launchEmul, resultHandler);
	     Thread.sleep(40);
	
	     //capabilities.setCapability("deviceName","Google Nexus 5 - 4.4.4 API 19 - 1080x1920");   \
	     capabilities.setCapability("deviceName", deviceName);
	     capabilities.setCapability("platformVersion", "4.3");
	     capabilities.setCapability("platformName", "Android");
	     capabilities.setCapability("app", pathToBuild);
	
	     AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
	     System.out.println("Android Driver is launched successfully");
	     
	  // We have our own timeouts, don't need this as much, reduced to half a second
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
		   
		   WaitUtility.sleep(3000);
		   return driver;

	}
	
	public  static  AndroidDriver launchAPPinEmulator(String emulatorName, String pathToBuild) throws Exception
	{
		AndroidDriver driver = null;
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    /*
	    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.2");
	    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, emulatorName);
	    desiredCapabilities.setCapability(MobileCapabilityType.APP, pathToBuild);
	    desiredCapabilities.setCapability("appiumVersion", "1.4.8");
	   
	   
	   driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	  */
	    
	    capabilities.setCapability("deviceName",emulatorName);
     // 	capabilities.setCapability("avd", emulatorName);
        capabilities.setCapability("platformVersion", "4.4.2");

        capabilities.setCapability("app", pathToBuild);
       capabilities.setCapability("bundleID", "com.example.sample.id");
        capabilities.setCapability("app", pathToBuild);
        capabilities.setCapability("appPackage", "com.clearchannel.iheartradio.controller");
        
        // Load other properties
      //  address = setTestProperty("APPIUM.WEBDRIVER.URL", testProperties);
      //  port = setTestProperty("APPIUM.WEBDRIVER.PORT", testProperties);
        URL appiumUrl = null;
        try{
        	appiumUrl = new URL("http://localhost:4723/wd/hub");
        }
        catch(Exception e){
        	e.printStackTrace();
        	try{
        		appiumUrl = new URL("http://localhost:4723/wd/hub");
        	}catch(Exception mue){
        		mue.printStackTrace(); // Driver creation will fail
        	}
        }
        try{
        	driver = new AndroidDriver(appiumUrl, capabilities);
        	System.out.println("Android driver is created.");
        }
        catch(Exception e ){
        	e.printStackTrace();
        	System.err.println("Could not start driver. Emulator or device may be unavailable. Appium may have disconnected or stopped. Sleeping 30 seconds to retry.");
        	
        }
		// We have our own timeouts, don't need this as much, reduced to half a second
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.MILLISECONDS);
	   
	   WaitUtility.sleep(5000);
	   return driver;
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
	   
	
	public static void waitForPageToLoad(WebDriver driver) {
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	
	        public Boolean apply(WebDriver driver) {
	
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	
	        }
	
	      };
	
	    Wait<WebDriver> wait = new WebDriverWait(driver,1000);
	
	      try {
	
	              wait.until(expectation);
	
	      } catch(Throwable error) {
	
	              System.out.println("Timeout waiting for Page Load Request to complete.");
	
	      }
	
	} 
	
	
	
	
	public static int getRandomInt()
	{
		Random randomGenerator = new Random();
	  
	    int randomInt = randomGenerator.nextInt(999999);
	     
	    return randomInt;
	    
	}
	
	public static IOSDriver launchBrowserInIOSMobile(String browser) throws Exception
	{
		 IOSDriver driver;
		
		
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.2");
	    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
	    desiredCapabilities.setCapability("browser", browser);
	    desiredCapabilities.setCapability("appiumVersion", "1.4.8");
	    
	   driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	   return driver;
	}
	
	public static IOSDriver launchBrowserInRealDevice(String browser, String deviceName, String udid, String bundleid) throws Exception
	{
		 IOSDriver driver;
		
		
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.4");
	    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
	    desiredCapabilities.setCapability("udid", udid);
	   // desiredCapabilities.setCapability("bundleid", bundleid); //com.clearchannel.iheartradio
		
	    desiredCapabilities.setCapability("browser", browser);
	    desiredCapabilities.setCapability("appiumVersion", "1.4.8");
	    
	   driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	  
	   WaitUtility.sleep(5000);
	   return driver;
	}
	
	
	//default to iphone 6
	public  static IOSDriver launchAPPinSimulator() throws Exception
	{
		return launchAPPinSimulator("iPhone 6");
	}
	
	public  static  IOSDriver launchAPPinSimulator(String simulatorName) throws Exception
	{
		 IOSDriver driver;
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    //desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.4");
	    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.1");
	    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, simulatorName);
	    desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/1111128/Library/Developer/Xcode/DerivedData/iPhone-ccflceywhaxzymfxdatoocajqggx/Build/Products/Debug-iphonesimulator/iHeartRadio.app");
	    desiredCapabilities.setCapability("appiumVersion", "1.4.8");
	   
	   
	   driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	  
	   WaitUtility.sleep(5000);
	   return driver;
	}
	
	
	public  static  IOSDriver launchAPPinSimulator(String simulatorName, String pathToBuild) throws Exception
	{ 
		 IOSDriver driver;
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    //desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.4");
	    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.2");
	    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, simulatorName);
	    desiredCapabilities.setCapability(MobileCapabilityType.APP, pathToBuild);
	    desiredCapabilities.setCapability("appiumVersion", "1.4.8");
	   
	   
	   driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
	   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	   Page.mediaType = "ios";
	   WaitUtility.sleep(5000);
	   return driver;
	}
	
	
	//This is for native, what about mobile web? 
	public static IOSDriver launchAPPinRealDevice(String deviceName, String udid, String bundleid, String ipaName) throws Exception
	{
		 IOSDriver driver;
		
		 DesiredCapabilities capabilities = new DesiredCapabilities();

		 capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.4");
		 capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		// capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
		 //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 5s");
		//capabilities.setCapability("device", "iPhone 5s");
		//capabilities.setCapability("udid", "6a667778f94f8241aa6511e3c8cbc8b1643bb9b5");
		//capabilities.setCapability("bundleid", "com.example.appiumiphonetest");
		capabilities.setCapability("udid", udid);
		capabilities.setCapability("bundleid", bundleid); //com.clearchannel.iheartradio
		//capabilities.setCapability("ipa", "MyiOSApp.ipa");
		capabilities.setCapability("ipa", ipaName); ///Users/1111128/git/ios-flagship/iPhone.ipa
		capabilities.setCapability("appiumVersion", "1.4.8");
	   
	   
	   driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
	   driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	   Page.setIsRealDevice(true);
	   WaitUtility.sleep(5000);
	   return driver;
	}



	public static Map<String, String> getLocationByIp(WebDriver driver)
	{    Map<String, String> geoInfo = new HashMap<String, String>();
		 driver.navigate().to("http://www.iplocation.net");
		 String country = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
		 String state = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(3)")).getText();
		 String city = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(4)")).getText();
		 
		 geoInfo.put("country", country);
		 geoInfo.put("state", state);
		 geoInfo.put("city", city);
		 
		 
		 
		 return geoInfo;
	}
	
	
	public static void outputRoyaltyToExcel(Map<String, String> eventLog, String fileName)
	{   
		if (eventLog == null || eventLog.size() < 2) return;
		System.out.println("eventLog.size:" + eventLog.size());  
	   
        try {
            
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");  

            /*
            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Step Actions:");
            rowhead.createCell(1).setCellValue("");
            */
            int rowCount = 0; 
            Iterator iterator = eventLog.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry)iterator.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                HSSFRow row = sheet.createRow((short)rowCount++);
                row.createCell(0).setCellValue((String)pair.getKey());
                row.createCell(1).setCellValue((String)pair.getValue());
             //   iterator.remove(); // avoids a ConcurrentModificationException
            }
            
            
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
	

	public static Object getAllProperties(WebDriver driver, WebElement element)
	{
		return ((JavascriptExecutor)driver).executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", element);
	
		
	}
	
}
