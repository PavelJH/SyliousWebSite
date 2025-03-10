package helpers;

import com.codeborne.selenide.SelenideElement;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

public class Helpers {
    /**
     * Метод parseDoubleValue пытается преобразовать строку в число типа double.
     * Если строка содержит разделитель тысяч (например, запятую), используется NumberFormat.
     */
    public double parseDoubleValue(String value) {
        try {
            // Если строка содержит запятую, используем NumberFormat для парсинга
            if (value.contains(",")) {
                NumberFormat format = NumberFormat.getInstance(Locale.US);
                return format.parse(value).doubleValue();
            } else {
                return Double.parseDouble(value);
            }
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse number: " + value, e);
        }

    }

}
