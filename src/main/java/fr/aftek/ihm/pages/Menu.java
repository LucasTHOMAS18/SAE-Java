package fr.aftek.ihm.pages;

import java.io.IOException;

import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class Menu extends BorderPane {
    private ApplicationJO application;

    public Menu(ApplicationJO application, boolean afficherAdm) throws IOException {
        this.application = application;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageBienvenueScrollBar.fxml"));
        loader.setController(new ControleurMenu(application, afficherAdm));
        loader.setRoot(this);
        loader.load();
    }

    public void btnAthletes() {
        // TODO
    }

    public void btnEpreuves() {
        // TODO
    }

    public void btnEquipes() {
        // TODO
    }

    public void btnPays() {
        // TODO
    }

    public void btnAdmin() {
        // TODO
    }
}
