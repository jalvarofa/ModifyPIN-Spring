package es.unileon.ulebank.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import es.unileon.ulebank.payments.Card;
import es.unileon.ulebank.repository.CardDao;

public interface CardManager extends Serializable{

	/**
	 * 
	 * @param cardDao
	 */
	public abstract void setCardDao(CardDao cardDao);

	/**
	 * 
	 * @return
	 */
	public abstract List<Card> getCards();

	/**
	 * 
	 * @throws IOException
	 */
	public abstract void modifyPin(Card c) throws IOException;

}