package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.w2a.base.Page;
import com.w2a.errorcollectors.ErrorCollector;
import com.w2a.pages.actions.HomePage;
import com.w2a.utilities.Utilities;

public class FlightSearchTest {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void flightSearchTest(Hashtable<String, String> data) {

		if (data.get("runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the testcase as runmode is No");
		}
		Page.initConfiguration();
		HomePage home = new HomePage();
		ErrorCollector.verifyEquals(home.findTabCount(), 8);
		home.gotoFlights().bookAFlight(data.get("flyingFrom"), data.get("destination"), data.get("departingDate"),
				data.get("returningDate"), data.get("noOfAdults"), data.get("noOfChild"), data.get("firstChildAge"),
				data.get("secondChildAge"));
		// ass.assertAll();

	}

	@AfterMethod
	public void tearDown() {
		if (Page.driver != null) {
			Page.quitBrowser();
		}
	}
}
