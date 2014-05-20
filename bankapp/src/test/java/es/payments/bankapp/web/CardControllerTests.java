package es.payments.bankapp.web;

import static org.junit.Assert.*;

import java.util.Map;

import javax.validation.constraints.Null;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.bank.Bank;
import es.unileon.ulebank.bank.BankHandler;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.domain.CardBean;
import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.handler.CardHandler;
import es.unileon.ulebank.handler.DNIHandler;
import es.unileon.ulebank.handler.GenericHandler;
import es.unileon.ulebank.office.Office;
import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.transactionManager.TransactionManager;
import es.unileon.ulebank.validator.PinValidator;

public class CardControllerTests {
	
	CreditCard testCard;
	CardHandler handler;
	private Office office;
	private Bank bank;
	private TransactionManager manager;
    private String accountNumber = "0000000000";
	
	@Before
	public void setUp() throws Exception {
		this.manager = new TransactionManager();
        this.bank = new Bank(manager, new GenericHandler("1234"));
        this.office = new Office(new GenericHandler("1234"), this.bank);
		handler = new CardHandler(new BankHandler("1234"), "01", "123456789");
		Client client = new Client(new DNIHandler("71451559N"), 27);
		Account account = new Account(office, bank, accountNumber);
		FeeStrategy commissionEmission = new LinearFee(0, 25);
		FeeStrategy commissionMaintenance = new LinearFee(0, 0);
		FeeStrategy commissionRenovate = new LinearFee(0, 0);
		testCard = new CreditCard(handler, client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission.getFee(0), commissionMaintenance.getFee(0), commissionRenovate.getFee(0));
	}

    @Test
    public void testShowCardViewOk() throws Exception{		
        CardController controller = new CardController(new PinValidator());
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.showCardView(null, null);
        assertEquals("showCard", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        
        Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("model");
        String cardValue = modelMap.get("cardNumber").toString();
        assertNotNull(cardValue);
    }
    
    @Test (expected = NullPointerException.class)
    public void testShowCardViewFail() throws Exception{
    	testCard = null;
        CardController controller = new CardController(new PinValidator());
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.showCardView(null, null);
    }

	@Test
	public void testShowModifyPinGetOk() {
		CardController controller = new CardController(new PinValidator());
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.showModifyPinGet();
        assertEquals("modifyPIN", modelAndView.getViewName());
        
        assertNotNull(modelAndView.getModel());
        
        Card modelMap = (Card) modelAndView.getModel().get("card");
        String cardValue = modelMap.getCardId();
        assertNotNull(cardValue);
	}
	
	@Test (expected = NullPointerException.class)
	public void testShowModifyPinGetFail() {
		CardController controller = null;
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.showModifyPinGet();
	}

	@Test
	public void testShowModifyPinPostOk() {
		CardController controller = new CardController(new PinValidator());
        controller.setCard(testCard);
        CardBean cardBean = new CardBean();
        cardBean.setPin(testCard.getPin());
        ModelAndView modelAndView = controller.showModifyPinPost(cardBean, null);
        
        assertEquals("showCard", modelAndView.getViewName());
        
        assertNotNull(modelAndView.getModel());
        
        Card modelMap = (Card) modelAndView.getModel().get("model");
        String cardValue = modelMap.getCardId();
        assertNotNull(cardValue);
	}
	
	@Test (expected = NullPointerException.class)
	public void testShowModifyPinPostFail() {
		CardController controller = null;
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.showModifyPinGet();
	}

	@Test
	public void testGetCard() {
		CardController controller = new CardController(new PinValidator());
        controller.setCard(testCard);
        
        assertEquals("1234 0112 3456 789"+handler.getControlDigit(), controller.getCard().getCardId());
	}
	
	@Test
	public void testSetCard() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(500.00);
		CardController controller = new CardController(new PinValidator());
        controller.setCard(testCard);
        assertEquals(500.00, controller.getCard().getBuyLimitDiary(),0.0001);
        
	}

}
