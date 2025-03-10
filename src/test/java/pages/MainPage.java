package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import dev.failsafe.internal.util.Assert;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {
    private final SelenideElement logInButton = $x("//a[@id='login-page-button']");
    private final SelenideElement registerButton = $x("//a[@id='register-page-button']");
    private final SelenideElement cartButton = $x("//button[@aria-label='cart button']");
    private final SelenideElement cartViewAndEditButton = $x("//a[normalize-space()='View and edit cart']");
    private final SelenideElement amountCart = $x("//span[@class='d-none d-lg-block ps-1']");
    private final SelenideElement syliusButtonLogo = $x("//a[@class='d-inline-block py-lg-2']");
    private final SelenideElement userNameTextOnMainPage = $x("//span[normalize-space()]");
    private final SelenideElement mainPageFullPriceCart = $x("(//span[@class='d-none d-lg-block ps-1'])[1]");

    private final SelenideElement instagramLink = $x("//a[@href='https://www.instagram.com/sylius.team/']");
    private final SelenideElement faceBookLink = $x("//a[@href='https://www.facebook.com/SyliusEcommerce/']");
    private final SelenideElement xTwitterLink = $x("//a[@href='https://x.com/Sylius']");
    List<SelenideElement> socialLinks = $$x("//div[contains(@class, 'd-flex') and contains(@class, 'justify-content-center') and contains(@class, 'mb-5')]//a[@class='link-reset']");

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

    public void clickLogo(){
        syliusButtonLogo.click();
    }

    public void cleanAllItems(){
        cartButton.click();
        cartViewAndEditButton.click();
        CartPage cartPage = new CartPage();
        cartPage.deleteFirstItemInCart();
    }

    public void verifyAllSocialLinks() {
        // Запоминаем идентификатор исходной вкладки
        String originalWindow = WebDriverRunner.getWebDriver().getWindowHandle();

        // Проходим по каждой найденной ссылке
        for (SelenideElement link : socialLinks) {
            // Прокручиваем до ссылки, чтобы она стала видимой
            link.scrollTo();
            sleep(700);

            // Получаем значение href ссылки
            String href = link.getAttribute("href");
            String expectedSubstring = "";

            // Определяем ожидаемую подстроку в зависимости от содержимого href
            if (href.contains("instagram")) {
                expectedSubstring = "instagram.com";
            } else if (href.contains("facebook")) {
                expectedSubstring = "facebook.com";
            } else if (href.contains("x.com")) {
                expectedSubstring = "x.com";
            }

            // Кликаем по ссылке, которая открывает новую вкладку
            link.click();

            // Переключаемся на новую вкладку (обычно она имеет индекс 1)
            switchTo().window(1);

            // Получаем URL открытой вкладки
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();

            // Проверяем, что текущий URL содержит ожидаемую подстроку
            assertTrue(currentUrl.contains(expectedSubstring),
                    "URL (" + currentUrl + ") does not contain the expected text: " + expectedSubstring);

            // Закрываем новую вкладку
            closeWindow();

            // Возвращаемся к исходной вкладке
            switchTo().window(originalWindow);
        }
    }


}
