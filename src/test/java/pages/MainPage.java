package pages;

import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;


import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final SelenideElement logInButton = $x("//a[@id='login-page-button']");
    private final SelenideElement registerButton = $x("//a[@id='register-page-button']");
    private final SelenideElement cartButton = $x("//button[@aria-label='cart button']");
    private final SelenideElement amountCart = $x("//span[@class='d-none d-lg-block ps-1']");
    private final SelenideElement syliusButtonLogo = $x("//a[@class='d-inline-block py-lg-2']");
    private final SelenideElement userNameTextOnMainPage = $x("//span[normalize-space()]");

    public void goToLogInPage(){
        logInButton.click();
    }

    public void checkUserNameOnPage(){
        String userNameOnMainPage = userNameTextOnMainPage.getText();
        Assertions.assertEquals("Hello John!", userNameOnMainPage, "The text does not match!");
    }



}
