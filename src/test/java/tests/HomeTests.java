package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTests extends BaseTest {

    @Test
    void testToValidateBasicProjectIsWorking() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLoaded(5), "Total Performance page is not loaded");
    }
}
