/* Application developed for AW subject, belonging to passive operations
 group.*/
package com.companyname.springapp.bank;

//import es.unileon.ulebank.transactionManager.TransactionManager;





import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import com.companyname.springapp.exceptions.MalformedHandlerException;
import com.companyname.springapp.office.Office;

/**
 *
 * @author runix
 */

public class Bank {

    private static final Logger LOG = Logger.getLogger(Bank.class.getName());

    private final List<Office> offices;

  
    private final String bankID;

//    private final TransactionManager manager;

    /**
     *
     * @param manager
     * @param bankID
     * @throws MalformedHandlerException
     */
    public Bank(String bankID) throws MalformedHandlerException {
        this.bankID = bankID.toString();
        this.offices = new ArrayList<Office>();
    }

    /**
     *
     * @return
     */
    public String getID() {
        return this.bankID;
    }

    /**
     *
     * @param office
     * @return
     */
    public boolean addOffice(Office office) {
        if (office != null) {
            for (int i = 0; i < offices.size(); ++i) {
                if (offices.get(i).getIdOffice().compareTo(office.getIdOffice()) == 0) {
                    return false;
                }
            }
            return this.offices.add(office);
        }
        return false;
    }

    /**
     *
     * @param office
     * @return
     */
    public boolean removeOffice(String office) {
        boolean removed = false;
        if (office != null) {
            for (int i = 0; i < offices.size() && !removed; ++i) {
                if (offices.get(i).getIdOffice().compareTo(office) == 0) {
                    this.offices.remove(i);
                    removed = true;
                }
            }
        }
        return removed;
    }

    /**
     * Forward transaction to the correct account or throw a exception
     * otherwise.
     *
     * @param t ( Transaction to forward )
     * @param destine ( The transaction destine )
     *
     * @throws es.unileon.ulebank.handler.MalformedHandlerException( If the
     * destine isn't well-formed )
     * @throws TransactionException ( If the transaction cannot be deliver )
     */
//    public void doTransaction(Transaction t, String destine) throws MalformedHandlerException, TransactionException {
//        StringBuilder error = new StringBuilder();
//        if (t != null && destine != null) {
//            String handler = destine;
//            String bank = handler.getBankHandler();
//            if (this.bankID.compareTo(bank) == 0) {
//                String office = handler.getOfficeHandler();
//                boolean found = false;
//                for (int i = 0; i < this.offices.size() && !found; i++) {
//                    if (this.offices.get(i).getIdOffice().compareTo(office) == 0) {
//                        found = true;
//                        this.offices.get(i).doTransaction(t, destine);
//                    }
//                }
//                if (!found) {
//                    error.append("Error, office not found\n");
//                }
//            } else {
//                this.manager.doTransaction(t, destine);
//            }
//        } else {
//            error.append(("The transaction cannot be null or destination be null"));
//        }
//
//        if (error.length() > 0) {
//            LOG.error("Bank id " + this.bankID + " error : " + error.toString());
//            throw new TransactionException(error.toString());
//        }
//    }
}
