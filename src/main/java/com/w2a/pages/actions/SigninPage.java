package com.w2a.pages.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.w2a.base.Page;
import com.w2a.pages.locators.SigninPageLocators;

public class SigninPage extends Page{
	
	public SigninPageLocators signinPage;

	
	public SigninPage() {
		this.signinPage=new SigninPageLocators();
		AjaxElementLocatorFactory factory=new AjaxElementLocatorFactory(driver,10);
		PageFactory.initElements(factory, this.signinPage);
	}
	
	public void doLogin(String username,String password) {
				
		//javascriptexecutor.executeScript("arguments[0].value='"+username+"';", signinPage.email);
		typeusingjavascript(signinPage.email, username);
		//javascriptexecutor.executeScript("arguments[0].value='"+password+"';", signinPage.password);
		typeusingjavascript(signinPage.password, password);
		click(signinPage.signBtn);
	}

}
