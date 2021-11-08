package com.jbc.exception.model;

import com.jbc.exception.general.ModelException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
import com.jbc.util.model.ModelAttributeUtil;
import com.jbc.util.model.ModelTypeUtil;

public class ModelNotFoundException extends ModelException {

	private static final long serialVersionUID = 2702378366111544961L;

	private ModelAttributeUtil attribute;
	private String value;

	public ModelNotFoundException(ModelTypeUtil model, ModelAttributeUtil attribute, long id) {
		constructorInit(model, attribute);
		value = String.valueOf(id);
	}

	public ModelNotFoundException(ModelTypeUtil model, ModelAttributeUtil attribute, String name) {
		constructorInit(model, attribute);
		value = name;
	}

	private void constructorInit(ModelTypeUtil model, ModelAttributeUtil attribute) {
		errorCode = ExceptionErrorCodeUtil.DepartmentNotFoundException.toString();
		this.model = model;
		this.attribute = attribute;
	}

	@Override
	public String toString() {
		return model + " with the " + attribute + ": " + value + ", was not found, please make sure that the "
				+ attribute + " is correct.";
	}

}
