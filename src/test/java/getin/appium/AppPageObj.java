package getin.appium;


import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSBy;

public class AppPageObj {

    @iOSBy(id = "com.bluepay.roket:id/user_login_Button")
    private MobileElement loginBtn;

    public void isDisplay() {
        this.loginBtn.isDisplayed();
    }
}
