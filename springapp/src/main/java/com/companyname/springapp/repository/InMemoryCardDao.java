package com.companyname.springapp.repository;

import java.util.List;

import com.companyname.springapp.domain.Card;

public class InMemoryCardDao implements CardDao {

    private List<Card> cardList;

    public InMemoryCardDao(List<Card> productList) {
        this.cardList = productList;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void saveCard(Card card) {
    }

}
