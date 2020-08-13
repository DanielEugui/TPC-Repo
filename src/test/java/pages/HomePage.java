package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.SeleniumUtils;

public class HomePage extends BasePage {

    // ALL ELEMENT WILL BE PROVIDED BY FILES.JSON (USING find_element() methods exercise 1)
    private static final String ELEMENT_1 = "(//img[@title = 'test automation'])[1]";
    private static final String ELEMENT_2 = "(//img[@title = 'test automation'])[2]";
    private static final String ELEMENT_3 = "(//img[@title = 'test automation'])[3]";

    @FindBy(xpath = ELEMENT_1)
    private WebElement totalPerformanceImg;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageLoadedLocator() {
        return By.xpath(ELEMENT_2 + " | " + ELEMENT_2);
    }

    public boolean isElement3Visible() {
        return (SeleniumUtils.waitForElementToBeVisible(driver, By.xpath(ELEMENT_3), 4));
    }
}
