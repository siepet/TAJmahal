package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class MainPage extends WebDriverPage {

	public MainPage(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	public void open() {
		get("http://codebros.pl:666/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
	