package tests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import core.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AuthPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static readConfig.ConfigProvider.URL;

public class Authorization extends BaseTest {

    @Test
    @Tag("positive")
    public void auth(){
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.auth();
        mainPage.checkUserNameOnPage();
    }

    @Test
    @Tag("negative")
    public void negativeAuth(){
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.authWithData("Admin", "Admin1234");
        String errorLogIn = authPage.errorAlertLogIn();
        assertEquals("Error\n" + "Invalid credentials.", errorLogIn);
    }

    @Test
    @Tag("negative")
    public void negativeAuthUsername(){
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.authWithData("Admin", "sylius");
        String errorLogIn = authPage.errorAlertLogIn();
        assertEquals("Error\n" + "Invalid credentials.", errorLogIn);
    }

    @Test
    @Tag("positive")
    public void negativeAuthPassword(){
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.authWithData("shop@example.com", "admin1234");
        String errorLogIn = authPage.errorAlertLogIn();
        assertEquals("Error\n" + "Invalid credentials.", errorLogIn);
    }

    @Test
    @Tag("positive")
    public void createNewUser(){
        Faker faker = new Faker();
        Selenide.open(URL);
        AuthPage authPage = new AuthPage();
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        authPage.clickRegisterHere();
        RegistrationPage registrationPage = new RegistrationPage();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String password = faker.internet().password();;

        registrationPage.regNewUser(firstName, lastName, email, phoneNumber, password, password);
        String successText = registrationPage.getSuccessText();
        assertEquals("Success\n" + "Thank you for registering.", successText, "Not equals");
    }

    @Test
    @Tag("negative")
    public void CreateNewUserWithExistEmail(){
        Faker faker = new Faker();
        Selenide.open(URL);
        AuthPage authPage = new AuthPage();
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        authPage.clickRegisterHere();
        RegistrationPage registrationPage = new RegistrationPage();

        String email = "shop@example.com";

        registrationPage.regNewUserNegativeEmailTests(email);
    }

    @Test
    @Tag("negative")
    public void createNewUserWrongVerificationPassword(){
        Faker faker = new Faker();
        Selenide.open(URL);
        AuthPage authPage = new AuthPage();
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        authPage.clickRegisterHere();
        RegistrationPage registrationPage = new RegistrationPage();

        String password = faker.internet().password();
        String verificationPassword = faker.internet().password();

        registrationPage.regNewUserNegativeEmailTests(password, verificationPassword);
    }

}
