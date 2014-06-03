package es.unileon.ulebank.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.repository.CardDao;

@Component
public class SimpleCardManager implements CardManager {
    @Autowired
    private CardDao cardDao;
 
    /**
     * 
     */
    @Override
	public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    /**
     * 
     */
    @Override
	public List<Card> getCards() {
        return cardDao.getCardList();
    }

    /**
     * 
     */
    @Override
	public void modifyPin(Card c) throws IOException {
//    	System.out.println("ENTRA EN MODIFYPIN");
//        c.setPin("8888");
//        System.out.println("PIN NUEVO---->"+c.getPin());
        cardDao.saveCard(c);
//    	if (c != null) {
//    		String newPin = c.getPin();
//    		System.out.println("PIN NUEVO---->"+newPin);
//    		cardDao.saveCard(c);
//    	}
    }
    
    public Card getCard(String cardId){

		return cardDao.getCard(cardId);
    	
    }
}
