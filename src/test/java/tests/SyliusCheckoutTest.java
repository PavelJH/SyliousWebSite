package tests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import core.BaseTest;
import org.junit.jupiter.api.Test;
import pages.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SyliusCheckoutTest extends BaseTest {
    public static String URL = "https://v2.demo.sylius.com/en_US/";
    public static String USER_NAME = "shop@example.com";
    public static String PASSWORD = "sylius";

    public static String T_SHIRTS_JEANS_MEN = "Men";
    public static String T_SHIRTS_JEANS_WOMEN = "Women";
    public static String CAPS_SIMPLE = "Simple";
    public static String CAPS_WITH_POMPONS = "Dresses";

    public static String QUANTITY = "3";


    @Test
    public void takePrice(){
        Selenide.open("https://v2.demo.sylius.com/en_US/taxons/t-shirts/men?criteria%5Bsearch%5D%5Bvalue%5D=&limit=18&page=2");
        String productPrice = $x("//div[@class='col-12 col-lg-9']//div[@class='products-grid']//span[normalize-space()]").getText();
        System.out.println(productPrice);
    }


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
    public void buyItemManyClick(){
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
        itemPage.selectSize("XL");
        itemPage.putQuantity("3");
        itemPage.clickAddToCart();
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
        itemPage.buyItems("L", QUANTITY);
        CartPage cartPage = new CartPage();
        cartPage.deleteFirstItemInCart();

    }

    @Test
    public void buyWithCheckAmountPrice(){
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
        itemPage.selectSize("XL");
        itemPage.putQuantity(QUANTITY);
        itemPage.clickAddToCart();
        cartPage.checkAmountPrice();
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
        itemPage.selectSize("XL");
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
        itemPage.putQuantity(QUANTITY);
        itemPage.selectSize("XL");
        itemPage.clickAddToCart();

        assertEquals(cartPage.getQtyNumber(), QUANTITY, "The lists are not the same");
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
        itemPage.selectSize("XL");
        itemPage.putQuantity(QUANTITY);
        itemPage.clickAddToCart();
        String totalPricePageCart = cartPage.getTotalPriceFull();
        String totalPriceMainPage = mainPage.getFullPriceCart();
        assertTrue(totalPriceMainPage.contains(totalPricePageCart),
                "Total price in cart does not contain the expected main page price");
    }

    @Test
    public void buyAndCheckOrder(){
        Faker faker = new Faker();
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
        itemPage.selectSize("XL");
        itemPage.putQuantity(QUANTITY);
        itemPage.clickAddToCart();
        String getOrderTotalPRice = cartPage.getOrderTotalPriceInCartFull();
        cartPage.clickCheckOutButton();
        CheckOutItemsPage checkOutItemsPage = new CheckOutItemsPage();

        // Генерация данных с Faker
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String street = faker.address().streetAddress();
        String city = faker.address().city();
        String postCode = faker.address().zipCode();
        String phoneNumber = faker.phoneNumber().cellPhone();

        // Заполнение формы
        checkOutItemsPage.fillBillingAddress(firstName, lastName, street, "Germany", city, postCode, phoneNumber);
        checkOutItemsPage.shippingShipment();
        checkOutItemsPage.payment();

        // Формирование итоговой строки
        String formattedBillingAddress = checkOutItemsPage.getFormattedBillingAddress(firstName, lastName, phoneNumber, street, city, postCode);
//        System.out.println(formattedBillingAddress);

        String getBillingAddressSummary = checkOutItemsPage.getBillingAddressSummary();
        String getShippingAddressSummery = checkOutItemsPage.getShippingAddressSummary();
        String getPaymentsSummery = checkOutItemsPage.getPaymentsSummary();
        String getShipmentSummery = checkOutItemsPage.getShippingSummary();

        String getTotalPrice = checkOutItemsPage.getTotalPrice();

//        System.out.println("its Billing Address: " + getBillingAddressSummary);
//        System.out.println("its Shipping Address: " + getShippingAddressSummery);
//        System.out.println("its Payment: " + getPaymentsSummery);
//        System.out.println("its Shipment: " + getShipmentSummery);

        assertEquals(formattedBillingAddress.trim(), getBillingAddressSummary.trim());
        assertEquals(getShippingAddressSummery.trim(), getBillingAddressSummary.trim());
        assertEquals(formattedBillingAddress.trim(), getShippingAddressSummery.trim());

        assertEquals("Cash on delivery", getPaymentsSummery);
        assertEquals("FedEx", getShipmentSummery);
        assertEquals(getOrderTotalPRice, getTotalPrice);

//        System.out.println(getOrderTotalPRice);
//        System.out.println(getTotalPrice);



    }

}
