package es.unileon.ulebank.repository;

import java.util.List;

import es.unileon.ulebank.payments.Card;

public interface CardDao {

    public List<Card> getCardList();

    public void saveCard(Card card);
    
    public Card getCard(String cardId);

}
