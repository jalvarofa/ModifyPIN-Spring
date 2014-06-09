package com.companyname.springapp.fees;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Simple fee that applies a minimum plus a percentage of the given amount.
 * @author roobre
 */
//@Entity
//@Table(name="comissions") 
public class LinearFee implements FeeStrategy {
//	@Id
//	@Column(name = "id")
	private int id;
	
//	@Column(name = "description")
	private String description;

    /**
     * Fee to be applied as amount multiplicator, THUS ONE (a 2% fee is storead
     * as 0.02).
     */
//	@Column(name = "comission")
    private final double fee;

    /**
     * Minimum value which is always paid.
     */
//    private final double minimum;

    public LinearFee(double fee, double minimum) throws InvalidFeeException {
        if (fee < 0 || minimum < 0) {
            throw new InvalidFeeException();
        }
        
        this.fee = fee;
//        this.minimum = minimum;
    }
    
    public LinearFee() {
		this.fee = 0;
	}

    public double getFee(double value) {
        double total=this.fee * value;
//        if(total <this.minimum) {
//            total = this.minimum;
//        }
        
        return total;
    
    }
}
