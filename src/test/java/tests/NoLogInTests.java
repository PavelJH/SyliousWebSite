package tests;

import com.codeborne.selenide.Selenide;
import core.BaseTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;

public class NoLogInTests extends BaseTest {

    @Test
    public void takePrice(){
        Selenide.open("https://v2.demo.sylius.com/en_US/taxons/t-shirts/men?criteria%5Bsearch%5D%5Bvalue%5D=&limit=18&page=2");
        String productPrice = $x("//div[@class='col-12 col-lg-9']//div[@class='products-grid']//span[normalize-space()]").getText();
        System.out.println(productPrice);
    }
}
