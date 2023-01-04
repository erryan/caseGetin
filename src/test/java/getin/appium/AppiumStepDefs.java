package getin.appium;

import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class AppiumStepDefs {

    @Autowired
    private AppPageObj appPageObj;

    @Then("I should see the User Login Button")
    public void verifyUserLoginButton() {
        appPageObj.isDisplay();
        //System.out.println("XXXXXXXX: " + appPageObj.getLoginButtonText());
    }
}
