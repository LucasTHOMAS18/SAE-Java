package fr.aftek.ihm.controleurs;

import javafx.fxml.FXML;
import fr.aftek.ihm.*;

public class Controleur {
    protected ApplicationJO application;
    @FXML
    public void retourAccueil(){
        application.retourAccueil();
    }
}
