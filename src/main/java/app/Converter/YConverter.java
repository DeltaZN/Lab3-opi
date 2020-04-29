package app.Converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;
import java.util.regex.Pattern;

@FacesConverter("yConverter")
public class YConverter implements Converter {
    private Pattern pattern1 = Pattern.compile("^\\.");
    private Pattern pattern2 = Pattern.compile("\\.$");
    private Pattern pattern3 = Pattern.compile("^-\\.");
    private Pattern pattern4 = Pattern.compile("-0$");
    private Pattern pattern5 = Pattern.compile("^0{2,}");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String value) {

        HtmlInputText htmlInputText = (HtmlInputText) component;

        value = value.replace(',', '.');

        if (pattern1.matcher(value).find() || pattern2.matcher(value).find() || pattern3.matcher(value).find() ||
                pattern4.matcher(value).find() || pattern5.matcher(value).find()) {
            FacesMessage facesMessage = new FacesMessage(htmlInputText.getLabel() + " некорректно");
            throw new ConverterException(facesMessage);
        }

        if (value.trim().equals("")) {
            FacesMessage facesMessage = new FacesMessage(htmlInputText.getLabel() + " не было введено");
            throw new ConverterException(facesMessage);
        }

        try {
            BigDecimal y = new BigDecimal(value.trim());
            return y;
        } catch (Exception e) {


            FacesMessage msg =
                    new FacesMessage("BigDecimal parse error.",
                            htmlInputText.getLabel() + " не является числом");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object value) {

        return value.toString();

    }
}
