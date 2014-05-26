package es.payments.bankapp.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.command.ModifyPinCommand;
import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.validator.PinValidator;

@Controller
public class CardController {

	/**
	 * Logger de la clase
	 */
    protected final Log logger = LogFactory.getLog(getClass());
    
    /**
     * Validador para el formilario del cambio de PIN
     */
    private PinValidator pinValidator;
    
    @Autowired
    private Card card;
    
	/**
	 * Crea un nuevo controlador recibiendo por parametros el validador de la clase y las propiedades
	 * de la tarjeta
	 * @param cardValidator
	 * @param properties
	 */
	@Autowired
	public CardController(PinValidator pinValidator) {
		this.pinValidator = pinValidator;
	}

    /**
     * Metodo que genera y devuelve la vista de showCard donde mostraremos los datos de la 
     * tarjeta del cliente
     * @param request
     * @param response
     * @return la vista showCard en la que se mustran los datos de la tarjeta
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value="/showCard.htm")
    public ModelAndView showCardView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("cardNumber", this.card.getCardNumber());
        //this.card.setPin(this.card.generatePinCode()); //genero el codigo pin y se lo asigno a la tarjeta porque no lee al principio del archivo properties
        myModel.put("pin", this.card.getPin());
        myModel.put("expirationDate", this.card.getExpirationDate());
        myModel.put("buyLimitDiary",this.card.getBuyLimitDiary());

        return new ModelAndView("showCard", "model", myModel);
    }
    
    /**
     * Metodo que llama al comando para cambiar el PIN de la tarjeta y despues devuelve
     * la vista showCard pasandole los datos de la tarjeta con el PIN modificado
     * @param bean
     * @return la vista showCard con los nuevos datos de la tarjeta en caso de que se
     * hayan realizado modificacion
     */
    @RequestMapping(value="/modifyPIN.htm", method = RequestMethod.POST)
    public ModelAndView showModifyPinPost(@ModelAttribute("card") CardBean bean, BindingResult result) {
    	bean.setPin(card.getPin());
    	this.pinValidator.validate(bean, result);
    	
    	if (result.hasErrors()) {
			return new ModelAndView("modifyPIN", "card", bean);
		}
    	
    	ModifyPinCommand command = new ModifyPinCommand(card, bean.getNewPin());
    	try {
			command.execute();
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
    	
    	return new ModelAndView("showCard", "model", card);
    }
    
    /**
     * Metodo que devuelve la vista de modifyPIN pasandole la tarjeta para poder tener
     * acceso a esta y modificar el pin
     * @return la vista modifyPin con el PIN viejo y la opcion de cambiarlo
     */
    @RequestMapping(value="/modifyPIN.htm", method = RequestMethod.GET)
    public ModelAndView showModifyPinGet() {
    	
    	CardBean bean = new CardBean();
    	bean.setPin(card.getPin());
		return new ModelAndView("modifyPIN", "card", bean );
    }

    /**
     * Metodo que asigna a nuestra tarjeta una tarjeta pasada por parametro
     * @param card
     */
    public void setCard(Card card) {
        this.card = card;
    }
    
    public Card getCard(){
    	return this.card;
    }
}
