package es.unileon.ulebank.web;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
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
import es.unileon.ulebank.repository.InMemoryCardDao;
import es.unileon.ulebank.service.SimpleCardManager;
import es.unileon.ulebank.validator.PinValidator;
import es.unileon.ulebank.web.CardController;

public class CardControllerTests {
	
	CreditCard testCard;
	CardHandler handler;
	private Bank bank;
    private String accountNumber = "00000000001111111111";
	
	@Before
	public void setUp() throws Exception {
        this.bank = new Bank("1234");
		Client client = new Client("71451559N", 27);
		Account account = new Account(accountNumber);
		FeeStrategy commissionEmission = new LinearFee(0, 25);
		FeeStrategy commissionMaintenance = new LinearFee(0, 0);
		FeeStrategy commissionRenovate = new LinearFee(0, 0);
		testCard = new CreditCard("1234 0112 3456 7890", client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission.getFee(0), commissionMaintenance.getFee(0), commissionRenovate.getFee(0));
	}

    @Test
    public void testShowCardViewOk() throws Exception{		
        CardController controller = new CardController();
        controller.setCard(testCard);
        SimpleCardManager scm = new SimpleCardManager();
        scm.setCardDao(new InMemoryCardDao(new ArrayList<Card>()));
        controller.setCardManager(scm);
        //controller.setProductManager(new SimpleProductManager());
        ModelAndView modelAndView = controller.showCardView(null, null);
        assertEquals("showCard", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        
        Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("model");
        String cardValue = modelMap.get("cardNumber").toString();
        assertNotNull(cardValue);
    }
    
    @Test (expected = NullPointerException.class)
    public void testShowCardViewFailNull() throws Exception{
    	testCard = null;
        CardController controller = new CardController();
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.showCardView(null, null);
    }

	@Test
	public void testShowModifyPinGetOk() {
		CardController controller = new CardController();
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.showModifyPinGet();
        assertEquals("modifyPIN", modelAndView.getViewName());
        
        assertNotNull(modelAndView.getModel());
        
        CardBean bean = (CardBean) modelAndView.getModel().get("card");
        String pinValue = bean.getPin();
        assertNotNull(pinValue);
	}
	
	@Test (expected = NullPointerException.class)
	public void testShowModifyPinGetFailNull() {
		CardController controller = null;
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.showModifyPinGet();
	}

	//No funciona por el null que se le pasa por parametro al modelAndView ya que no podemos crear un bindinResult
	@Test
	public void testShowModifyPinPostOk() {
		CardController controller = new CardController();
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
		CardController controller = new CardController();
        controller.setCard(testCard);
        
        assertEquals("1234 0112 3456 7890", controller.getCard().getCardId());
	}
	
	@Test
	public void testSetCard() throws IncorrectLimitException {
		testCard.setBuyLimitDiary(500.00);
		CardController controller = new CardController();
        controller.setCard(testCard);
        assertEquals(500.00, controller.getCard().getBuyLimitDiary(),0.0001);
        
	}

}
