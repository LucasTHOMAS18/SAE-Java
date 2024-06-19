package fr.aftek.ihm.pages;

import java.io.IOException;

import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurAdmin;
import fr.aftek.ihm.controleurs.ControlleurBienvenue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

public class PageBienvenueScrollBar extends ScrollPane {
    private ApplicationJO application;

    public PageBienvenueScrollBar(ApplicationJO application) throws IOException {
        this.application = application;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageBienvenueScrollBar.fxml"));
        ControlleurBienvenue ControlleurBienvenue = new ControlleurBienvenue();
        loader.setController(ControlleurBienvenue);
        loader.setRoot(this);
        loader.load();
    }
}