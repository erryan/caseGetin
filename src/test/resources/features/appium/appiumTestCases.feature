@appiumTest
Feature: App Test

  Scenario: App test
    Then I should see the User Login Button
    Then I should see "Giriş Yap" login button text
    When I click on Login button
    Then I should see login screen
    When I click on login button on Login Screen
    Then I should see "Merhaba, Çağrı" text on the login Button