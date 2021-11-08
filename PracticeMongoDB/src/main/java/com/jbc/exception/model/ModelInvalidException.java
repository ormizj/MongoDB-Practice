package com.jbc.exception.model;

import com.jbc.exception.general.ModelException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
import com.jbc.util.model.ModelAttributeUtil;
import com.jbc.util.model.ModelTypeUtil;

public final class ModelInvalidException extends ModelException {

	private static final long serialVersionUID = -1188147176818121021L;

	private ModelAttributeUtil attribute;

	public ModelInvalidException(ModelTypeUtil model, ModelAttributeUtil attribute) {
		errorCode = ExceptionErrorCodeUtil.DepartmentInvalidException.toString();
		this.model = model;
		this.attribute = attribute;
	}

	@Override
	public String toString() {
		return "The " + model + ": " + attribute + " is not valid, please make sure the " + attribute + " is valid.";
	}

}
