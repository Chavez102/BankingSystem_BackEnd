package com.Revature.model;

public class Response {
	
	public String message;

	public Response(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + "]";
	}
	
	
}
