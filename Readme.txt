Instructions to run the automation assignment for login and change password flows.
--------------------------------------------------------------------------------------------------------
*For running the test with your username and password 
enter your email and password in the field username@email.com and password1234 in the below command
or replace the dummy email and password in the testng.xml file
Choose the type of browser to run e.g, "chrome" , "chrome_mobile" or "firefox"

mvn clean test -Dusername=username@email.com -Dpassword=password1234 -Dbrowser=chrome

--------------------------------------------------------------------------------------------------------
*For running all the tests in both the mobile and desktop in the same run , use the below command

mvn clean test -Dsurefire.suiteXmlFile=testNgSpec.xml -Dusername=user@email.com -Dpassword=password1234
--------------------------------------------------------------------------------------------------------
Following tests are automated:
1.Login Flow
2.User navigation from home screen to Account settings page.
3. Change password screnario for
	a.successful change
	b.Invalid password or where the password does not meet the criteria
	c.newpassword and repeated password does not match.
