package com.capgemini.exception;

/**
 * 
 * @author chao538
 *
 */
@SuppressWarnings("serial")
public class ExceptionMessege extends Exception{
	private String state;
	
	public String getUserNameError() {
		return userNameError;
	}

	public void setUserNameError(String userNameError) {
		this.userNameError = userNameError;
	}

	public ExceptionMessege(String state, String userNameError) {
		super();
		this.state = state;
		this.userNameError = userNameError;
	}

	private String userNameError;
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public ExceptionMessege() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExceptionMessege(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExceptionMessege(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ExceptionMessege(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized Throwable getCause() {
		// TODO Auto-generated method stub
		return super.getCause();
	}

	@Override
	public String getLocalizedMessage() {
		// TODO Auto-generated method stub
		return super.getLocalizedMessage();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	
}
