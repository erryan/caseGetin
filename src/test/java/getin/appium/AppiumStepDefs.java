package getin.appium;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class AppiumStepDefs {

    @Autowired
    private AppPageObj appPageObj;

    @When("I click on Login button")
    public void clickLogin() {
        appPageObj.clickLoginButton();
    }

    @When("I click on login button on Login Screen")
    public void clickLoginToLoginScreen() {
        appPageObj.clickLoginButtontoLoginScreen();
    }

    @Then("I should see login screen")
    public void verifyUserLoginScreen() {
        Assert.assertTrue(appPageObj.displayLoginButton());
    }

    @Then("I should see the User Login Button")
    public void verifyUserLoginButton() {
        Assert.assertTrue(appPageObj.displayLoginScreen());
    }

    @Then("I should see {string} login button text")
    public void registerScreenExists(String loginButtonText) {
        Assert.assertEquals(loginButtonText, appPageObj.loginButtonTextExists());
    }
}
