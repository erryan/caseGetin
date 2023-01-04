package getin.appium.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;

public class AppDriverFactory {

    private static AppiumDriver<?> Driver;

    private static DesiredCapabilities caps;

    public static AppiumDriver<?> startDriver() {

        caps = new DesiredCapabilities();
        caps.setCapability("device", "iPhone 11 Pro");
        caps.setCapability("os_version", "13");
        caps.setCapability("project", "First TestNg iOS Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");
        caps.setCapability("browserstack.debug", true);
        caps.setCapability("autoAcceptAlerts", true);
        caps.setCapability("app", "bs://1f10b0917c41a5d61cfda66cc84bebbfceb1ea06");

        try {
            Driver = new IOSDriver<>( new URL("https://appiumtester_yHvQVI:ovWxpHg7gaUStb4Z73Lb@hub-cloud.browserstack.com/wd/hub" ), caps);
        } catch (MalformedURLException e) {
            System.out.println(" ==== AVISO : Por favor verifique a url que foi informada para executar os testes. ====");
            System.exit(1);
        } catch (UnreachableBrowserException e) {
            System.out.println(" ==== AVISO : Please start appium server, use appium in command line. ====");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.out.println(" ==== AVISO : Por favor selecionar um dos devices abaixo para executar os testes ==== ");
            System.exit(1);
        }
        return Driver;
    }

    public static AppiumDriver<?> getDriver() {
        return Driver;
    }

    public static void quitDriver() {
        if (Driver != null) {
            Driver.quit();
        }
    }
}
