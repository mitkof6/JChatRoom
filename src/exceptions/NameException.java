package exceptions;

/**
 * Subclass of {@link CustomException}. This class is thrown when there is an exception with name 
 * Option pane.
 * 
 * @author Jim Stanev
 */
public class NameException extends CustomException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * The constructor. Calls super class {@link CustomException}.
	 * 
	 * @param message the message of the exception to pass
	 */
	public NameException(String message) {
		super(message);
	}

}
