package fr.aftek;

public class GenderException extends RuntimeException {
    public GenderException(String message){
        super(message);
    }
    public GenderException(){
        super();
    }
}
