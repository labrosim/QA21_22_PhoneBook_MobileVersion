package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement menuOptions;
    @FindBy(xpath = "//*[@text='Logout']")
    MobileElement logoutButton;

    public boolean isActivityTitleDisplayed(String text) {
        // return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }
    public AuthenticationScreen logout(){
        menuOptions.click();
        logoutButton.click();
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }
}
