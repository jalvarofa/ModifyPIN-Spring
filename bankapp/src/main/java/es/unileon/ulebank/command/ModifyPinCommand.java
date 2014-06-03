package es.unileon.ulebank.command;

import java.io.IOException;

import org.apache.log4j.Logger;

import es.unileon.ulebank.handler.CommandHandler;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.payments.Card;
/**
 * @author Israel
 * Comando para modificar el codigo PIN de la tarjeta
 */
public class ModifyPinCommand implements Command {
	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(ModifyPinCommand.class.getName());
	/**
	 * Identificador del comando
	 */
	private Handler id;
	/**
	 * Tarjeta cuyo PIN vamos a modificar
	 */
	private Card card;
	/**
	 * PIN que se va a modificar
	 */
	private String newPin;
	/**
	 * PIN antes de modificarlo
	 */
	private String oldPin;
//	private SimpleCardManager cM;
	
	/**
	 * Constructor de la clase
	 * @param cardManager 
	 * @param cardId
	 * @param newPin
	 */
	public ModifyPinCommand(Card card, String pin) {
		this.id = new CommandHandler(card.getCardNumber());
		this.card = card;
		this.newPin = pin;
//		this.cM = cardManager;
	}
	
	/**
	 * Realiza la modificacion del PIN
	 * @throws IOException 
	 */
	public void execute() throws IOException {
			//Almacenamos el antiguo PIN
			this.oldPin = card.getPin();
			//Cambiamos el PIN por el nuevo
			this.card.setPin(newPin);
	}

	/**
	 * Deshace la modificacion del PIN restaurando el anterior
	 */
	public void undo() {
		try {
			//Restaura el PIN al valor anterior
			this.card.setPin(oldPin);
		} catch (IOException e) {
			LOG.info(e.getMessage());
		}
	}

	/**
	 * Rehace la modificacion del PIN
	 */
	public void redo() {
		try {
			//Recuperamos la modificacion del PIN
			this.card.setPin(newPin);
		} catch (IOException e) {
			LOG.info(e.getMessage());
		}
	}

	/**
	 * Devuelve el identificador del comando
	 */
	public Handler getId() {
		return this.id;
	}
}
