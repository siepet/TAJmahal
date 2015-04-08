package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class SignIn extends WebDriverPage {

	public SignIn(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	public void open() {
		get("http://codebros.pl:666/signin");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
	