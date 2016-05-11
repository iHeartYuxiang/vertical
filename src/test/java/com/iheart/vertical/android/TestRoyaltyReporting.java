package com.iheart.vertical.android;

import com.iheart.vertical.abstractLayer.*;
import com.iheart.vertical.utils.*;

import java.util.List;
import java.util.ArrayList;

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

import static org.junit.Assert.*; 

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test; 
import org.junit.Ignore; 
import org.junit.Before; 
import org.junit.After; 
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.TestName;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestRoyaltyReporting {

	private static final String DEVICE_NAME = "Samsung Galaxy S3 - 4.3 - API 18 - 720x1280";
	//private static final String DEVICE_NAME = "HTC One XL - 4.2.2 - API 17 - 720X1280";
	//private static final String DEVICE_NAME = "Google Nexus 5 - 5.0.0 - API 21 - 1080X1920";
	
	
	 private static final String BUNDLE_ID = "com.clearchannel.iheartradio";
	// private static final String APP_NAME = "/Users/1111128/Desktop/androidBuild/iHeartRadio-google-mobile-ampprod-debug.apk";
	//The following is the one that I used earlier
  //   private static final String APP_NAME = "/Users/1111128/Desktop/androidBuild/iHeartRadio-google-mobile-ampprod-dev.apk";
	 private static final String APP_NAME = "/Users/1111128/Desktop/androidBuild/iHeartRadio-google-mobile-ampprod-release.apk";
			
	 
	 private AndroidDriver driver;
	 private AndroidRoyaltyReport royaltyReport;
	 static Map<String, String> eventLog ;
	
	//final String URL = "http://www.iheart.com";
	final String URL = "http://beta.iheart.com";
	
	@Rule public TestName name = new TestName();
	
	@BeforeClass
	 public static void prepare() {
		eventLog = new LinkedHashMap<String, String>();
       Page.getErrors().delete(0, Page.getErrors().length());
	              
	 }
	
	@Before
    public void init() throws Exception {
	   driver = Utils.launchAPPinGenymotion(DEVICE_NAME, APP_NAME);
		// driver = Utils.launchAPPinAndroidPhone( APP_NAME);
		
	   Page.setDriver(driver);
	   Page.mediaType = "android";
			   
	   //Wait for page to load
       WaitUtility.sleep(2000);
       
       royaltyReport = new AndroidRoyaltyReport(driver);
       
        Page.getErrors().delete(0, Page.getErrors().length());
        
        //Stop playing if it is already playing 
       try {
           ((AndroidPlayer)Page.getPlayer()).pauseMiniPlayer();
       }catch(Exception e)
       {
    	   
       }
    }
	
	
	@Test
    public void testRoyalty_myStations() throws Exception
    {   
        System.out.println("test method:" +  name.getMethodName() );
        
        
        try{
        	royaltyReport.testRoyalty_myStations();
        	
        }catch(Exception e)
	     {
	         handleException(e);
	     }      
	        
    }    
	
	
	@Test
    public void testRoyalty_liveRadio() throws Exception
    {   
        System.out.println("test method:" +  name.getMethodName() );
        
        
        try{
        	royaltyReport.testRoyalty_liveRadio();;
        	
        }catch(Exception e)
	     {
	         handleException(e);
	     }      
	        
    }  
	
	@Test
    public void testRoyalty_artistRadio() throws Exception
    {   
        System.out.println("test method:" +  name.getMethodName() );
        
        
        try{
        	royaltyReport.testRoyalty_artistRadio();;
        	
        }catch(Exception e)
	     {
	         handleException(e);
	     }      
	        
    }  
	
	
	@Test
    public void testRoyalty_perfectFor() throws Exception
    {   
        System.out.println("test method:" +  name.getMethodName() );
        
        
        try{
        	royaltyReport.testRoyalty_perfectFor();
        	
        }catch(Exception e)
	    {
	         handleException(e);
	    }      
	        
    }  
	
	
	
	@Test
    public void testRoyalty_podcasts() throws Exception
    {   
        System.out.println("test method:" +  name.getMethodName() );
        
        
        try{
        	royaltyReport.testRoyalty_podcasts();
        	
        }catch(Exception e)
	     {
	         handleException(e);
	     }      
	        
    }  
	
	
	 @After
	 public void tearDown() throws Exception{
		 
		 //first, save eventLog
	    eventLog.putAll(royaltyReport.getEventLog());
		 
	 	 try {
		    	if (Page.getErrors().length() > 0)
					 fail(Page.getErrors().toString());
	 	 }catch(Exception e)
	 	 {
			 e.printStackTrace();
	 	 }
		    	
	 }
  
	
	  @AfterClass
	 public static void logEventToFile() throws Exception{
	   //put all logged events into a file for late verification
	    DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm");
			Date date = new Date();
		
	   Utils.outputRoyaltyToExcel(eventLog, "eventLog_"+ dateFormat.format(date) + ".xls");
	 
    	
	 }  
	
	    private void handleException(Exception e)
	    {   Page.getErrors().append("Exception is thrown.");
	        e.printStackTrace();
	       
            try{
	    	   
	    	   Page.takeScreenshot(driver, name.getMethodName());
            }catch(Exception eX)
            {
            	
            }
           
	    }
	    
	   
	    
	    @Rule
	    public TestRule watcher = new TestWatcher() {
	        @Override
	        public void finished(Description description) {
	           driver.quit();
	        }

	        @Override
	        public void failed(Throwable e, Description description) {
	          
	        	try {
	               
	        		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

	               // String filePathRoot = "C:\\_Jenkins\\workspace\\" + jenkinsJobName + "\\target\\surefire-reports\\";
	        		String currentPath =  System.getProperty("user.dir");
	        		String path = currentPath + "\\target\\surefire-reports\\";
	        		
	                String fullFilePath = path + description.getClassName() + "\\" + description.getMethodName() + ".jpg";

	                FileUtils.copyFile(screenshot, new File(fullFilePath));
	                
	        		
	            } catch(Exception ex) {
	                System.out.println(ex.toString());
	                System.out.println(ex.getMessage()); 
	            }

	           driver.quit();
	        }
	    };



}

	

