package com.companyname.springapp.command;

import java.io.IOException;

import com.companyname.springapp.exceptions.PaymentException;
import com.companyname.springapp.exceptions.TransactionException;
import com.companyname.springapp.exceptions.TransferException;
import com.companyname.springapp.fees.InvalidFeeException;

/**
 * @author Israel
 */
public interface Command {
	/**
	 * Realiza la ejecucion del comando
	 * @throws InvalidFeeException 
	 * @throws es.unileon.ulebank.history.TransactionException 
	 * @throws TransactionException 
	 * @throws PaymentException 
	 * @throws IOException 
	 */
	public void execute() throws Exception;
	/**
	 * Deshace los cambios realizados
	 * @throws TransferException 
	 * @throws es.unileon.ulebank.history.TransactionException 
	 */
	public void undo() throws TransferException;
	/**
	 * Rehace los cambios deshechos
	 * @throws es.unileon.ulebank.history.TransactionException 
	 * @throws TransactionException 
	 * @throws PaymentException 
	 */
	public void redo() throws PaymentException, TransactionException;
	
}
