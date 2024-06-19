package fr.aftek.ihm.pages;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

public class PageBienvenueScrollBar extends ScrollPane {
    public PageBienvenueScrollBar()throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/aftek/ihm/fxml/PageBienvenueScrollBar.fxml"));
        loader.setRoot(this);
        loader.setController(new fr.aftek.ihm.controleurs.ControlleurBienvenue());
        loader.load();

    }
}