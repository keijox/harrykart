package se.atg.service.harrykart.java.error;



//import io.swagger.annotations.ApiModelProperty;


// TODO: Auto-generated Javadoc
/**
 * This class shows a list of error codes and error messages for different exceptions which can occur.
 */
public class AtgErrorCode {

    /** The error code. */
	//@ApiModelProperty(notes="It prints error code",example ="2000")
    private String errorCode;
    
    /** The error message. */
	//@ApiModelProperty(notes="It prints error message",example ="Operation successfull")
    private String errorMessage;

    /**
     * returns an error code of the type String.
     *
     * @return errorCode the particular error code is returned when
     * the corresponding exception occurs.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * sets the error code.
     *
     * @param errorCode the errorCode from getErrorCode() is passed here
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     *returns a particular errorMessage corresponding to the
     * type of exception which has occured.
     * @return errorMessage returns the corresponding errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * sets the corresponding error message.
     *
     * @param errorMessage the errorMessage from getErrorMessage() is passed here
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    /**
     * contains the list of exceptions with the error codes and error messages which can occur.
     *
     * @param errorCode takes in the corresponding error code
     * @param errorMessage takes in the corresponding error message
     */
    public AtgErrorCode(String errorCode, String errorMessage) {
    	this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * The response code is used when the operation is successful.
     */
    public static final AtgErrorCode SUCCESS = new AtgErrorCode("5000", "Operation successfully");

    
     /**
     * The exception is thrown when there is an unknown error.
     */
    public static final AtgErrorCode UNKNOWN_EXCEPTION = new AtgErrorCode("9000", "Unknown error");
 
    
    /**
     * The exception is thrown when there is an unknown error.
     */
    public static final AtgErrorCode DIVIDE_BY_ZERO_EXCEPTION = new AtgErrorCode("9001", "Divide by zero error");
    
}

