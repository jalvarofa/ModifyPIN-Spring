package com.companyname.springapp.repository;

import java.util.List;

import com.companyname.springapp.domain.Card;

public interface CardDao {

    public List<Card> getCardList();

    public void saveCard(Card prod);

}