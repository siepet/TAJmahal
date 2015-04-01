Given a Messenger
When user checks connection with valid server name wp.pl
Then status value 0 is returned

When user checks connection with not valid server name wp.com
Then status value 1 is returned

When users sends message helloWorld with server wp.pl successfully
Then sending value 0 is returned

When users sends message hiasdsaddsa with server wp.com not successfully
Then sending value 1 is returned