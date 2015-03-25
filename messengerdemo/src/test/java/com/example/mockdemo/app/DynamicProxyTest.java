package com.example.mockdemo.app;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class DynamicProxyTest {

	@Test
	public void checkTestingConnectionSuccesfully() {

		InvocationHandler ih = new MessageServiceHandler();
		MessageService msMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myMessage = new Messenger(msMock);
		assertEquals(0, myMessage.testConnection("wp.pl"));
	}
	
	@Test
	public void checkTestingConnectionNotSuccesfully() {

		InvocationHandler ih = new MessageServiceHandler();
		MessageService msMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myMessage = new Messenger(msMock);
		assertEquals(1, myMessage.testConnection("wp.pl.com"));
	}
	
	@Test
	public void checkSendingMessageSuccessfully() {

		InvocationHandler ih = new MessageServiceHandler();
		MessageService msMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myMessage = new Messenger(msMock);
		assertEquals(0, myMessage.sendMessage("wp.pl", "radek"));
	}
	
	@Test
	public void checkSendingMessageNotSuccessfully() {

		InvocationHandler ih = new MessageServiceHandler();
		MessageService msMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myMessage = new Messenger(msMock);
		assertEquals(1, myMessage.sendMessage("wp.pl.com", "wiadomosc panie"));
	}
	
	@Test
	public void checkSendingMessageReturnsUndefined() {

		InvocationHandler ih = new MessageServiceHandler();
		MessageService msMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myMessage = new Messenger(msMock);
		assertEquals(2, myMessage.sendMessage("wp.pl.com", "radek"));
	}
	
	@Test
	public void checkSendingMessageException() {

		InvocationHandler ih = new MessageServiceHandler();
		MessageService msMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);

		Messenger myMessage = new Messenger(msMock);
		assertEquals(2, myMessage.sendMessage("", "radek"));
	}
	
	class MessageServiceHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if ("checkConnection".equals(method.getName())){
				if(args[0].toString().matches("[a-z].*.pl$")) {
					return ConnectionStatus.SUCCESS;
				} else {
					return ConnectionStatus.FAILURE;
				}
			}
			
			if("send".equals(method.getName())){
				if(args[0].toString().equals("wp.pl.com") &&
						args[1].toString().equals("radek")){
					throw new MalformedRecipientException();
				}
			}
			
			if("send".equals(method.getName())){
				if(args[0].toString().length() > 3 ){//&& !args[1].toString().equals("radek")){
					if(args[0].toString().matches("[a-z].*.pl$")){
						return SendingStatus.SENT;
					} else if(!args[0].toString().matches("[a-z].*.pl$")) {
						return SendingStatus.SENDING_ERROR;
					}
				} else {
					throw new MalformedRecipientException();
				}
				if(args[1].toString().equals("radek")){
					return null;
				}

			}

			
			return 5;
		}
	}
}
