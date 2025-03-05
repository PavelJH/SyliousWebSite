package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemPage {

    final SelenideElement nameOfProductText = $x("//h1[normalize-space()]");
    final SelenideElement priceText = $x("(//div[@class='fs-3'])[1]");
    final SelenideElement listTShirtSize = $x("//select[@id='sylius_shop_add_to_cart_cartItem_variant_t_shirt_size']");
    final SelenideElement quantityField = $x("//input[@id='sylius_shop_add_to_cart_cartItem_quantity']");
    final SelenideElement addToCartButton = $x("//button[@id='add-to-cart-button']");
    final SelenideElement successAddToCartText = $x("//div[@class='fw-bold']");

    public void selectSize(String size){
        listTShirtSize.click();
        $x("//select[@id='sylius_shop_add_to_cart_cartItem_variant_t_shirt_size']/option[@value='t_shirt_size_" + size.toLowerCase() + "']").click();
    }

    public void putQuantity(String quantity){
        // Очищаем поле и вводим новое значение
        quantityField.clear();          // Очищаем текущее значение
        sleep(500);
        quantityField.sendKeys(String.valueOf(quantity));  // Вводим новое значение (например, 3)
    }

    public void clickAddToCart(){
        addToCartButton.click();
    }

    public void buyItems(String size, String quantity){
        listTShirtSize.click();
        $x("//select[@id='sylius_shop_add_to_cart_cartItem_variant_t_shirt_size']/option[@value='t_shirt_size_" + size.toLowerCase() + "']").click();

        quantityField.clear();          // Очищаем текущее значение
        sleep(500);
        quantityField.sendKeys(String.valueOf(quantity));

        addToCartButton.click();
        successAddToCartText.click();
        assertTrue(successAddToCartText.isDisplayed());
    }

    public String getNameItem(){
        sleep(300);
        return nameOfProductText.getText();

    }

    public String getPrise() {
        sleep(300);
        return priceText.getText();
    }



}
