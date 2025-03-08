package tests;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import core.BaseTest;
import org.junit.jupiter.api.Test;
import pages.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static readConfig.ConfigProvider.*;


public class Buy extends BaseTest {

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
        itemPage.selectSize(SIZE_XL);
        itemPage.putQuantity(QUANTITY_03);
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
        itemPage.buyItems(SIZE_L, QUANTITY_03);
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
        itemPage.selectSize(SIZE_XL);
        itemPage.putQuantity(QUANTITY_03);
        itemPage.clickAddToCart();
        cartPage.checkAmountPrice();
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
        itemPage.selectSize(SIZE_XL);
        itemPage.putQuantity(QUANTITY_03);
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
        checkOutItemsPage.fillBillingAddress(firstName, lastName, street, COUNTRY_DE, city, postCode, phoneNumber);
        checkOutItemsPage.shippingShipment();
        checkOutItemsPage.payment();

        // Формирование итоговой строки
        String formattedBillingAddress = checkOutItemsPage.getFormattedBillingAddress(firstName, lastName, phoneNumber, street, city, postCode, COUNTRY_DE);
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
        assertEquals("UPS", getShipmentSummery);
        assertEquals(getOrderTotalPRice, getTotalPrice);

//        System.out.println(getOrderTotalPRice);
//        System.out.println(getTotalPrice);

    }
}
