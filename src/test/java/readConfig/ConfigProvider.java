package readConfig;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import jdk.jfr.Description;


public interface ConfigProvider {

    static Config readConfigSelenium(){
        return ConfigFactory.load("Sylius");
    }
    String URL = readConfigSelenium().getString("urlTickets");

    @Description("ItemsParams")
    String T_SHIRTS_JEANS_MEN = readConfigSelenium().getString("ItemsParams.TShirtsMen");
    String T_SHIRTS_JEANS_WOMEN = readConfigSelenium().getString("ItemsParams.TShirtsWomen");
    String CAPS_SIMPLE = readConfigSelenium().getString("ItemsParams.CapsSimple");
    String CAPS_WITH_POMPONS = readConfigSelenium().getString("ItemsParams.CapsWithPompons");
    String DRESSES = readConfigSelenium().getString("ItemsParams.Dresses");
    String JEANS_MEN = readConfigSelenium().getString("ItemsParams.JeansMen");
    String JEANS_WOMEN = readConfigSelenium().getString("ItemsParams.JeansWomen");

    @Description("quantity List to 10")
    String QUANTITY_01 = readConfigSelenium().getString("quantity.1");
    String QUANTITY_02 = readConfigSelenium().getString("quantity.2");
    String QUANTITY_03 = readConfigSelenium().getString("quantity.3");
    String QUANTITY_04 = readConfigSelenium().getString("quantity.4");
    String QUANTITY_05 = readConfigSelenium().getString("quantity.5");
    String QUANTITY_06 = readConfigSelenium().getString("quantity.6");
    String QUANTITY_07 = readConfigSelenium().getString("quantity.7");
    String QUANTITY_08 = readConfigSelenium().getString("quantity.8");
    String QUANTITY_09 = readConfigSelenium().getString("quantity.9");
    String QUANTITY_10 = readConfigSelenium().getString("quantity.10");

    @Description("Size List")
    String SIZE_S = readConfigSelenium().getString("size.S");
    String SIZE_M = readConfigSelenium().getString("size.M");
    String SIZE_L = readConfigSelenium().getString("size.L");
    String SIZE_XL = readConfigSelenium().getString("size.XL");
    String SIZE_XXL = readConfigSelenium().getString("size.XXL");

    @Description("Country List")
    String COUNTRY_AU = readConfigSelenium().getString("country.AU");
    String COUNTRY_CA = readConfigSelenium().getString("country.CA");
    String COUNTRY_CN = readConfigSelenium().getString("country.CN");
    String COUNTRY_FR = readConfigSelenium().getString("country.FR");
    String COUNTRY_DE = readConfigSelenium().getString("country.DE");
    String COUNTRY_MX = readConfigSelenium().getString("country.MX");
    String COUNTRY_NZ = readConfigSelenium().getString("country.NZ");
    String COUNTRY_PL = readConfigSelenium().getString("country.PL");
    String COUNTRY_PT = readConfigSelenium().getString("country.PT");
    String COUNTRY_ES = readConfigSelenium().getString("country.ES");
    String COUNTRY_GB = readConfigSelenium().getString("country.GB");
    String COUNTRY_US = readConfigSelenium().getString("country.US");



}
