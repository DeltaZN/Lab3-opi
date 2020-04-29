package app.Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.math.BigDecimal;


@FacesValidator(value = "textValidator")
public class TextValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        HtmlInputText htmlInputText = (HtmlInputText) uiComponent;

        BigDecimal y = (BigDecimal) o;

        if (!(y.compareTo(new BigDecimal("-5")) > 0 & y.compareTo(new BigDecimal("5")) < 0)) {
            FacesMessage facesMessage = new FacesMessage(htmlInputText.getLabel() + " не удовлетворяет заданному интервалу от -5 до 5");
            throw new ValidatorException(facesMessage);
        }


    }

}
