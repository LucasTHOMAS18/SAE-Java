package fr.aftek.ihm.pages;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurAdmin;

public class PageAdmin extends Page {
    @SuppressWarnings("unused")
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


