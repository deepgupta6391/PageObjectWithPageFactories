package com.w2a.rough;

import com.w2a.base.Page;
import com.w2a.pages.actions.HomePage;

public class FlightSearchTest {

	public static void main(String[] args) {
		Page.initConfiguration();
		HomePage home = new HomePage();
		
		home.gotoFlights().bookAFlight("Delhi (DEL-Indira Gandhi Intl.)", "Bengaluru (BLR-Kempegowda Intl.)","01/10/2020",
				"01/11/2020", "3", "2", "5", "7");
		

		 Page.quitBrowser();
	}

}
