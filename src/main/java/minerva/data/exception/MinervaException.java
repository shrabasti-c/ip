package minerva.data.exception;

/**
 * Represents an exception specific to the Minerva application.
 */
public class MinervaException extends Exception {

    /**
     * Constructs a {@code MinervaException} with the specified error message.
     *
     * @param errorMessage the message describing the cause of the exception
     */
    public MinervaException(String errorMessage) {
        super(errorMessage);
    }
}