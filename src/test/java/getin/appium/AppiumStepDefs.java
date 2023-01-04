package getin.appium;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AppiumStepDefs extends AppPageObj {

    @Then("I should see the User Login Button")
    public void isDisplayUserLoginButton() {
        Assert.assertTrue(isDisplayLoginButton());
    }

    @Then("I should see {string} login button text")
    public void verifyUserLoginButtonText(String getText) {
        Assert.assertEquals(getText, getLoginText());
    }

    @When("I click on Login button")
    public void clickLoginBtn() {
        clickLoginButton();
    }

    @Then("I should see login screen")
    public void verifyLoginScreen() {
        Assert.assertTrue(isDisplayScreen());
    }

    @When("I click on login button on Login Screen")
    public void clickLoginScreenBtn() {
        clickLoginScreenButton();
    }

    @Then("I should see {string} text on the login Button")
    public void verifyUserLoginButtonName(String getText) {
        Assert.assertEquals(getText, getLoginText());
    }
}
