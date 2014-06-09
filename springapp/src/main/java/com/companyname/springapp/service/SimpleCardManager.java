package com.companyname.springapp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.companyname.springapp.domain.Card;
import com.companyname.springapp.repository.CardDao;

@Component
public class SimpleCardManager implements CardManager {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private CardDao cardDao;

    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }
    
    private List<Card> cards;

    public List<Card> getCards() {
        return cardDao.getCardList();       
    }

    public void modifyPin(Card card) throws IOException {
//    	List<Card> cards = cardDao.getCardList();
//        if (cards != null) {
//            for (Card card : cards) {
//                String newPin = nPin;
//                card.setPin(newPin);
//                cardDao.saveCard(card);
//            }
//        }
    	this.cardDao.saveCard(card);
	}
	
    public void setCards(List<Card> cards) {
        this.cards = cards;       
    }
}
