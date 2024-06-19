package fr.aftek.ihm.pages;

import java.io.IOException;

import fr.aftek.ihm.ApplicationJO;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class Menu extends BorderPane {
    private ApplicationJO application;

    public Menu(ApplicationJO application) throws IOException {
        this.application = application;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }
}
