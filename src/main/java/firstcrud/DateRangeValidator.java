package firstcrud;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

@FacesValidator("dateRangeValidator")
public class DateRangeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return; // Let required="true" handle.
        }
        Date birthDate = (Date) value;
        if (birthDate == null) {
            return; // Let required="true" handle.
        }

        Date maxDateValue = new Date();
        if (birthDate.after(maxDateValue)) {
            FacesMessage msg = new FacesMessage("Invalid birth date.", null);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}