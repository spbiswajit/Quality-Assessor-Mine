package com.qaitdevlabs.qualityassessor.common.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public GenericException(String customMsg) {
		this.setCustomMsg(customMsg);
	}

	private String customMsg;

	public String getCustomMsg() {
		return customMsg;
	}

	public void setCustomMsg(String customMsg) {
		this.customMsg = customMsg;
	}

}