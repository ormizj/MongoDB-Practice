package com.jbc.exception;

import lombok.Getter;
import lombok.Setter;

/*basic exception that all other exceptions will extend*/
@Getter
@Setter
public abstract class CustomException extends Exception {

	private static final long serialVersionUID = -5768709396919501503L;

	protected String errorCode;

}
