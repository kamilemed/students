package firstcrud;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@FacesValidator("calendarValidator")
public class CalendarValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date birthDate = (Date) value;
        Date today = new Date();

        if (birthDate.after(today)) {
            String messageBundleName = context.getApplication().getMessageBundle();
            Locale locale = context.getViewRoot().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);

            String message = bundle.getString("error.set.date");
            FacesMessage msg = new FacesMessage(message, null);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}