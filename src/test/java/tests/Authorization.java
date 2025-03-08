package tests;

import com.codeborne.selenide.Selenide;
import core.BaseTest;
import org.junit.jupiter.api.Test;

import static readConfig.ConfigProvider.URL;

public class Authorization extends BaseTest {

    @Test
    public void firstAuth(){
        Selenide.open(URL);
    }
}
