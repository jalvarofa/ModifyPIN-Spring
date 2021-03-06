package es.unileon.ulebank.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.payments.CreditCard;
import es.unileon.ulebank.repository.CardDao;
import es.unileon.ulebank.repository.InMemoryCardDao;

public class CardManagerTests {

    private CardManager cardManager;
    
    private List<Card> cards;

    
    @Before
    public void setUp() throws Exception {
        cardManager = new SimpleCardManager();
        cards = new ArrayList<Card>();
        
        // stub up a list of products
        Card card = new CreditCard();
//        card.setDescription("Chair");
//        card.setPrice(CHAIR_PRICE);
        cards.add(card);
        
        card = new CreditCard();
//        card.setDescription("Table");
//        card.setPrice(TABLE_PRICE);
        cards.add(card);
        
        CardDao cardDao = new InMemoryCardDao(cards);
        cardManager.setCardDao(cardDao);
        //productManager.setProducts(products);
    }

    @Test
    public void testGetCardsWithNoCards() {
        cardManager = new SimpleCardManager();
        cardManager.setCardDao(new InMemoryCardDao(null));
        assertNull(cardManager.getCards());
    }

    @Test
    public void testGetCards() {
        List<Card> cards = cardManager.getCards();
        assertNotNull(cards);        
//        assertEquals(PRODUCT_COUNT, cardManager.getCards().size());
    
        Card card = cards.get(0);
//        assertEquals(CHAIR_DESCRIPTION, card.getDescription());
//        assertEquals(CHAIR_PRICE, card.getPrice());
        
        card = cards.get(1);
//        assertEquals(TABLE_DESCRIPTION, card.getDescription());
//        assertEquals(TABLE_PRICE, card.getPrice());      
    }   
    
    @Test
    public void testIncreasePriceWithNullListOfProducts() throws IOException {
        try {
            cardManager = new SimpleCardManager();
            cardManager.setCardDao(new InMemoryCardDao(null));
            cardManager.modifyPin(cards.get(0));
        }
        catch(NullPointerException ex) {
        	fail("Card list is null.");
        }
    }
    
    @Test
    public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            cardManager = new SimpleCardManager();
            cardManager.setCardDao(new InMemoryCardDao(new ArrayList<Card>()));
            //productManager.setProducts(new ArrayList<Product>());
            cardManager.modifyPin(cards.get(0));
        }
        catch(Exception ex) {
        	fail("Card list is empty.");
        }           
    }
}
