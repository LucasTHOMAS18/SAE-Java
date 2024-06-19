package fr.aftek.ihm.controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControlleurBienvenue extends Controleur {
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnAthletes;
    @FXML
    private Button btnEquipes;
    @FXML
    private Button btnEpreuves;
    @FXML
    private Button btnPays;
    @FXML
    private Button btnAdmin;

    public ControlleurBienvenue(){
        //TODO
    }

    @FXML
    public void retourAcceuil(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @FXML
    private void afficherAthletes() {
        //TODO
    }

    @FXML
    private void afficherEquipes() {
        //TODO
    }

    @FXML
    private void afficherEpreuves() {
        //TODO
    }

    @FXML
    private void afficherPays() {
        //TODO
    }

    @FXML
    private void afficherAdmin() {
        //TODO
    }

    @FXML
    private void init() {
        //TODO
    }

}
