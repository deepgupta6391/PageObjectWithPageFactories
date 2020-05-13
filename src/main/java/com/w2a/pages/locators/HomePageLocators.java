package com.w2a.pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class HomePageLocators {
	
	@FindAll({
		@FindBy(css="#tab-flight-tab-hp"),
		@FindBy(xpath="//*[@data-section-id='#section-flight-tab-hp']")
	})
	public WebElement flightTab;
	
	
	//locators for flight booking
	@FindBy(css="#flight-origin-hp-flight")
	public WebElement fromCity;	
	@FindBy(css="#flight-destination-hp-flight")
	public WebElement destinationCity;	
	@FindBy(css="#flight-departing-hp-flight")
	public WebElement departingDate;	
	@FindBy(css="#flight-returning-hp-flight")
	public WebElement returningDate;	
	@FindBy(css="#traveler-selector-hp-flight")
	public WebElement travellersTab;	
	@FindBy(css=".traveler-selector-sinlge-room-data.traveler-selector-room-data>div:first-child>div:nth-child(4)")
	public WebElement toAddAdults;
	@FindBy(css=".traveler-selector-sinlge-room-data.traveler-selector-room-data>div:nth-child(2)>div>div:nth-child(4)")
	public WebElement toAddChild;
	@FindBy(css=".traveler-selector-sinlge-room-data.traveler-selector-room-data>div:first-child>div:nth-child(3)")
	public WebElement toGetAdultCount;
	@FindBy(css=".traveler-selector-sinlge-room-data.traveler-selector-room-data>div:nth-child(2)>div>div:nth-child(3)")
	public WebElement toGetChildCount;	
	@FindBy(css=".cols-nested.children-data.gcw-toggles-fields.available-for-flights>label>select[id^='flight-age-select-1-hp-flight']")
	public WebElement toAddFirstChildAge;	
	@FindBy(css=".cols-nested.children-data.gcw-toggles-fields.available-for-flights>label>select[id^='flight-age-select-2-hp-flight']")
	public WebElement toAddSecondChildAge;
	//to select list of child's age
	@FindBy(css=".cols-nested.children-data.gcw-toggles-fields.available-for-flights>label>select")
	public List<WebElement> childrensAgeSelector;
	@FindBy(css="#gcw-flights-form-hp-flight>div.cols-nested.ab25184-submit>label>button.btn-primary.btn-action.gcw-submit")
	public WebElement searchBtn;
	@FindBy(css=".tab")
	public List<WebElement> tabCount; 

}
