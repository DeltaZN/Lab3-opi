package app.Converter;

import junit.framework.TestCase;
import org.junit.Test;

import javax.faces.component.html.HtmlInputText;
import java.math.BigDecimal;

public class YConverterTest extends TestCase {

    YConverter yConverter = new YConverter();
    HtmlInputText input;

    @Test
    public void testGetAsObject() {
        BigDecimal ans = (BigDecimal) yConverter.getAsObject(null, input, "123");
        BigDecimal expected = new BigDecimal(123);
        assertEquals(ans, expected);
    }
}
