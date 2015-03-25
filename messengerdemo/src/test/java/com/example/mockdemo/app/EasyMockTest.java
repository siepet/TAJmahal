package com.example.mockdemo.app;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class EasyMockTest {

	private Messenger myMessenger;
	private MessageService mock;
	
	@Before
	public void setUp(){
		mock = createMock(MessageService.class);
		myMessenger = new Messenger(mock);
	}
	
	@Test
	public void testingTheConnectionSuccessfull(){
		expect(mock.checkConnection("wp.pl")).andReturn(ConnectionStatus.SUCCESS);
		replay(mock);
		assertEquals(0, myMessenger.testConnection("wp.pl"));
		verify(mock);
	}
	
	@Test
	public void testingTheConnectionNotSuccessfully(){
		expect(mock.checkConnection("wp.com")).andReturn(ConnectionStatus.FAILURE);
		replay(mock);
		assertEquals(1, myMessenger.testConnection("wp.com"));
		verify(mock);
	}
	
	@Test
	public void testingSendingSuccessfully() throws MalformedRecipientException{
		expect(mock.send("wp.pl", "radek")).andReturn(SendingStatus.SENT);
		replay(mock);
		assertEquals(0, myMessenger.sendMessage("wp.pl", "radek"));
		verify(mock);
	}
	
	@Test
	public void testingSendingNotSuccessfully() throws MalformedRecipientException{
		expect(mock.send("wp.com", "radek")).andReturn(SendingStatus.SENDING_ERROR);
		replay(mock);
		assertEquals(1, myMessenger.sendMessage("wp.com", "radek"));
		verify(mock);
	}
	
	@Test
	public void testingSendingWithException() throws MalformedRecipientException{
		expect(mock.send("wp.pl", "rad")).andThrow(new MalformedRecipientException());
		replay(mock);
		assertEquals(2, myMessenger.sendMessage("wp.pl", "rad"));
		verify(mock);
	}

}
