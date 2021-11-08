package com.jbc.exception.model;

import com.jbc.exception.general.ModelException;
import com.jbc.util.exception.ExceptionErrorCodeUtil;
import com.jbc.util.model.ModelTypeUtil;

/*exception for a Model that is currently absent from the data-base*/
public class ModelEmptyException extends ModelException {

	private static final long serialVersionUID = -960829004929513135L;

	public ModelEmptyException(ModelTypeUtil model) {
		errorCode = ExceptionErrorCodeUtil.DepartmentsIsEmptyException.toString();
		this.model = model;
	}

	@Override
	public String toString() {
		return "There are no " + model.toPlural() + " in the system.";
	}

}
