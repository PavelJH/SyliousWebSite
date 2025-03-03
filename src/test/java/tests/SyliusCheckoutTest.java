package tests;

import com.codeborne.selenide.Selenide;
import core.BaseTest;
import dev.failsafe.internal.util.Assert;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import pages.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SyliusCheckoutTest extends BaseTest {
    public static String URL = "https://v2.demo.sylius.com/en_US/";
    public static String USER_NAME = "shop@example.com";
    public static String PASSWORD = "sylius";

    public static String T_SHIRTS_JEANS_MEN = "Men";
    public static String T_SHIRTS_JEANS_WOMEN = "Women";
    public static String CAPS_SIMPLE = "Simple";
    public static String CAPS_WITH_POMPONS = "Dresses";

    @Test
    public void takePrice(){
        Selenide.open("https://v2.demo.sylius.com/en_US/taxons/t-shirts/men?criteria%5Bsearch%5D%5Bvalue%5D=&limit=18&page=2");
        String productPrice = $x("//div[@class='col-12 col-lg-9']//div[@class='products-grid']//span[normalize-space()]").getText();
        System.out.println(productPrice);
    }


    @Test
    public void logInTest(){
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.auth();
        mainPage.checkUserNameOnPage();
        mainPage.clickTShirts(T_SHIRTS_JEANS_MEN);
        ItemsPage itemsPage = new ItemsPage();
        itemsPage.selectItemLimit("18");
        itemsPage.extractAllProductData();
        // Получаем список данных с главной страницы (извлекаем названия и цены)
        List<String> extractedData = itemsPage.extractAllProductData();

        // Получаем список данных после перехода по ссылкам и сбора информации с каждой страницы товара
        List<String> itemPagePriceAndName = itemsPage.processEachLink();

        // Сравниваем два списка
        assertEquals(extractedData, itemPagePriceAndName, "The lists are not the same");

    }
    @Test
    public void buyItemManyClick(){
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.auth();
        mainPage.checkUserNameOnPage();
        mainPage.clickTShirts(T_SHIRTS_JEANS_MEN);
        ItemsPage itemsPage = new ItemsPage();
        itemsPage.firstItemOnPageClick();
        ItemPage itemPage = new ItemPage();
        itemPage.selectSize("XL");
        itemPage.putQuantity("3");
        itemPage.clickAddToCart();
        CartPage cartPage = new CartPage();
        cartPage.deleteFirstItemInCart();


    }

    @Test
    public void buyItem(){
        Selenide.open(URL);
       MainPage mainPage = new MainPage();
        mainPage.clickTShirts(T_SHIRTS_JEANS_MEN);
        ItemsPage itemsPage = new ItemsPage();
        itemsPage.firstItemOnPageClick();
        ItemPage itemPage = new ItemPage();
        itemPage.buyItems("L", "5");
        CartPage cartPage = new CartPage();
        cartPage.deleteFirstItemInCart();

    }

}
