package com.example.mockdemo.app;

import com.example.mockdemo.messenger.*;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class MockitoTest {
	
	private Messenger myMessenger;
	private MessageService mock;
	
	@Before
	public void setUp(){
		mock = mock(MessageService.class);
		myMessenger = new Messenger(mock);
	}
	
	@Test
	public void testingTheConnectionSuccessfull(){
		when(mock.checkConnection("wp.pl")).thenReturn(ConnectionStatus.SUCCESS);
		assertEquals(0, myMessenger.testConnection("wp.pl"));
	}
	
	@Test
	public void testingTheConnectionNotSuccessfully(){
		when(mock.checkConnection("wp.com")).thenReturn(ConnectionStatus.FAILURE);
		assertEquals(1, myMessenger.testConnection("wp.com"));		
	}
	
	@Test
	public void testingSendingSuccessfully() throws MalformedRecipientException{
		when(mock.send("wp.pl", "radek")).thenReturn(SendingStatus.SENT);
		assertEquals(0, myMessenger.sendMessage("wp.pl", "radek"));
	}
	
	@Test
	public void testingSendingNotSuccessfully() throws MalformedRecipientException{
		when(mock.send("wp.com", "radek")).thenReturn(SendingStatus.SENDING_ERROR);
		assertEquals(1, myMessenger.sendMessage("wp.com", "radek"));
	}
	
	@Test
	public void testingSendingWithException() throws MalformedRecipientException{
		when(mock.send("wp.pl", "r")).thenThrow(new MalformedRecipientException());
		assertEquals(2, myMessenger.sendMessage("wp.pl", "r"));
	}
	
	

}
