package seedu.duke.utils.exceptions;

/**
 * Custom exception to indicate that a prerequisite for a certain module is not satisfied.
 * This exception should be thrown when a required prerequisite for a module is not met,
 * preventing the successful completion of an operation.
 *
 * @author SebasFok
 */
public class FailPrereqException extends Exception{
    public FailPrereqException(String message) {
        super(message);
    }
}
