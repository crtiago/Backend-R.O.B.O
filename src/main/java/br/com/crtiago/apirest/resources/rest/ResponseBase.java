package br.com.crtiago.apirest.resources.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseBase<E> {

	@JsonProperty(value = "Success")
	private boolean success;
	@JsonProperty(value = "Message")
	private String message;
	@JsonProperty(value = "Data")
	private E data;

	public ResponseBase() {
		super();
	}

	public ResponseBase(boolean success, String message, E data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

}
