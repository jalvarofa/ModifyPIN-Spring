/* Application developed for AW subject, belonging to passive operations
 group.*/

package com.companyname.springapp.exceptions;

/**
 * @author Israel Garcia Centeno
 */
public class MalformedHandlerException extends RuntimeException{
    
	private static final long serialVersionUID = 1L;

	public MalformedHandlerException(String msg) {
        super(msg);
    }
}
