package fr.aftek.ihm.pages;

import java.io.IOException;
import java.util.List;

import fr.aftek.Equipe;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PageEquipes extends BorderPane {
    public PageEquipes(List<Equipe> liste) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Equipes.fxml"));
        loader.setRoot(this);
        loader.load();
    }
}
