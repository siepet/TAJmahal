package com.example.seleniumdemo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SomeSiteTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		File firefox = new File("/usr/bin/firefox");
		System.setProperty("webdriver.firefox.bin", firefox.getAbsolutePath());
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void homePage(){
		driver.get("http://codebros.pl:666/");		
		assertEquals("Bracketize! - main page", driver.getTitle());
	}
	
	@Test
	public void loginPage(){
		driver.get("http://codebros.pl:666");
		element = driver.findElement(By.cssSelector("#navbar > form > input"));
		element.click();
		assertEquals("Bracketize!", driver.getTitle());
	}
	
	@Test
	public void successfullLoginHomePage(){
		
		driver.get("http://codebros.pl:666");
		driver.findElement(By.cssSelector("#session_login")).sendKeys("admin");
		driver.findElement(By.cssSelector("#session_password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("#navbar > form > input")).click();
		element = driver.findElement(By.cssSelector("body > div"));
		assertEquals("You have successfully logged in!", element.getText());
		
		driver.get("http://codebros.pl:666/signout");
		element = driver.findElement(By.cssSelector("body > div"));
		assertEquals("You have successfully logged out!", element.getText());
		
	}
	
	
	@Test
	public void unsuccessfullLoginHomePage(){
		
		driver.get("http://codebros.pl:666");
		driver.findElement(By.cssSelector("#session_login")).sendKeys("admin123");
		driver.findElement(By.cssSelector("#session_password")).sendKeys("admin121233");
		driver.findElement(By.cssSelector("#navbar > form > input")).click();
		element = driver.findElement(By.cssSelector("body > div"));
		assertEquals("Wrong login/password combination!", element.getText());		
	}
	
	@Test
	public void screenshotFadeFlashMessageVisible(){		
		driver.get("http://codebros.pl:666");
		driver.findElement(By.cssSelector("#session_login")).sendKeys("admin");
		driver.findElement(By.cssSelector("#session_password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("#navbar > form > input")).click();
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		
		try {
			FileUtils.copyFile(screenshot, new File("flashVisible.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
		assertTrue(true);
		driver.get("http://codebros.pl:666/signout");
		element = driver.findElement(By.cssSelector("body > div"));
		assertEquals("You have successfully logged out!", element.getText());
	}
	
	@Test
	public void screenshotFadeFlashMessageInvisible() throws InterruptedException{		
		driver.get("http://codebros.pl:666");
		driver.findElement(By.cssSelector("#session_login")).sendKeys("admin");
		driver.findElement(By.cssSelector("#session_password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("#navbar > form > input")).click();
		Thread.sleep(2000);
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		
		try {
			FileUtils.copyFile(screenshot, new File("flashInvisible.png"));
		} catch (IOException e){
			e.printStackTrace();
		}
		assertTrue(true);
		driver.get("http://codebros.pl:666/signout");
		element = driver.findElement(By.cssSelector("body > div"));
		assertEquals("You have successfully logged out!", element.getText());
	}
	
	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
}
