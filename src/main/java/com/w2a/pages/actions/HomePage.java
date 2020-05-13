package com.w2a.pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.w2a.base.Page;
import com.w2a.pages.locators.HomePageLocators;



public class HomePage extends Page {

	public HomePageLocators home;

	public HomePage() {

		this.home = new HomePageLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this.home);
	}

	public HomePage gotoFlights() {
		click(home.flightTab);
		return this;
	}

	public void gotoHotels() {

	}

	public void gotoFlightAndHotels() {

	}

	public int findTabCount() {
		return home.tabCount.size();
	}

	public void bookAFlight(String flyingFrom, String destination, String departing, String returning,
			String noOfAdults, String noOfChild, String firstChildAge, String secondChildAge) {
		
		type(home.fromCity, flyingFrom);
		type(home.destinationCity, destination);
		type(home.departingDate, departing);
		type(home.returningDate, returning);
		click(home.travellersTab);

		int adults=(int)Double.parseDouble(noOfAdults);
		int child=(int)Double.parseDouble(noOfChild);
		//int adults = Integer.parseInt(noOfAdults);
		//int child = Integer.parseInt(noOfChild);
		for (int i = 2; i <= adults; i++) {
			click(home.toAddAdults);
		}
		for (int i = 1; i <= child; i++) {
			click(home.toAddChild);
		}

		// new Select(home.toAddFirstChildAge).selectByValue(firstChildAge);
		type(home.toAddFirstChildAge, firstChildAge);
		// new Select(home.toAddSecondChildAge).selectByValue(secondChildAge);
		type(home.toAddSecondChildAge, secondChildAge);
		click(home.travellersTab);
		click(home.searchBtn);
	}

}
