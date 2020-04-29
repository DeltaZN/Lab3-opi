package app.Converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("loginConverter")
public class LoginConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String value) {


        if (value.trim().equals("")) {
            FacesMessage facesMessage = new FacesMessage("Введите имя");
            throw new ConverterException(facesMessage);
        }

        return value;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object value) {

        return value.toString();

    }
}
