package fr.aftek.ihm.controleurs;

import java.io.IOException;

import fr.aftek.ihm.ApplicationJO;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class ControleurMenu extends Controleur {

    @FXML public VBox vboxAdm;

    private boolean afficherAdm;

    public ControleurMenu(ApplicationJO application, boolean afficherAdm) {
        this.application = application;
        this.afficherAdm = afficherAdm;
    }

    @FXML
    public void initialize() {
        vboxAdm.setVisible(afficherAdm);
    }

    public void afficherAthletes() throws IOException, InterruptedException {
        application.classementAthletes();
    }

    public void afficherEquipes() {
        // TODO
    }

    public void afficherEpreuves() {
        // TODO
    }

    public void afficherPays() {
        // TODO
    }
    
    public void afficherAdmin() {
        // TODO
    }
}
