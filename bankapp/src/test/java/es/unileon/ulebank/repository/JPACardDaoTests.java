package es.unileon.ulebank.repository;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.unileon.ulebank.payments.Card;

public class JPACardDaoTests {

    private ApplicationContext context;
    private CardDao cardDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        cardDao = (CardDao) context.getBean("cardDao");
    }

    @Test
    public void testGetCardsList() {
        List<Card> cards = cardDao.getCardList();
        assertEquals(cards.size(), 1, 0);	   
    }

    @Test
    public void testSaveCard() throws IOException {
        List<Card> cards = cardDao.getCardList();

        Card card = cards.get(0);
        String pin = card.getPin();
        card.setPin("7779");
        cardDao.saveCard(card);

        List<Card> updatedProducts = cardDao.getCardList();
        Card card2 = updatedProducts.get(0);
        assertTrue(card2.getPin().equals("7779"));

//      	card2.setPin(pin);
//        cardDao.saveCard(card2);
    }
}
