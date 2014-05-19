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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.command.ModifyPinCommand;
import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.payments.Card;

@Controller
public class CardController {

    protected final Log logger = LogFactory.getLog(getClass());
    
    @Autowired
    private Card card;

    @RequestMapping(value="/showCard.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("cardNumber", this.card.getCardNumber());
        //this.card.setPin(this.card.generatePinCode()); //genero el codigo pin y se lo asigno a la tarjeta porque no lee al principio del archivo properties
        myModel.put("pin", this.card.getPin());
        myModel.put("expirationDate", this.card.getExpirationDate());
        myModel.put("buyLimitDiary",this.card.getBuyLimitDiary());

        
        return new ModelAndView("showCard", "model", myModel);
    }
    
    @RequestMapping(value="/modifyPIN.htm", method = RequestMethod.POST)
    public ModelAndView handleRequest(@ModelAttribute("card") CardBean bean) {
    	System.out.println("********" + bean.getPin());
    	ModifyPinCommand command = new ModifyPinCommand(card, bean.getPin());
    	try {
			command.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return new ModelAndView("showCard", "model", card);
    }
    
    @RequestMapping(value="/modifyPIN.htm", method = RequestMethod.GET)
    public ModelAndView init() {
    	
    	return new ModelAndView("modifyPIN", "card", card);
    }


    public void setCard(Card card) {
        this.card = card;
    }
}
