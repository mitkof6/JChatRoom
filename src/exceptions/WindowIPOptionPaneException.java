package exceptions;

/**
 * Subclass of {@link CustomException}. This class is thrown when there is an exception with 
 * the Window IP Option Pane.
 *
 *@author Jim Stanev
 */
public class WindowIPOptionPaneException extends CustomException{

	private static final long serialVersionUID = 1L;

	/**
	 * The constructor. Calls super class {@link CustomException}.
	 * 
	 * @param message the message of the exception to pass
	 */
	public WindowIPOptionPaneException(String message) {
		super(message);
	}
}
