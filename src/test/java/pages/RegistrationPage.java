package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.fail;

public class RegistrationPage {
    private final SelenideElement firstNameField = $x("//input[@id='sylius_shop_customer_registration_firstName']");
    private final SelenideElement lastNameField = $x("//input[@id='sylius_shop_customer_registration_lastName']");
    private final SelenideElement emailField = $x("//input[@id='sylius_shop_customer_registration_email']");
    private final SelenideElement phoneField = $x("//input[@id='sylius_shop_customer_registration_phoneNumber']");
    private final SelenideElement subscribeNewsLetterCheckBox = $x("//input[@id='sylius_shop_customer_registration_subscribedToNewsletter']");
    private final SelenideElement passwordField = $x("//input[@id='sylius_shop_customer_registration_user_plainPassword_first']");
    private final SelenideElement verificationPasswordField = $x("//input[@id='sylius_shop_customer_registration_user_plainPassword_second']");
    private final SelenideElement newCustomerText = $x("//h1[@class='h2 mb-']");
    private final SelenideElement createAccountButton = $x("//button[@id='register-button']");
    private final SelenideElement alertUsed = $x("//div[@class='invalid-feedback d-block']");
    private final SelenideElement successText = $x("//div[@class='alert alert-success my-0'][1]");

    public void regNewUser(String firstName, String lastName, String email, String phoneNumber, String password, String verPassword){
        newCustomerText.isDisplayed();
        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        emailField.setValue(email);
        if (alertUsed.isDisplayed()) {
            fail("User already exist");
        }
        phoneField.setValue(phoneNumber);
        subscribeNewsLetterCheckBox.click();
        passwordField.setValue(password);
        passwordField.pressTab();
//        // Прокручиваем до поля подтверждения пароля, чтобы оно стало видимым
//        verificationPasswordField.scrollIntoView(true);
        verificationPasswordField.setValue(verPassword);
        newCustomerText.click();
        verificationPasswordField.pressTab(); // убирает фокус, вызывая событие blur
        sleep(300);
        if (alertUsed.isDisplayed()) {
            fail("The entered passwords don't match");
        }
        createAccountButton.click();
    }

    public String getSuccessText(){
        return successText.getText();
    }

    public void regNewUserNegativeEmailTests(String email){
        newCustomerText.isDisplayed();
        emailField.setValue(email);
        emailField.pressTab();
        sleep(500);
        if (!alertUsed.isDisplayed()) {
            fail("User already exist");
        }
    }

    public void regNewUserNegativeEmailTests(String password, String verPassword){
        newCustomerText.isDisplayed();

        passwordField.setValue(password);
        passwordField.pressTab();
        verificationPasswordField.setValue(verPassword);
        verificationPasswordField.pressTab();
        sleep(500);
        if (!alertUsed.isDisplayed()) {
            fail("The entered passwords don't match");
        }
    }

}
