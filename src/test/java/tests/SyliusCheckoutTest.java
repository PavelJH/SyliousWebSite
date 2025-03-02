package tests;

import com.codeborne.selenide.Selenide;
import core.BaseTest;
import org.junit.jupiter.api.Test;
import pages.AuthPage;
import pages.MainPage;

public class SyliusCheckoutTest extends BaseTest {
    public static String URL = "https://v2.demo.sylius.com/en_US/";
    public static String USER_NAME = "shop@example.com";
    public static String PASSWORD = "sylius";


    @Test
    public void logInTest(){
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.auth();
        mainPage.checkUserNameOnPage();

    }

}
