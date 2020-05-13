package com.w2a.rough;

import com.w2a.base.Page;
import com.w2a.pages.actions.SigninPage;

public class SigninTest {

	public static void main(String[] args) {
		Page.initConfiguration();
		SigninPage signin=Page.topNav.gotoSignIn();
		signin.doLogin("jitengupta271@gmail.com","Honey@1234");

	}

}
