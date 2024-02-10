package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {
    @Test
    public void loginSuccess() {
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillEmail("qwe@d.com")
                .fillPassword("Ff12345$")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessModel2() {
        Assert.assertTrue(new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("mara@gmail.com")
                        .password("Mmar123456$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list"));


    }

    @Test
    public void loginWrongEmail() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("maragmail.com")
                        .password("Mmar123456$").build())
                .submitLoginNegative()
                .isErrorMessageHasText("Login or Password incorrect");
    }

    @AfterMethod
    public void postCondition() {
        if(new ContactListScreen(driver).isActivityTitleDisplayed("Contact list")){
            new ContactListScreen(driver).logout();
        }



    }
}

