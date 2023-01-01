package getin.appium;

import getin.appium.core.AppDriverProvider;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static java.time.Duration.ofMillis;

public class AppPageObj {

    @Autowired
    private AppDriverProvider appProvider;

    @Value("${mobile.timeout.second}")
    private int waitSecond;

    private final By btnLogIn = MobileBy.AccessibilityId("com.bluepay.roket:id/user_login_Button");

    private final By loginScreenBtnLogIn = MobileBy.AccessibilityId("com.bluepay.roket:id/login_action_Button");

    public synchronized WebElement findElement(By byElement) {
        appProvider.getDriver().manage().timeouts().implicitlyWait(ofMillis(waitSecond));
        WebElement element;
        try {
            element = new WebDriverWait(appProvider.getDriver(), ofMillis(waitSecond))
                    .until(ExpectedConditions.presenceOfElementLocated(byElement));
        } catch (Exception ex) {
            element = null;
        }
        return element;
    }

    public void waitForElementToBeClickable(By element) {
        new WebDriverWait(appProvider.getDriver(), ofMillis(waitSecond))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isDisplayed(By element) {
        boolean isDisplay;
        try {
            isDisplay = findElement(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
        return isDisplay;
    }

    public String getText(By element) {
        String text;
        try {
            text = findElement(element).getText();
        } catch (Exception e) {
            return null;
        }
        return text;
    }

    public void click(By element) {
        waitForElementToBeClickable(element);
        findElement(element).click();
    }

    public boolean displayLoginButton() {
        return isDisplayed(btnLogIn);
    }

    public boolean displayLoginScreen() {
        return isDisplayed(loginScreenBtnLogIn);
    }

    public String loginButtonTextExists() {
        return getText(btnLogIn);
    }

    public void clickLoginButton() {
        click(btnLogIn);
    }

    public void clickLoginButtontoLoginScreen() {
        click(loginScreenBtnLogIn);
    }
}
