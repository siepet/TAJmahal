package com.example.mockdemo.app;

import com.example.mockdemo.messenger.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentCaptor;
import org.mockito.Spy;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class MockitoTest {
	
	private static final String WP_PL = "wp.pl";
	@Spy private Messenger myMessenger;
	private MessageService mock;
	
	@Before
	public void setUp(){
		mock = mock(MessageService.class);
		myMessenger = new Messenger(mock);
	}
	
	@Test
	public void testingTheConnectionSuccessfull(){
		when(mock.checkConnection(WP_PL)).thenReturn(ConnectionStatus.SUCCESS);
		assertEquals(0, myMessenger.testConnection(WP_PL));
	}
	
	@Test
	public void testingTheConnectionNotSuccessfully(){
		when(mock.checkConnection("wp.com")).thenReturn(ConnectionStatus.FAILURE);
		assertEquals(1, myMessenger.testConnection("wp.com"));		
	}
	
	@Test
	public void testingSendingSuccessfully() throws MalformedRecipientException{
		when(mock.send(WP_PL, "radek")).thenReturn(SendingStatus.SENT);
		assertEquals(0, myMessenger.sendMessage(WP_PL, "radek"));
	}
	
	@Test
	public void testingSendingNotSuccessfully() throws MalformedRecipientException{
		when(mock.send("wp.com", "radek")).thenReturn(SendingStatus.SENDING_ERROR);
		assertEquals(1, myMessenger.sendMessage("wp.com", "radek"));
	}
	
	@Test
	public void testingSendingWithException() throws MalformedRecipientException{
		when(mock.send(WP_PL, "r")).thenThrow(new MalformedRecipientException());
		assertEquals(2, myMessenger.sendMessage(WP_PL, "r"));
	}
	
	@Test
	public void testingTheConnectionSuccessfullyWithArgumentMatchers(){
		when(mock.checkConnection(endsWith(".pl"))).thenReturn(ConnectionStatus.SUCCESS);
		assertEquals(0, myMessenger.testConnection(WP_PL)); 
	}
	
	@Test
	public void testingTheConnectionStatusWithAnswer(){
		Answer<ConnectionStatus> answer = new Answer<ConnectionStatus>() {

			@Override
			public ConnectionStatus answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				//Object mock = invocation.getMock();
				if(arguments[0].toString().endsWith("pl")){
					return ConnectionStatus.SUCCESS;
				} else {
					return ConnectionStatus.FAILURE;
				}
			}
			
		};
		
		when(mock.checkConnection(anyString())).thenAnswer(answer);
		assertEquals(0, myMessenger.testConnection(WP_PL));		// testing for good
		assertEquals(1, myMessenger.testConnection("wp.com"));		// testing for bad
	}
	
	@Test
	public void testingTheSendingStatusWithAnswer() throws MalformedRecipientException {
		Answer<SendingStatus> answer = new Answer<SendingStatus>() {

			@Override
			public SendingStatus answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				//Object mock = invocation.getMock();
				if(arguments[0].toString().endsWith("pl")){
					if(arguments[1].toString().length() < 4) {
						throw new MalformedRecipientException();
					}
					return SendingStatus.SENT;
				} else {
					return SendingStatus.SENDING_ERROR;
				}
			}
			
		};
		
		when(mock.send(anyString(), anyString())).thenAnswer(answer);
		assertEquals(0, myMessenger.sendMessage(WP_PL, "wiadomosc"));		// testing for good
		assertEquals(1, myMessenger.sendMessage("wp.com", "wiadomosc"));	// testing for bad
		assertEquals(2, myMessenger.sendMessage("wpl.pl", "asd"));			// testing for throw
	}
	
	@Test
	public void testingCheckingTheConnectionSuccessWithCaptures(){
		ArgumentCaptor<String> server = ArgumentCaptor.forClass(String.class);
		when(mock.checkConnection(WP_PL)).thenReturn(ConnectionStatus.SUCCESS);

		
	
		assertEquals(mock.checkConnection(WP_PL), ConnectionStatus.SUCCESS);
		verify(mock).checkConnection(server.capture());
	}


	

	

}
