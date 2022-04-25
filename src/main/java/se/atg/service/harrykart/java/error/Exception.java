package se.atg.service.harrykart.java.error;

/**
 * Custom ATG Exception class.
 */
public class Exception extends java.lang.Exception {

	/** The error code. */
	private String errorCode;

	/** The error message. */
	private String errorMessage;

	/**
	 * Instantiates a new Atg exception.
	 *
	 * @param e the e
	 */
	public Exception(Throwable e) {
		super(e);
	}

	/**
	 * Create AtgException object.
	 *
	 * @param errorCode    Error code
	 * @param errorMessage Error message
	 * @param e            Generic exception
	 */
	public Exception(String errorCode, String errorMessage, java.lang.Exception e) {
		super(e);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * Create AtgException object.
	 *
	 * @param error the error
	 * @param e     the e
	 */

	public Exception(ErrorCode error, Throwable e) {
		super(e);
		this.errorCode = error.getErrorCode();
		this.errorMessage = error.getErrorMessage();
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */

	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Set the error code.
	 * 
	 * @param errorCode error code of the exception which occurs.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return the error message.
	 */

	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Set the error message.
	 * 
	 * @param errorMessage error message of the exception which occurs.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
