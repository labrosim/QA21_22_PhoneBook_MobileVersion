package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement menuOptions;
    @FindBy(xpath = "//*[@content-desc='More options']")
    List<MobileElement> menuOptionsList;
    @FindBy(xpath = "//*[@text='Logout']")
    MobileElement logoutButton;
    @FindBy(xpath = "//*[@content-desc='add']")
    MobileElement plusBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> contactNameList;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contactList;
    @FindBy(id = "android:id/button1")
    MobileElement OkBtn;

    int countBefore;
    int countAfter;

    public boolean isActivityTitleDisplayed(String text) {
        // return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthenticationScreen logout() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout2() {
        if (isElementDisplayed(menuOptions)) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public AuthenticationScreen logout3() {
        if (isElementPresentInList(menuOptionsList)) {
            menuOptions.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm() {
        if (activityTextView.getText().equals("Contact list")) {
            should(plusBtn, 5);
            plusBtn.click();
        }
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {
        // List<AndroidElement> list =  driver.findElements(By.xpath(""));
        isShouldHave(activityTextView, "Contact list", 5);
        System.out.println("size of list " + contactNameList.size());
        boolean isPresent = false;

        for (MobileElement el : contactNameList) {
            if (el.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

    public ContactListScreen deleteFirstContact() {
        isActivityTitleDisplayed("Contact list");
        int countBefore = contactList.size();
        System.out.println(countBefore);
        MobileElement first = contactList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX() + rectangle.getWidth() / 8;
        int y = rectangle.getY() + rectangle.getHeight() / 2;
        int xTo = rectangle.getX() + (rectangle.getWidth() / 8) * 7;
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom, y))
                .moveTo(PointOption.point(xTo,y))
                .release().perform();
        should(OkBtn,8);
        OkBtn.click();
        shouldLessOne(contactList, countBefore);

        int countAfter = contactList.size();
        System.out.println(countAfter);
        return this;
    }



    public ContactListScreen isListSizeLessOnOne() {
        Assert.assertEquals(countBefore - countAfter, 1);
        return this;
    }
}
