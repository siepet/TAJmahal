package com.example.webguidemo;

import org.jbehave.web.selenium.WebDriverProvider;

import com.example.webguidemo.pages.MainPage;
import com.example.webguidemo.pages.SignIn;
import com.example.webguidemo.pages.Sport;
import com.example.webguidemo.pages.Home;

public class Pages {

	private WebDriverProvider driverProvider;
	
	//Pages
	private Home home;
	private Sport sport;
	private SignIn signin;
	private MainPage mainpage;
	// ...

	public Pages(WebDriverProvider driverProvider) {
		super();
		this.driverProvider = driverProvider;
	}

	public Home home() {
		if (home == null) {
			home = new Home(driverProvider);
		}
		return home;
	}
	
	public Sport sport(){
		if (sport == null) {
			sport = new Sport(driverProvider);
		}
		return sport;
	}
	
	public SignIn signin(){
		if(signin == null){
			signin = new SignIn(driverProvider);
		}
		return signin;
	}
	
	public MainPage mainpage(){
		if(mainpage == null){
			mainpage = new MainPage(driverProvider);
		}
		return mainpage;
	}
	
	
}
