package com.nerkar.request;

import com.nerkar.models.User;

public class CreateChatRequest {
	
	private int userId;
	
	public CreateChatRequest() {
		// TODO Auto-generated constructor stub
	}

	public CreateChatRequest(int userId) {
		super();
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
