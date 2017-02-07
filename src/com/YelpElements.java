package com.yelp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class YelpElements {
	
	WebDriver driver;
	static Logger log = Logger.getLogger(YelpElements.class);
	
	@FindBy(id="find_desc")
	WebElement searchBox;
 
	
	@FindBy(xpath="//strong[contains(text(),'Restaurants')]")
	WebElement inputRestaurants;
	
	@FindBy(id="header-search-submit")
	WebElement searchIcon;

	
public YelpElements(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


public void clickSearch(){
	searchBox.click();
	log.info("Search Text box element found and clickable");
}

public void SelectRestaurantFromDropdown(){
	inputRestaurants.click();
	log.info("Restaurant Dropdown selected");
}

public void clickSearchIcon(){
	searchIcon.click();
	log.info("Search Icon element found and clickable");
}	

public void searchFor(String search){
	searchBox.sendKeys(search);
	log.info("Enter search item as "+search);
}

public void verifyYelpPageload(){
	String expectedTitle = "Berkeley Restaurants, Dentists, Bars, Beauty Salons, Doctors";
	String actualTitle = driver.getTitle();
	try{
	Assert.assertEquals(actualTitle,expectedTitle);
	System.out.println("Yelp Webpage is loaded successfully");
	}
	catch(Throwable e){
	System.out.println("Yelp Page load failed");
	}
}

}