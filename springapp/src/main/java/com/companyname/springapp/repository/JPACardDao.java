package com.companyname.springapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.springapp.domain.Card;

@Repository(value = "productDao")
public class JPACardDao implements CardDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Card> getCardList() {
        Query query = em.createQuery("select c from CreditCard c order by c.id");
        em.close();
        return query.getResultList();
    }

    @Transactional(readOnly = false)
    public void saveCard(Card prod) {
        em.merge(prod);
        em.close();
    }

}