package com.example.mockdemo.bdd;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.MessageServiceSimpleImpl;

public class MessengerSteps {
	
	private Messenger myMessenger;
	private MessageService messageService;
	private int connectionStatus;
	private int sendingStatus;

	@Given("a Messenger")
	public void givenAMessenger() {
		messageService = new MessageServiceSimpleImpl();
		myMessenger = new Messenger(messageService);
	}

	@When("user checks connection with valid server name $name")
	public void whenUserChecksConnectionWithValidServerName(String name) {
		connectionStatus = myMessenger.testConnection(name);
	}
	
	@Then("status value $value is returned")
	public void thenStatusValueIsReturned(int value) {
		assertEquals(value, connectionStatus);
	}
	
	@When("user checks connection with not valid server name $name")
	public void whenUserChecksConnectionWithNotValidServerName(String name) {
		connectionStatus = myMessenger.testConnection(name);
	}
	
	@When("users sends message $m with server $s successfully")
	public void whenUsersSendsMessagemWithServersSuccessfully(String message, String server) {
		System.out.println("server == " + server + ", message == " + message);
		sendingStatus = myMessenger.sendMessage(server, message);
	}
	
	@Then("sending value $value is returned")
	public void thenSendingValueIsReturned(int value) {
		assertEquals(value, sendingStatus);
	}




}
