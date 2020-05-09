package app.Converter;

import org.junit.Assert;
import org.junit.Test;

import javax.faces.component.html.HtmlInputText;
import java.math.BigDecimal;

public class YConverterTest {

    YConverter yConverter = new YConverter();
    HtmlInputText input;

    @Test
    public void testGetAsObject() {
        BigDecimal ans = (BigDecimal) yConverter.getAsObject(null, input, "123");
        BigDecimal expected = new BigDecimal(123);
        Assert.assertEquals(ans, expected);
    }
}
