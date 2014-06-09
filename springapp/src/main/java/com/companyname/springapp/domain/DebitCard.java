package com.companyname.springapp.domain;

import java.io.IOException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.companyname.springapp.account.Account;
import com.companyname.springapp.client.Client;
import com.companyname.springapp.exceptions.CommissionException;
import com.companyname.springapp.fees.InvalidFeeException;

/**
 * @author Israel, Rober dCR
 * Clase que representa una tarjeta de Debito
 */
@Entity
@Table(name="cards") 
public class DebitCard extends Card {
	
	/**
	 * Account associated to the Card
	 */
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Account.class)
	@JoinColumn(name = "account_id", referencedColumnName = "account_number")
	private Account account;
	/**
	 * Card owner
	 */
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Client.class)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private Client owner;
	
	/**
	 * Constructor de la clase
	 * @param cardId
	 * @param owner
	 * @param account
	 * @param buyLimitDiary
	 * @param buyLimitMonthly
	 * @param cashLimitDiary
	 * @param cashLimitMonthly
	 * @param commissionEmission
	 * @param commissionMaintenance
	 * @param commissionRenovate
	 * @throws NumberFormatException
	 * @throws CommissionException
	 * @throws IOException
	 * @throws InvalidFeeException 
	 */
	public DebitCard(String cardId, Client owner, Account account,
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			double commissionEmission, double commissionMaintenance, double commissionRenovate) throws NumberFormatException, CommissionException, IOException, InvalidFeeException {
		super(cardId, CardType.DEBIT.toString(), buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly,
				/*new LinearFee(0,commissionEmission),
				new LinearFee(0,commissionMaintenance),
				new LinearFee(0,commissionRenovate)*/
				commissionEmission, commissionMaintenance, commissionRenovate);
		this.account = account;
		this.owner = owner;
	}
	
	public DebitCard(){
		super(CardType.DEBIT.toString());
	}
	/**
	 * Method that makes the payment
	 * @param receiverAccount Account which receives the money from the card
	 * @param quantity Amount of the payment
	 * @param payConcept Concept of the payment
	 * @throws PaymentException
	 * @throws es.unileon.ulebank.history.TransactionException 
	 */
//	public void makeTransaction(Account receiverAccount, double quantity, String payConcept) throws PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException{
//
//		try{
//			//Discount the quantity from sender account
//			this.account.doWithdrawal(new CardTransaction(quantity, new Date(), payConcept, this.account, receiverAccount));
//			//Add the money to receiver account
//			receiverAccount.doDeposit(new CardTransaction(quantity, new Date(), payConcept, this.account, receiverAccount));
//		}catch(TransactionException e){
//			e.printStackTrace();
//			throw new PaymentException("Denegate Transaction");
//		}
//		
//	}

	/**
	 * Devuelve el duegno de la tarjeta
	 * @return Client
	 */
	public Client getOwner() {
		return owner;
	}
}
