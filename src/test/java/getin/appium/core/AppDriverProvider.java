package getin.appium.core;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.net.URL;

public class AppDriverProvider {

    public IOSDriver<IOSElement> driver;

    private DesiredCapabilities caps;

    @Value("${mobile.browserstack.username}")
    public String userName;

    @Value("${mobile.browserstack.accessKey}")
    public String accessKey;

    @Value("${mobile.capability.device}")
    public String device;

    @Value("${mobile.capability.os_version}")
    public String os_version;

    @Value("${mobile.capability.project}")
    public String project;

    @Value("${mobile.capability.build}")
    public String build;

    @Value("${mobile.capability.name}")
    public String name;

    @Value("${mobile.capability.app}")
    public String app;

    public AppDriverProvider() {
        caps = new DesiredCapabilities();
        caps.setCapability("device", device);
        caps.setCapability("os_version", os_version);
        caps.setCapability("project", project);
        caps.setCapability("build", build);
        caps.setCapability("name", name);
        caps.setCapability("browserstack.debug", true);
        caps.setCapability("app", app);

        try {
            driver = new IOSDriver<IOSElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub" ), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public IOSDriver<IOSElement> getDriver() {
        return driver;
    }
}
