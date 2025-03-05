package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CheckOutItemsPage {
    private final SelenideElement firstNameField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_firstName']");
    private final SelenideElement lastNameField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_lastName']");
    private final SelenideElement streetAddressField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_street']");
    private final SelenideElement countryList = $x("//select[@id='sylius_shop_checkout_address_billingAddress_countryCode']");
    private final SelenideElement countrySelect = $x("#sylius_shop_checkout_address_billingAddress_countryCode");
    private final SelenideElement cityField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_city']");
    private final SelenideElement postCodeField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_postcode']");
    private final SelenideElement phoneNumberField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_phoneNumber']");

    private final SelenideElement backToStoreButton = $x("//a[normalize-space()='Back to store']");
    private final SelenideElement changeAddressButton = $x("//a[normalize-space()='Change address']");
    private final SelenideElement nextButton = $x("button[type='submit']");

    private final SelenideElement upsCheckBox = $x("//input[@id='sylius_shop_checkout_select_shipping_shipments_0_method_0']");
    private final SelenideElement dhlExpressCheckBox = $x("(//input[@id='sylius_shop_checkout_select_shipping_shipments_0_method_1'])[1]");
    private final SelenideElement fedExCheckBox = $x("((//input[@id='sylius_shop_checkout_select_shipping_shipments_0_method_2'])[1]");


    public void fillBillingAddress(String name,String lastName, String street, String country, String city, String postCodeNumber, String phoneNumber){
        firstNameField.setValue(name);
        lastNameField.setValue(lastName);
        streetAddressField.setValue(city);
        streetAddressField.setValue(street);
        countryList.click();
        countrySelect.selectOption(country);
        cityField.setValue(city);
        postCodeField.setValue(postCodeNumber);
        phoneNumberField.setValue(phoneNumber);
        nextButton.click();
    }

    public void shippingSShipment(){
        dhlExpressCheckBox.click();
        nextButton.click();
    }

}
