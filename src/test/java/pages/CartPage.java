package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.Html;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartPage {

    private final SelenideElement deleteFirstItem = $x("//button[@class='btn btn-sm btn-transparent px-2']//*[name()='svg']");
    private final SelenideElement yourCartIsEmpty = $x("//div[@class='alert alert-info']");


    public void deleteFirstItemInCart(){
        deleteFirstItem.click();
        String alertText = yourCartIsEmpty.getText();
        assertTrue(alertText.contains("Your cart is empty"), "The cart is not empty.");
    }

}
