package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private static final String ELEMENT_1 = "(//img[@title = 'test automation'])[1]";
    private static final String ELEMENT_2 = "(//img[@title = 'test automation'])[2]";

    @FindBy(xpath = ELEMENT_1)
    private WebElement totalPerformanceImg;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageLoadedLocator() {
        return By.xpath(ELEMENT_2 + " | " + ELEMENT_2);
    }
}
