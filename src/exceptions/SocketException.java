package exceptions;

/**
 * Subclass of {@link CustomException}. This class is thrown when there is an exception with the
 * sockets.  
 * 
 * @author Jim Stanev
 */
public class SocketException extends CustomException{

	
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor. Calls super class {@link CustomException}.
	 * 
	 * @param message the message of the exception to pass
	 */
	public SocketException(String message) {
		super(message);
	}
}
