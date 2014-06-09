package com.companyname.springapp.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.companyname.springapp.domain.Card;

import com.companyname.springapp.repository.CardDao;

public interface CardManager extends Serializable {

//	public void modifyPin(String nPin) throws IOException;
	public void modifyPin(Card card) throws IOException;
    
    public List<Card> getCards();
    
    public abstract void setCardDao(CardDao cardDao);

}
