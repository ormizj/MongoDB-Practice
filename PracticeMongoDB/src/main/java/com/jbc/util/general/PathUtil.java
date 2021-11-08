package com.jbc.util.general;

/*enum that contains all the used Paths information*/
public enum PathUtil {

	COM_JBC_PACKAGE;

	@Override
	public String toString() {
		switch (this) {
		case COM_JBC_PACKAGE:
			return "com.jbc";
		default:
			return super.toString();
		}
	}

}