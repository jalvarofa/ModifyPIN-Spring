package es.unileon.ulebank.repository;

import java.util.List;

import es.unileon.ulebank.payments.Card;

public class InMemoryCardDao implements CardDao {

    private List<Card> cardList;

    public InMemoryCardDao(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void saveCard(Card card) {
    }

	@Override
	public Card getCard(String cardId) {
		// TODO Auto-generated method stub
		return null;
	}

}
