package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactsTests extends AppiumConfig {
    @BeforeClass
    public void preCondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("mara@gmail.com")
                        .password("Mmar123456$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
    }

    @Test(invocationCount = 3)
    public void createNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("John")
                .lastName("Wow" + i)
                .email("qwe" + i + "@mail.com")
                .phone("54565489" + i)
                .address("NY")
                .description("Friend")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());
    }

    @Test
    public void createNewContactSuccessReq() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Jane")
                .lastName("Hgy" + i)
                .email("qwe" + i + "@mail.com")
                .phone("54565456" + i)
                .address("California")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());

    }

    @Test
    public void createContactWithEmptyName() {
        Contact contact = Contact.builder()
                .lastName("Wowl")
                .email("qwe@mail.com")
                .phone("545654894586")
                .address("NY")
                .description("Empty name")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{name=must not be blank}");
    }
    @Test
    public void createContactWithEmptyLastName() {
        Contact contact = Contact.builder()
                .name("Dave")
                .email("qwe5@mail.com")
                .phone("545654894523")
                .address("NY")
                .description("Empty lastName")
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorContainsText("{lastName=must not be blank}");
    }


    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();
    }
}
