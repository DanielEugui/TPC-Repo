package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.SeleniumUtils;

public abstract class BasePage {

    protected WebDriver driver;
    private static final int PAGE_LOAD_TIMEOUT = 90;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        if (!isLoaded(PAGE_LOAD_TIMEOUT)) {
            throw new IllegalStateException("Page is not loaded after waiting for " + PAGE_LOAD_TIMEOUT);
        }
    }

    protected abstract By getPageLoadedLocator();

    /**
     * This method is used to wait until page is loaded
     * @param timeOut time to wait of page loaded
     * @return true if page is loaded, false if page is not loaded after wait timeOut time
     */
    public boolean isLoaded(int timeOut) {
        return SeleniumUtils.waitForElementToBeVisible(driver, getPageLoadedLocator(), timeOut);
    }
}
