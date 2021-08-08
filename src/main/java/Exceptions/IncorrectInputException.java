package Exceptions;

public class IncorrectInputException extends Exception{

    public IncorrectInputException(String msg){
        super("Incorrect input: " + msg);
    }

}
