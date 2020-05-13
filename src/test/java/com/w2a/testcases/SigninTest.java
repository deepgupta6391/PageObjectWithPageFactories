package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.actions.SigninPage;
import com.w2a.utilities.Utilities;

public class SigninTest {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void signinTest(Hashtable<String, String> data) {
		
		if (data.get("runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Skipping the testcase as runmode is No");
		}
		Page.initConfiguration();
		SigninPage signin = Page.topNav.gotoSignIn();
		signin.doLogin(data.get("username"), data.get("password"));
	}

	@AfterMethod
	public void tearDown() {
		if (Page.driver != null) {
			Page.quitBrowser();
		}
	}

}
