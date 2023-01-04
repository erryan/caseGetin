package getin.appium;

import getin.appium.core.AppDriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AppPageObj {

    private final By lgnBtn = MobileBy.AccessibilityId("com.bluepay.roket:id/user_login_Button");

    private final By loginScreenBtn = MobileBy.AccessibilityId("com.bluepay.roket:id/login_action_Button");

    public AppPageObj() {
        PageFactory.initElements(new AppiumFieldDecorator(AppDriverFactory.getDriver()), this);
    }

    public Boolean isDisplayLoginButton() {
        AppDriverFactory.getDriver().manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        return AppDriverFactory.getDriver().findElement(lgnBtn).isDisplayed();
    }

    public Boolean isDisplayScreen() {
        AppDriverFactory.getDriver().manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        return AppDriverFactory.getDriver().findElement(loginScreenBtn).isDisplayed();
    }

    public String getLoginText() {
        AppDriverFactory.getDriver().manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        return AppDriverFactory.getDriver().findElement(lgnBtn).getText();
    }

    public void clickLoginButton() {
        AppDriverFactory.getDriver().manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        AppDriverFactory.getDriver().findElement(lgnBtn).click();
    }

    public void clickLoginScreenButton() {
        AppDriverFactory.getDriver().manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        AppDriverFactory.getDriver().findElement(loginScreenBtn).click();
    }
}
