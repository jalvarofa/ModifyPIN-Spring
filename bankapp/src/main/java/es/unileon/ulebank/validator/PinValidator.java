package es.unileon.ulebank.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import es.unileon.ulebank.domain.CardBean;

public class PinValidator {

	public boolean supports(Class<?> someClass) {
		return CardBean.class.isAssignableFrom(someClass);
	}

	/**
	 * Valida los campos del formulario enviando un mensaje de error en caso de que este alguno vacio
	 */
	public void validate(Object object, Errors errors) {
		CardBean bean = (CardBean)object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPin", "required.pin", "Pin field is necesary");
		validatePin(bean.getNewPin(), errors);

	}

	private void validatePin(String newPin, Errors errors) {
		if (!errors.hasFieldErrors("newPin")) {
			if (newPin.length() != 4) {
				errors.rejectValue("newPin", "size.pin");
			}
			if (!newPin.trim().matches("^[0-9]*$")) {
				errors.rejectValue("newPin", "format.pin");
			}
		}
	}

}
