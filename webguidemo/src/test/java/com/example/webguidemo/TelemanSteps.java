package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class TelemanSteps {
	
	private final Pages pages;

	public TelemanSteps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("user is on Home page")
    public void userIsOnHomePage(){        
        pages.home().open();        
    }
 
    @When("user opens Sport link")
    public void userClicksOnSportLink(){        
        pages.home().findElement(By.linkText("SPORT")).click();
    }
 
    @Then("Sport page is shown")
    public void sportPageIsShown(){
       assertEquals("Sport w Programie TV - Program telewizyjny Teleman.pl", pages.sport().getTitle());
    }
    
    
    @Given("user is on SignIn page")
    public void givenUserIsOnSignInPage() {
      pages.signin().open();
    }

    @When("user fills login field")
    public void whenUserFillsLoginField() {
    	pages.signin().findElement(By.cssSelector("div.row:nth-child(1) > div:nth-child(2) > form:nth-child(2) > div:nth-child(2) > input:nth-child(2)")).sendKeys("admin");
    }

    @When("fills password field")
    public void whenFillsPasswordField() {
    	pages.signin().findElement(By.cssSelector("div.row:nth-child(1) > div:nth-child(2) > form:nth-child(2) > div:nth-child(3) > input:nth-child(2)")).sendKeys("admin123");

    }

    @When("clicks \"Zaloguj si\u0119\"")
    public void whenClicksZalogujSię() {
    	pages.signin().findElement(By.cssSelector("div.row:nth-child(1) > div:nth-child(2) > form:nth-child(2) > input:nth-child(4)")).click();
    }

    @Then("Home page is displayed.")
    public void thenHomePageIsDisplayed() {
      assertEquals("Bracketize! - main page", pages.mainpage().getTitle());
      String message = pages.mainpage().findElement(By.xpath("html/body/div[1]")).getText();
      assertEquals(message, "You have successfully logged in!");

    }

    @When("user fills login field with bad data")
    @Pending
    public void whenUserFillsLoginFieldWithBadData() {
      // PENDING
    }

    @When("fills password field with bad data")
    @Pending
    public void whenFillsPasswordFieldWithBadData() {
      // PENDING
    }

    @Then("SignIn page is displayed")
    @Pending
    public void thenSignInPageIsDisplayed() {
      // PENDING
    }

    @Then("message \"$text\" is displayed.")
    public void thenMessageWrongLoginpasswordCombinationIsDisplayed(String text) {
      // PENDING
    	System.out.println(text);
    }


}
