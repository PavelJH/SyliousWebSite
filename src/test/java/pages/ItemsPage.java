package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class ItemsPage {

    private final SelenideElement valueSearchField = $x("//input[@id='criteria_search_value']");
    private final SelenideElement searchButton = $x("//button[@aria-label='search button']//*[name()='svg']");
    private final SelenideElement clearFiltersButton = $x("//a[@aria-label='clear filters']");
    private final SelenideElement numbersOfProductButton = $x("(//button[@type='button'])[3]");
    private final SelenideElement previousPageButton = $x("//a[normalize-space()='Previous']");
    private final SelenideElement nextPageButton = $x("//a[normalize-space()='Next']");
    private final SelenideElement firstItemOnPage = $x("//img[@alt='Product Stellar Drift T-Shirt Image']");


    public void searchField(String nameItem){
        valueSearchField.setValue(nameItem);
        searchButton.click();
    }

    public void searchItemOnPage(String itemName){
        String productName = itemName;
        String xpath = "//div[@class='products-grid']//a[@class='link-reset']//div[normalize-space(text())='" + productName + "']";
        $x(xpath).click();
    }



    public void selectItemLimit(String limit) {
        numbersOfProductButton.click();
        String xpath = "//a[@class='dropdown-item' and contains(text(), '" + limit + "')]";
        $x(xpath).click();
    }
    // Метод для получения всех ссылок
    public List<SelenideElement> getLinks() {
        // Получаем коллекцию всех ссылок с классом 'link-reset' внутри 'products-grid'
        ElementsCollection hrefs = $$x("//div[@class='products-grid']//a[@class='link-reset']");
        return hrefs;
    }
    // Метод для клика по каждой ссылке и выполнения действий
    public List<String> processEachLink() {
        // Создаем список, чтобы хранить информацию о товарах
        List<String> itemPagePriceAndName = new ArrayList<>();

        // Получаем все ссылки
        List<SelenideElement> links = getLinks();

        // Проходим по каждой ссылке
        for (SelenideElement link : links) {
            link.scrollIntoView(true);
            sleep(100);
            // Дожидаемся, пока ссылка станет видимой
            link.shouldBe(visible).shouldBe(enabled).click();

            // Переходим на страницу товара
            ItemPage itemPage = new ItemPage();

            // Получаем название и цену товара
            String name = itemPage.nameOfProductText.getText();
            String price = itemPage.priceText.getText();

            System.out.println("its what I look " + name);
            // Форматируем строку и добавляем ее в список
            String itemInfo = "Name: " + name + ", Price: " + price;
            itemPagePriceAndName.add(itemInfo);

            // Возвращаемся назад
            back();
        }

        // Возвращаем собранный список
        return itemPagePriceAndName;
    }

    /**
     * Берет все товары со страницы и ищет у них название продукта и цену,
     * после чего записывает в список
     *
     * @return
     */
    public List<String> extractAllProductData() {
        // Ожидаем, пока товары загрузятся на странице
        SelenideElement productGrid = $x("//div[@class='products-grid']");
        productGrid.shouldBe(Condition.visible);  // Ожидаем видимость контейнера с товарами

        // Получаем все элементы товаров
        List<SelenideElement> products = productGrid.$$(byXpath(".//a[@class='link-reset']"));

        // Создаем список для хранения извлеченных данных
        List<String> extractedData = new ArrayList<>();

        // Проходим по каждому товару
        for (SelenideElement product : products) {
            // Извлекаем название товара
            String name = product.$x(".//div[@class='h6 text-break']").getText();

            // Извлекаем цену товара
            // Мы ищем элемент span с ценой в соседнем div
            SelenideElement priceElement = product.$x("./following-sibling::div//span[normalize-space(text()) and contains(text(), '$')]");

            // Проверяем, что цена существует и добавляем ее в список
            if (priceElement.exists()) {
                String price = priceElement.getText();
                extractedData.add("Name: " + name + ", Price: " + price);
            }
        }
        // Теперь можно использовать список extractedData по вашему усмотрению
        // Например, распечатать все данные
        for (String data : extractedData) {
//            System.out.println(data); //Выводит все записаные строки
        }
        return extractedData;
    }

public void firstItemOnPageClick(){
    firstItemOnPage.click();
}





}
