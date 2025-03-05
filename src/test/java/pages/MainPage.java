package pages;

import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
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
    private final SelenideElement mainPageFullPriceCart = $x("(//span[@class='d-none d-lg-block ps-1'])[1]");

    // Метод для получения кнопки по названию категории
    private SelenideElement getCategoryButton(String categoryName) {
        return $x("//a[normalize-space()='" + categoryName + "']");
    }

    public void selectCategory(String category) {
        String xpath = "//a[contains(text(), '" + category + "')]";
        $x(xpath).click();
    }

    public void goToLogInPage(){
        logInButton.click();
    }

    public void checkUserNameOnPage(){
        String userNameOnMainPage = userNameTextOnMainPage.getText();
        Assertions.assertEquals("Hello John!", userNameOnMainPage, "The text does not match!");
    }

    public void clickTShirts(String category) {
        getCategoryButton("T-shirts").click();
        selectCategory(category);
    }

    public void clickCaps(String category) {
        getCategoryButton("Caps").click();
        selectCategory(category);
    }

    public void clickDresses() {
        getCategoryButton("Dresses").click();
    }

    public void clickJeans(String category) {
        getCategoryButton("Jeans").click();
        selectCategory(category);
    }


    public String getFullPriceCart(){
        return mainPageFullPriceCart.getText();
    }




}
