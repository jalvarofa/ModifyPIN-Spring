package com.companyname.springapp.web;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.companyname.springapp.account.Account;
import com.companyname.springapp.bank.Bank;
import com.companyname.springapp.client.Client;
import com.companyname.springapp.domain.Card;
import com.companyname.springapp.domain.CreditCard;
import com.companyname.springapp.fees.FeeStrategy;
import com.companyname.springapp.fees.LinearFee;
import com.companyname.springapp.repository.CardDao;
import com.companyname.springapp.repository.InMemoryCardDao;
import com.companyname.springapp.repository.JPACardDao;
import com.companyname.springapp.service.CardManager;
import com.companyname.springapp.service.ModifyPin;
import com.companyname.springapp.service.SimpleCardManager;
import com.companyname.springapp.validator.Mock;
import com.companyname.springapp.validator.PinValidator;

public class ModifyPinFormControllerTests {
	
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
		cardManager = new SimpleCardManager();
		cardManager.setCardDao(new InMemoryCardDao(cards));
	}

	//No funciona por el null que se le pasa por parametro al modelAndView ya que no podemos crear un bindinResult
	@Test
	public void testOnSubmitPinPostOk() throws IOException {
		Mock bindRes = new Mock();
		ModifyPinFormController controller = new ModifyPinFormController();
        controller.setCard(testCard);
        controller.setCardManager(cardManager);
        controller.setPinValidator(new PinValidator());
        ModifyPin modifyPin = new ModifyPin();
        modifyPin.setNewPin(testCard.getPin());
        ModelAndView modelAndView = controller.onSubmit(modifyPin, bindRes);
        
        assertEquals("hello", modelAndView.getViewName());
        
        assertNotNull(modelAndView.getModel());
        
        Card modelMap = (Card) modelAndView.getModel().get("card");
        String cardValue = modelMap.getCardId();
        assertNotNull(cardValue);
	}
	
	@Test (expected = NullPointerException.class)
	public void testOnSubmitPinPostFail() throws IOException {
		ModifyPinFormController controller = null;
        controller.setCard(testCard);
        ModelAndView modelAndView = controller.onSubmit(null, null);
	}

	@Test
	public void testFormBackingObject() throws ServletException {
		ModifyPinFormController controller = new ModifyPinFormController();
        controller.setCard(testCard);
        controller.setCardManager(cardManager);
        ModelAndView modelAndView = controller.formBackingObject(null);
        assertEquals("priceincrease", modelAndView.getViewName());
        
        assertNotNull(modelAndView.getModel());
        
        ModifyPin modifyPin = (ModifyPin) modelAndView.getModel().get("card");
        String pinValue = modifyPin.getNewPin();
        assertNotNull(pinValue);
	}

//	@Test
//	public void testSetCardManager() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCardManager() {
//		fail("Not yet implemented");
//	}

}
