package getin.appium.core;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;

public class AppDriverFactory {

    private static IOSDriver Driver;

    private static DesiredCapabilities caps;

    public static IOSDriver startDriver() {

        caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "iPhone 11 Pro");
        caps.setCapability("platformVersion", "13");
        caps.setCapability("platformName", "ios");
        caps.setCapability("project", "First TestNg iOS Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");
        caps.setCapability("browserstack.debug", true);
        caps.setCapability("autoAcceptAlerts", true);
        caps.setCapability("app", "bs://1f10b0917c41a5d61cfda66cc84bebbfceb1ea06");

        try {
            Driver = new IOSDriver( new URL("https://appiumtester_yHvQVI:ovWxpHg7gaUStb4Z73Lb@hub-cloud.browserstack.com/wd/hub" ), caps);
        } catch (MalformedURLException e) {
            System.out.println("Por favor verifique a url que foi informada para executar os testes.");
            System.exit(1);
        } catch (UnreachableBrowserException e) {
            System.out.println("Please start appium server, use appium in command line.");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.out.println("Por favor selecionar um dos devices abaixo para executar os testes");
            System.exit(1);
        }
        return Driver;
    }

    public static IOSDriver getDriver() {
        return Driver;
    }

    public static void quitDriver() {
        if (Driver != null) {
            Driver.quit();
        }
    }
}
