package fr.aftek;

public class GenderException extends RuntimeException {
    /**
     * Constructeur pour créer une exception avec un message personnalisé.
     *
     * @param message Le message de l'exception.
     */
    public GenderException(String message){
        super(message);
    }
    public GenderException(){
        super();
    }
}
