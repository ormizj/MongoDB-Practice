package com.jbc.exception.model;

import com.jbc.exception.general.ModelException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
import com.jbc.util.model.ModelTypeUtil;

public final class ModelNullException extends ModelException {

	private static final long serialVersionUID = -5935761979130763757L;

	public ModelNullException(ModelTypeUtil model) {
		errorCode = ExceptionErrorCodeUtil.DepartmentNullException.toString();
		this.model = model;
	}

	@Override
	public String toString() {
		return "The entered " + model + " is, or contains null, please make sure you instantiate the " + model
				+ " correctly.";
	}

}
