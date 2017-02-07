package com.yelp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class SearchResultsElements {
	
WebDriver driver;
static Logger log = Logger.getLogger(YelpElements.class);
	
    @FindBy(id="find_desc")
    WebElement searchBox;
    
    @FindBy(id="header-search-submit")
	WebElement searchIcon;
    
	@FindBy(xpath="//*[@id='super-container']//ul[contains(@class,'ylist')]/li")
	WebElement searchResults;
	
	@FindBy(xpath="//*[@id='wrap']//span[contains(@class,'pagination-results-window')]")
	WebElement totalresultscount;
	
	@FindBy(xpath="//*[@id='wrap']//span[contains(@class,'pagination-results-window')]")
	WebElement totalfilteredresultscount;
	
	@FindBy(xpath="//*[@id='wrap']//span[contains(@class,'icon--18-filter')]")
	WebElement filters;
	
	@FindBy(xpath="//input[contains(@type,'checkbox') and contains(@value,'Downtown_Berkeley')]")
	WebElement DBerkely;
	
	@FindBy(xpath="//span[contains(@class,'radio-link') and contains(text(),'Biking')]")
	WebElement Distance;
	
	@FindBy(xpath="//div[@data-key='1']//div//h3//a")
	WebElement FirstResult; 
	
   public SearchResultsElements(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
   
   public void searchForNewvalue(String searchnewitem){
		searchBox.sendKeys(searchnewitem);
	    log.info("Enter new search to append as "+searchnewitem);
	}
   
   public void clickSearchIcon(){
		searchIcon.click();
		log.info("Search box Icon element found and clickable");
	}
   
   public void getTotalCount(){
	  String totalCount = totalresultscount.getText().split(" ")[3];
	  System.out.println("The total number of results for the search Restaurants Pizza is:"+totalCount);
   }
   
  public void getFilteredTotalCount(){
	  String totalFilteredCount = totalfilteredresultscount.getText().split(" ")[3];
	  System.out.println("The total number of results for filtered results is: "+totalFilteredCount);
    }
	
   public void clickFilterIcon(){
	filters.click();
	log.info("Filter Icon element found and clickable");
    }	
   
   public void clickDberkely(){
	DBerkely.click();
	log.info("Downtown Berkely location selected");
    }
   
   public void clickBikingDist(){
	Distance.click();
	log.info("Biking Distance 2 mile selected");
    }
   
   public void clickFirstResult(){
	FirstResult.click();
	log.info("First Result in the search page selected");
   }
   
   public void verifyAppendedtext(){
	   String input = "Restaurants Pizza";
	   try{
		   Assert.assertEquals(searchBox.getAttribute("value"),input);
	       System.out.println("Appended text as expected");
	   }catch(Throwable e){
	   System.out.println("Appended test not as expected");
	   }
   }
}