package fr.aftek.ihm.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.pages.Page;
import fr.aftek.ihm.controleurs.ControleurAdmin;
import fr.aftek.ihm.controleurs.ControlleurBienvenue;

public class PageAdmin extends Page {
    private ApplicationJO application;

    public PageAdmin(ApplicationJO application) throws IOException {
        this.application = application;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PageAdmin.fxml"));
        ControleurAdmin ControleurAdmin = new ControleurAdmin(application);
        loader.setController(ControleurAdmin);
        loader.setRoot(this);
        loader.load();
    }


}


