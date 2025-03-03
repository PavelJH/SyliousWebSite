package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class AuthPage {

    private final SelenideElement userNameField = $x("//input[@name='_username']");
    private final SelenideElement passwordField = $x("//input[@name='_password']");
    private final SelenideElement rememberMeCheckBox = $x("//input[@id='_remember_me']");
    private final SelenideElement logInButton = $x("//button[@id='login-button']");
    private final SelenideElement forgotPasswordButton = $x("//a[normalize-space()='Forgot password?']");
    private final SelenideElement registerHereButton = $x("//a[@id='register-here-button']");
    private final SelenideElement userNameText = $x("//strong[contains(text(), '@')]");
    private final SelenideElement passwordText = $x("//p/strong[preceding-sibling::text()[contains(., 'Password:')]]");


    public void auth(){
        String name = userNameText.getText();
        String password = passwordText.getText();
        userNameField.setValue(name);
        passwordField.setValue(password);
        logInButton.click();
        System.out.println("For now, UserName is: "+"'"+ name +"'"+ " and Password is: " + "'"+ password+ "'");
    }




}
