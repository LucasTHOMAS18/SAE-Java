package fr.aftek.ihm.controleurs;

import java.io.IOException;

import fr.aftek.ihm.ApplicationJO;
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

    private ApplicationJO application;

    public ControlleurBienvenue(ApplicationJO application){
        this.application = application;
    }

    @FXML
    public void retourAcceuil(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @FXML
    private void afficherAthletes() throws IOException, InterruptedException {
        application.classementAthletes();
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
    private void afficherAdmin() throws IOException {
        //TODO
        application.admin();
    }

    @FXML
    private void init() {
        //TODO
    }

}
