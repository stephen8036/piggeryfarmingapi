package com.piggery.util;

import java.util.ArrayList;
import java.util.List;

public class ResponseModel {
	private int status;

	private String message;

	private String token;

	private List<Object> data;

	private Object object;

	public ResponseModel() {

	}

	public ResponseModel(int status, String message, String token, Object object) {
		this.status = status;
		this.message = message;
		this.token = token;
		this.object = object;
	}

	public ResponseModel(int status, String message, String token, Object object, List<Object> data) {
		this.status = status;
		this.message = message;
		this.token = token;
		this.object = object;
		this.data = data;
	}

	public ResponseModel(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public ResponseModel(int status, String message, List<Object> data) {
		this.status = status;
		this.message = message;

	}

	public ResponseModel(int status, String message, ArrayList<Object> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
