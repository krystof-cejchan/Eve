package enums_annotations_exceptions.exceptions;

public class InvalidXML extends Exception {
    /**
     * prints out message<br>
     * shuts down the program
     */
    public InvalidXML() {
        super.printStackTrace();
        System.exit(-1);
    }
}
