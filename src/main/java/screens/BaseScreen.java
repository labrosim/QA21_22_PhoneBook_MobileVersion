package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class BaseScreen {
    AppiumDriver<MobileElement> driver;

    public BaseScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void type(MobileElement element, String text) {

        element.click();
        element.clear();
        if (text != null) {
            element.sendKeys(text);
        }
        driver.hideKeyboard();
    }

    public boolean isShouldHave(MobileElement element, String text, int time) {
        return new WebDriverWait(driver, time)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }


    void should(MobileElement element, int time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
    }

    public void checkAlertText(String text) {
        Alert alert = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
    }

    public boolean isElementDisplayed(MobileElement element) {
        try {
            should(element,5);
            return element.isDisplayed();

        } catch (IllegalAccessError e) {
            return false;
        }
    }

    public boolean isElementPresentInList(List<MobileElement> list) {
        return list.size()>0;
    }


    public void shouldLessOne(List<MobileElement> list, int less) {
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.numberOfElementsToBeLessThan
                        (By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowContainer']"),less));

    }
}
