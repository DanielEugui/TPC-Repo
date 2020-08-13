package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

    private SeleniumUtils() { }

    /**
     * Get a WebElement from current driver position using a By locator. If the
     * element is not present after given timeout, null is returned.
     *
     * @param driver
     * @param byLocator
     * @param seconds max time to wait for the element to be present
     * @return
     */
    public static WebElement getWebElement(WebDriver driver, final By byLocator, int seconds) {
        WebElement webElement = null;

        try {
            webElement = (new WebDriverWait(driver, seconds)).until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver d) {
                    return d.findElement(byLocator);
                }
            });
        } catch (Exception e) {
            return null;
        }
        return webElement;
    }

    /**
     * Wait for the given element to disappear. If the element disappears before the
     * timeout, the function returns True. If the element is still present after the
     * given timeout, the function returns False.
     *
     * @param driver
     * @param byLocator
     * @param seconds max time to wait for the element to disappear
     * @return true if the element disappeared, else false
     */
    public static Boolean waitForElementToDisappear(WebDriver driver, final By byLocator, int seconds) {
        try {
            new WebDriverWait(driver, seconds).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return !d.findElement(byLocator).isDisplayed();
                }
            });
        } catch (Exception e) {
            if (null != e.getCause() && e.getCause().getClass() == NoSuchElementException.class) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Wait for an element to be present and visible
     *
     * @param driver
     * @param byLocator
     * @param seconds max seconds to wait for element to present and be visible
     */
    public static Boolean waitForElementToBeVisible(WebDriver driver, final By byLocator, int seconds) {
        try {
            new WebDriverWait(driver, seconds).ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(byLocator));
            return Boolean.TRUE;
        } catch (TimeoutException e) {
            return Boolean.FALSE;
        }
    }

    /**
     * Wait for the page to be loaded and for the DOM to be processed. Returns true
     * if page loaded within timeout.
     *
     * @param driver
     * @param seconds max seconds to wait for page to load
     */
    public static Boolean waitForPageToBeLoaded(WebDriver driver, int seconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        Wait<WebDriver> wait = new WebDriverWait(driver, seconds);
        try {
            wait.until(expectation);
            return Boolean.TRUE;
        } catch (Throwable error) {
            return Boolean.FALSE;
        }
    }
}
