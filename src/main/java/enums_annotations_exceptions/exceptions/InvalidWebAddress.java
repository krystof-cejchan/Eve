package enums_annotations_exceptions.exceptions;

public class InvalidWebAddress extends Exception {
    public InvalidWebAddress(String errorMessage) {
        super(errorMessage);
    }

    public InvalidWebAddress() {

    }


}
