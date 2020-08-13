package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.testng.internal.TestResult;

public class BaseTest {

    protected WebDriver driver;
    protected SoftAssert softly;

    @BeforeMethod
    public void setUp() throws Exception {
        softly = new SoftAssert();
        driver = createFireFoxDriver();
        driver.get("https://totalperform.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Warning: did not successfully quit selenium driver.");
            }
        }

        if (softly != null) {
            try {
                softly.assertAll();
            } catch (AssertionError e) {
                Reporter.getCurrentTestResult().setStatus(TestResult.FAILURE);
                Reporter.getCurrentTestResult().setThrowable(e);
            }
        }
    }

    /**
     * This method is used to create a fireFox driver
     * @return FireFox driver
     * @throws Exception
     */
    private WebDriver createFireFoxDriver() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

}
