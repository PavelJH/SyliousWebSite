package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CheckOutItemsPage {
    private final SelenideElement firstNameField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_firstName']");
    private final SelenideElement lastNameField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_lastName']");
    private final SelenideElement streetAddressField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_street']");
    private final SelenideElement countryList = $x("//select[@id='sylius_shop_checkout_address_billingAddress_countryCode']");
    private final SelenideElement cityField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_city']");
    private final SelenideElement postCodeField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_postcode']");
    private final SelenideElement phoneNumberField = $x("//input[@id='sylius_shop_checkout_address_billingAddress_phoneNumber']");

    private final SelenideElement backToStoreButton = $x("//a[normalize-space()='Back to store']");
    private final SelenideElement changeAddressButton = $x("//a[normalize-space()='Change address']");
    private final SelenideElement nextButton = $x("//button[normalize-space()='Next']");

    private final SelenideElement upsCheckBox = $x("(//input[@id='sylius_shop_checkout_select_shipping_shipments_0_method_0'])[1]");
    private final SelenideElement dhlExpressCheckBox = $x("(//input[@id='sylius_shop_checkout_select_shipping_shipments_0_method_1'])[1]");
    private final SelenideElement fedExCheckBox = $x("(//input[@id='sylius_shop_checkout_select_shipping_shipments_0_method_2'])[1]");

    private final SelenideElement paypalPayment = $x("//input[@id='sylius_shop_checkout_select_payment_payments_0_method_0']");
    private final SelenideElement cashOnDeliveryPayment = $x("//input[@id='sylius_shop_checkout_select_payment_payments_0_method_1']");
    private final SelenideElement bankTransferPayment = $x("(//input[@id='sylius_shop_checkout_select_payment_payments_0_method_1'])[1]");
    private final SelenideElement changeShippingMethodButton = $x("//a[normalize-space()='Change shipping method']");

    private final SelenideElement billingAddressInfo = $x("//div[@class='row']//div[1]//div[1]//div[2]");
    private final SelenideElement shippingAddressInfo = $x("(//div[@class='row']//div[2]//div[1]//div[2]");
    private final SelenideElement paymentsInfo = $x("(//div[@class='card-body d-flex flex-column gap-2']//div[@class='me-auto'])[1]");
    private final SelenideElement shippingInfo = $x("(//div[@class='card-body d-flex flex-column gap-2']//div[@class='me-auto'])[2]");
    private final SelenideElement placeOrderButton = $x("//button[@id='confirmation-button']");

    public void fillBillingAddress(String name,String lastName, String street, String country, String city, String postCodeNumber, String phoneNumber){
        firstNameField.clear();
        firstNameField.setValue(name);
        lastNameField.clear();
        lastNameField.setValue(lastName);
        streetAddressField.clear();
        streetAddressField.setValue(street);
        countryList.click();
        SelenideElement countrySelect = $x("//select[@id='sylius_shop_checkout_address_billingAddress_countryCode']/option[normalize-space()='" + country + "']");
        countrySelect.click();
        cityField.clear();
        cityField.setValue(city);
        postCodeField.clear();
        postCodeField.setValue(postCodeNumber);
        phoneNumberField.setValue(phoneNumber);
        nextButton.scrollIntoView(true);
        nextButton.click();
    }

    public void shippingShipment(){
        dhlExpressCheckBox.click();
        nextButton.click();
    }

    public void payment(){
        bankTransferPayment.click();
        nextButton.click();
    }

    public String getBillingAddressSummary(){
        return billingAddressInfo.getText();
    }

    public String getShippingAddressSummary(){
        return shippingAddressInfo.getText();
    }

    public String getPaymentsSummary(){
        return paymentsInfo.getText();
    }

    public String getShippingSummary(){
        return shippingInfo.getText();
    }


}
