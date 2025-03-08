package tests;

import com.codeborne.selenide.Selenide;
import core.BaseTest;
import org.junit.jupiter.api.Test;
import pages.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static readConfig.ConfigProvider.*;

public class Compare extends BaseTest {

    @Test
    public void comparePriceInPageItemAndItem(){
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
    public void comparesItemsPageWithCartPrice(){
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.auth();
        mainPage.checkUserNameOnPage();
        CartPage cartPage = new CartPage();
        mainPage.cleanAllItems();
        mainPage.clickLogo();
        mainPage.clickTShirts(T_SHIRTS_JEANS_MEN);
        ItemsPage itemsPage = new ItemsPage();
        itemsPage.firstItemOnPageClick();
        ItemPage itemPage = new ItemPage();
        itemPage.selectSize(SIZE_XL);
        String itemPagePrice = itemPage.getPrise(); // сохраняем цену товара
        itemPage.clickAddToCart();
        String itemCartPriceUnit = cartPage.getUnitPrice();
        assertEquals(itemCartPriceUnit, itemPagePrice, "The lists are not the same");
    }
    @Test
    public void comparesItemsPageWithCartQuantity(){
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.auth();
        mainPage.checkUserNameOnPage();
        CartPage cartPage = new CartPage();
        mainPage.cleanAllItems();
        mainPage.clickLogo();
        mainPage.clickTShirts(T_SHIRTS_JEANS_MEN);
        ItemsPage itemsPage = new ItemsPage();
        itemsPage.firstItemOnPageClick();
        ItemPage itemPage = new ItemPage();
        itemPage.putQuantity(QUANTITY_03);
        itemPage.selectSize(SIZE_XL);
        itemPage.clickAddToCart();

        assertEquals(cartPage.getQtyNumber(), QUANTITY_03, "The lists are not the same");
    }
    @Test
    public void comparePriceMainPageCartAndCartPage() {
        Selenide.open(URL);
        MainPage mainPage = new MainPage();
        mainPage.goToLogInPage();
        AuthPage authPage = new AuthPage();
        authPage.auth();
        mainPage.checkUserNameOnPage();
        CartPage cartPage = new CartPage();
        mainPage.cleanAllItems();
        mainPage.clickLogo();
        mainPage.clickTShirts(T_SHIRTS_JEANS_MEN);
        ItemsPage itemsPage = new ItemsPage();
        itemsPage.firstItemOnPageClick();
        ItemPage itemPage = new ItemPage();
        itemPage.selectSize(SIZE_XL);
        itemPage.putQuantity(QUANTITY_03);
        itemPage.clickAddToCart();
        String totalPricePageCart = cartPage.getTotalPriceFull();
        String totalPriceMainPage = mainPage.getFullPriceCart();
        System.out.println(totalPriceMainPage);
        System.out.println(totalPricePageCart);
        assertTrue(totalPriceMainPage.contains(totalPriceMainPage),
                "Total price in cart does not contain the expected main page price");
    }
}
