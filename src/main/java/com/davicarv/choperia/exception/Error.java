package com.davicarv.choperia.exception;

import java.io.Serializable;
import java.util.Calendar;

public class Error implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Calendar timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public Error(Calendar timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	
	
}
