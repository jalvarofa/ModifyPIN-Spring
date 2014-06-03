package es.unileon.ulebank.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.payments.Card;

@Repository(value = "cardDao")
public class JPACardDao implements CardDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional (readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Card> getCardList() {
    	List<Card> result = new ArrayList<Card>();
    	result.addAll(em.createQuery("select c from CreditCard c order by c.id").getResultList());
        //em.close();
    	return result;
    }

    @Transactional (readOnly = false)
    public void saveCard(Card card) {
//    	System.out.println("-------> MERGE " + card.getPin());
        Card carddd = em.merge(card);
//        System.out.println("-------> MERGE2 " + carddd.getPin());
//        System.out.println("-------> MERGE3 " + card.getPin());
    }

	@Override
	@Transactional
	public Card getCard(String cardId) {
		return (Card) em.createQuery("select c from CreditCard c where c.id="+cardId).getSingleResult();
	}

}
