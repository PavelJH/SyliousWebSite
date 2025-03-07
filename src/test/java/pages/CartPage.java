package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.Helpers;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartPage {

    private final SelenideElement deleteLastItem = $x("//table[@class='table align-middle']/tbody/tr[last()]//button[@class='btn btn-sm btn-transparent px-2']//*[name()='svg']");
    private final SelenideElement yourCartIsEmpty = $x("//div[@class='alert alert-info']");

    private final SelenideElement qtyInCart = $x("//table[@class='table align-middle']/tbody/tr[last()]//input[@id='sylius_shop_cart_items_0_quantity']");
    private final SelenideElement unitPriceInCart = $x("//table[@class='table align-middle']/tbody/tr[last()]//td[@class='text-black-50 text-end']/span[starts-with(normalize-space(), '$')][1]");
    private final SelenideElement totalPriceInCartLastItem = $x("(//div[@class='table-responsive']//span[starts-with(normalize-space(), '$')])[2]");
    private final SelenideElement itemsTotalPriceInCartFull = $x("(//div[@class='ms-auto text-end'][starts-with(normalize-space(), '$')])[1]");
    private final SelenideElement orderTotalPriceInCartFull = $x("//div[@class='ms-auto h5 text-end']");


    private final SelenideElement checkOutButton = $x("//button[@type='submit']");



    public void deleteFirstItemInCart() {
        if (!deleteLastItem.exists()) {
            return; // Если элемент не найден, выходим из метода
        }
        deleteLastItem.click();
        String alertText = yourCartIsEmpty.getText();
        assertTrue(alertText.contains("Your cart is empty"), "The cart is not empty.");
    }

    public String getQtyNumber() {
        // Для input лучше использовать getValue()
        return qtyInCart.getValue();
    }

    public String getUnitPrice() {
        return unitPriceInCart.getText();
    }

    public String getTotalPriceFull(){
        return itemsTotalPriceInCartFull.getText();
    }

    public void clickCheckOutButton(){
        checkOutButton.click();
    }

    public String getOrderTotalPriceInCartFull(){
        return orderTotalPriceInCartFull.getText();
    }


    public void checkAmountPrice() {
        // Получаем количество и преобразуем его в double
        String quantityValue = qtyInCart.getValue();
        double quantity = Double.parseDouble(quantityValue.trim());

        // Извлекаем цену за единицу: удаляем знак '$' и пробелы
        String unitPriceInCartNumber = unitPriceInCart.getText();
        String unitPriceStr = unitPriceInCartNumber.replace("$", "").trim();
        Helpers helpers = new Helpers();
        double unitPrice = helpers.parseDoubleValue(unitPriceStr);//парсит и убирает запятую

        // Извлекаем итоговую сумму: удаляем знак '$' и пробелы
        String totalPriceInCartNumber = totalPriceInCartLastItem.getText();
        String totalPriceStr = totalPriceInCartNumber.replace("$", "").trim();
        double totalPrice = helpers.parseDoubleValue(totalPriceStr);

        // Вычисляем ожидаемую сумму как произведение количества и цены за единицу
        double calculatedTotal = quantity * unitPrice;

        // Для JUnit 5: порядок параметров: expected, actual, message
        assertEquals(calculatedTotal, totalPrice, 0.001, "The amount in the basket does not match the calculated amount");
    }



}
