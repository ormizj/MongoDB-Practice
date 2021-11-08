package com.jbc.exception.general;

import com.jbc.exception.CustomException;
import com.jbc.util.model.ModelTypeUtil;

/*basic exception that all other exceptions related to Model will extend*/
public abstract class ModelException extends CustomException {

	private static final long serialVersionUID = 1805399132301454759L;

	protected ModelTypeUtil model;
	
}
