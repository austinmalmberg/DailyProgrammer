package com.austin.exceptions;

/**
 * Thrown when a value becomes larger than the object's maximum value.
 * 
 * @author Austin Malmberg
 *
 */
@SuppressWarnings("serial")
public class OverflowException extends RuntimeException {

	public OverflowException() {
		super();
	}
	
	public OverflowException(String s) {
		super(s);
	}
}
