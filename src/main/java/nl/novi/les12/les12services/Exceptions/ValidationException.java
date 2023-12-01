package nl.novi.les12.les12services.Exceptions;

public class ValidationException extends RuntimeException{
        public ValidationException() {
            super();
        }

        public ValidationException(String message) {
            super(message);
        }


}
