package com.jbc.exception.model;

import com.jbc.exception.general.ModelException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
import com.jbc.util.model.ModelAttributeUtil;
import com.jbc.util.model.ModelTypeUtil;

public class ModelAlreadyExistsException extends ModelException {

	private static final long serialVersionUID = -6999516401879961430L;

	private ModelAttributeUtil attribute;
	private String value;

	public ModelAlreadyExistsException(ModelTypeUtil model, ModelAttributeUtil attribute, long id) {
		constructorInit(model, attribute);
		value = String.valueOf(id);
	}

	public ModelAlreadyExistsException(ModelTypeUtil model, ModelAttributeUtil attribute, String name) {
		constructorInit(model, attribute);
		value = name;
	}

	private void constructorInit(ModelTypeUtil model, ModelAttributeUtil attribute) {
		errorCode = ExceptionErrorCodeUtil.DepartmentAlreadyExistsException.toString();
		this.attribute = attribute;
		this.model = model;
	}

	@Override
	public String toString() {
		return model + " with the " + attribute + ": " + value + ", already exists, please enter a different "
				+ attribute + ".";
	}

}
