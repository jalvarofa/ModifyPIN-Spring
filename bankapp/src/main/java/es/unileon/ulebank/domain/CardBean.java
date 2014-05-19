package es.unileon.ulebank.domain;

public class CardBean {
	private String pin;

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		System.out.println("------->" + pin);
		this.pin = pin;
	}
}
