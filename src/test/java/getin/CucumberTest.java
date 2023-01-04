package getin;

import getin.appium.core.AppDriverFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"getin"},
        plugin = {"pretty","junit:target/junitreport.xml","json:target/jsonreport.json","html:target/cucumber-reports"})
@SpringBootTest(classes = TestApplication.class)
public class CucumberTest {

    @BeforeClass
    public static void tearUp() {
        AppDriverFactory.startDriver();
    }

    @AfterClass
    public static void tearDown() {
        AppDriverFactory.quitDriver();
    }

}
