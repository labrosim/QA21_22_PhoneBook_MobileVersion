package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class SplashScreen extends BaseScreen{
    public SplashScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/version_text']")
    MobileElement versionTextView;

    @FindBy(id="com.sheygam.contactapp:id/title_text")
    MobileElement titleTextView;

    public String getCurrentVersion(){
        return  versionTextView.getText();
    }


}

