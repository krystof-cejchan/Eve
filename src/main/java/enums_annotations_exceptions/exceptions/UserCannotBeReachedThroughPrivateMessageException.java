package enums_annotations_exceptions.exceptions;

public class UserCannotBeReachedThroughPrivateMessageException extends Exception {
    public UserCannotBeReachedThroughPrivateMessageException(String errorMessage) {
        super(errorMessage);
    }

    public UserCannotBeReachedThroughPrivateMessageException() {
    }


}
