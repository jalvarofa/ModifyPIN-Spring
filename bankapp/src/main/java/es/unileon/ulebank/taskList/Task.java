/* Application developed for AW subject, belonging to passive operations
 group.*/
package es.unileon.ulebank.taskList;

import es.unileon.ulebank.command.Command;
import es.unileon.ulebank.exceptions.PaymentException;
import es.unileon.ulebank.exceptions.TransactionException;
import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.fees.InvalidFeeException;
import es.unileon.ulebank.handler.Handler;

import java.util.Date;

/**
 *
 * @author runix
 */
public class Task {

    
    private final Command command;

    private final Date effectiveDate;

    public Task(Date effectiveDate, Command command) {
        this.effectiveDate = effectiveDate;
        this.command = command;
    }

    public Handler getID() {
        return this.command.getId();
    }

    public void execute() throws Exception {
        this.command.execute();
    }

    public void undo() throws TransferException, es.unileon.ulebank.history.TransactionException {
        this.command.undo();
    }

    public void redo() throws PaymentException, TransactionException, es.unileon.ulebank.history.TransactionException {
        this.command.redo();
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
}
