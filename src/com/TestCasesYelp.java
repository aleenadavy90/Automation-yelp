package com.yelp;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.yelp.YelpElements;
import com.yelp.SearchResultsElements;

public class TestCasesYelp {
	
	private WebDriver driver;
	private String baseURL;
	static Logger log = Logger.getLogger(TestCasesYelp.class);
	YelpElements homepage;
	SearchResultsElements resultspage;
	
	@BeforeMethod
	public void beforeMethod(){
		
		driver = new FirefoxDriver();
		baseURL = "https://www.yelp.com";
		homepage = new YelpElements(driver);
		resultspage = new SearchResultsElements(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		PropertyConfigurator.configure("log4j.properties");
	}
		
	@Test
	public void Test_A() throws Exception{
		
		//Go to yelp.com
		driver.get(baseURL);
		homepage.verifyYelpPageload();
		
		//Select “Restaurants” in the drop-down box in Find
	    homepage.clickSearch();
		homepage.SelectRestaurantFromDropdown();
		
		//Click Search button
		homepage.clickSearchIcon();
		
		//Append Pizza to Restaurants to make the search text as “Restaurants Pizza”
		resultspage.searchForNewvalue(" Pizza");
		resultspage.verifyAppendedtext();
		resultspage.clickSearchIcon();
		
		//Report total no. of Search results with no. of results in the current page
		System.out.println("**********************************************************");
		resultspage.getTotalCount();
		int currentcount = driver.findElements(By.xpath("//ul[contains(@class,'ylist-bordered')]/li")).size();
	    System.out.println("The total number of results in the current page is: " +currentcount); 
	    
	    //Parameterize any 2 of the filtering parameters (Neighborhoods, Distance, Price, Features, etc.) and Apply the filter
	    resultspage.clickFilterIcon();
	    Thread.sleep(3000);
	    resultspage.clickDberkely();
	    Thread.sleep(3000);
	    resultspage.clickBikingDist();
	    Thread.sleep(3000);
	    
	    //Report total no. of Search results with no. of results in the current page
		resultspage.getFilteredTotalCount();
		int currentfilteredcount = driver.findElements(By.xpath("//ul[contains(@class,'ylist-bordered')]/li")).size();
	    System.out.println("The total number of results in the filtered current page is: " +currentfilteredcount); 
	
	    //Report the star rating of each of the results in the first result page
	    System.out.println("**********************************************************");
	    System.out.println("The star rating of each of the Restaurants are as follows:");
	    List<WebElement> name_restaurant = driver.findElements(By.xpath("//a[contains(@class,'biz-name')]"));
	    List<WebElement> star_rating = driver.findElements(By.xpath("//div[contains(@class,'i-stars')]"));
	    for (int i = 0; i < name_restaurant.size()-1; i++) {
	        WebElement name = name_restaurant.get(i);
	        WebElement star = star_rating.get(i);

	        System.out.println(name.getText() + ":" + star.getAttribute("title"));
	    }
	    
	    //Click and expand the first result from the search results
	    resultspage.clickFirstResult();
	      
	    // Log all critical information of the selected restaurant details, for the reporting purpose
	    System.out.println("**************************************");
	    System.out.println("The Restaurant details are as follows:");
	    List<WebElement> elements = driver.findElements(By.xpath("//div[@class='mapbox-text']"));
    	for(WebElement info : elements){
    		System.out.println(""+info.getText());
    	}
    	
    	int i=1;
    	System.out.println("The first three reviews are as follows:");
    	List<WebElement> titles = driver.findElements(By.xpath("//div[@class='review-wrapper']//div//p[@lang='en']"));
		List<WebElement> sublist = titles.subList(0, 3);
			for(WebElement firstThreeReviews : sublist){
				System.out.println("Review "+i);
				System.out.println("*********");
			    System.out.println(""+firstThreeReviews.getText());
				i++;
			}
	}
	
	
	@AfterMethod
	public void afterMethod(){
		
	driver.quit();
	}

}
