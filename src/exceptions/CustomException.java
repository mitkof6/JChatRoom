package exceptions;

/**
 * The custom exception class. This class extends Exception.
 * 
 * @author Jim Stanev
 */
public class CustomException extends Exception{

	
	private static final long serialVersionUID = 1L;
	protected String message;
	
	/**
	 * The constructor.
	 * 
	 * @param message the message of the exception to pass
	 */
	public CustomException(String message){
		this.message = message;
	}
	
	/**
	 * The message getter.
	 * 
	 * @return message the message of the exception
	 */
	public String getMessage(){
		return this.message;
	}
}
