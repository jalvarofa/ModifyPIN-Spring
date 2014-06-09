package com.companyname.springapp.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.companyname.springapp.account.Account;
import com.companyname.springapp.bank.Bank;
import com.companyname.springapp.client.Client;
import com.companyname.springapp.domain.Card;
import com.companyname.springapp.domain.CreditCard;
import com.companyname.springapp.exceptions.IncorrectLimitException;
import com.companyname.springapp.fees.FeeStrategy;
import com.companyname.springapp.fees.LinearFee;
import com.companyname.springapp.repository.InMemoryCardDao;
import com.companyname.springapp.repository.JPACardDao;
import com.companyname.springapp.service.ModifyPin;
import com.companyname.springapp.service.SimpleCardManager;


public class CardControllerTests {

	CreditCard testCard;
	private Bank bank;
    private String accountNumber = "00000000001111111111";
    private SimpleCardManager cardManager;
    private List<Card> cards;
	
	@Before
	public void setUp() throws Exception {
		cards = new ArrayList<Card>();
        this.bank = new Bank("1234");
		Client client = new Client("71451559N", 27);
		Account account = new Account(accountNumber);
		FeeStrategy commissionEmission = new LinearFee(0, 25);
		FeeStrategy commissionMaintenance = new LinearFee(0, 0);
		FeeStrategy commissionRenovate = new LinearFee(0, 0);
		testCard = new CreditCard("1234 0112 3456 7890", client, account, 400.0, 1000.0, 400.0, 1000.0, commissionEmission.getFee(0), commissionMaintenance.getFee(0), commissionRenovate.getFee(0));
		cards.add(testCard);
	}

    @Test
    public void testhandleRequestViewOk() throws Exception{		
        CardController controller = new CardController();
        controller.setCard(testCard);
        SimpleCardManager scm = new SimpleCardManager();
        scm.setCardDao(new InMemoryCardDao(cards));
        controller.setCardManager(scm);
        //controller.setProductManager(new SimpleProductManager());
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        
        Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("card");
        String cardValue = modelMap.get("cardNumber").toString();
        assertNotNull(cardValue);
    }
    
    @Test (expected = NullPointerException.class)
    public void testhandleRequestViewFailNull() throws Exception{
    	testCard = null;
        CardController controller = new CardController();
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.handleRequest(null, null);
    }

//	@Test
//	public void testformBackingPinGetOk() throws ServletException, IOException {
//		ModifyPinFormController controller = new ModifyPinFormController();
//        controller.setCard(testCard);
//        ModelAndView modelAndView = controller.formBackingObject(null);
//        assertEquals("modifyPIN", modelAndView.getViewName());
//        
//        assertNotNull(modelAndView.getModel());
//        
//        ModifyPin modifyPin = (ModifyPin) modelAndView.getModel().get("card");
//        String pinValue = modifyPin.getNewPin();
//        assertNotNull(pinValue);
//	}
//	
//	@Test (expected = NullPointerException.class)
//	public void testformBackingPinGetFailNull() throws ServletException, IOException {
//		ModifyPinFormController controller = null;
//        controller.setCard(testCard);
//        ModelAndView modelAndView = controller.formBackingObject(null);
//	}
//
//	//No funciona por el null que se le pasa por parametro al modelAndView ya que no podemos crear un bindinResult
//	@Test
//	public void testOnSubmitPinPostOk() throws IOException {
//		ModifyPinFormController controller = new ModifyPinFormController();
//        controller.setCard(testCard);
//        ModifyPin modifyPin = new ModifyPin();
//        modifyPin.setNewPin(testCard.getPin());
//        ModelAndView modelAndView = controller.onSubmit(modifyPin, null);
//        
//        assertEquals("hello", modelAndView.getViewName());
//        
//        assertNotNull(modelAndView.getModel());
//        
//        Card modelMap = (Card) modelAndView.getModel().get("model");
//        String cardValue = modelMap.getCardId();
//        assertNotNull(cardValue);
//	}
//	
//	@Test (expected = NullPointerException.class)
//	public void testOnSubmitPinPostFail() throws IOException {
//		ModifyPinFormController controller = null;
//        controller.setCard(testCard);
//        ModelAndView modelAndView = controller.onSubmit(null, null);
//	}

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
