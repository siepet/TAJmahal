Scenario: User can successfully login to the page.

Given user is on SignIn page
When user fills login field
And fills password field
And clicks "Zaloguj się"
Then Home page is displayed.

Scenario: User provides wrong login and password combination

Given user is on SignIn page
When user fills login field with bad data
And fills password field with bad data
And clicks "Zaloguj się"
Then SignIn page is displayed
And message "Wrong login/password combination!" is displayed.