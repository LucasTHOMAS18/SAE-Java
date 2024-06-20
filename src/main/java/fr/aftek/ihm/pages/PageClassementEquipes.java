package fr.aftek.ihm.pages;

import java.io.IOException;
import java.util.List;

import fr.aftek.Equipe;
import fr.aftek.ihm.controleurs.ControleurClassementEquipes;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class PageClassementEquipes extends BorderPane {
    private ControleurClassementEquipes controleurClassementEquipes;

    public PageClassementEquipes(List<Equipe> liste) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Equipes.fxml"));
        loader.setController(controleurClassementEquipes);
        loader.setRoot(this);
        loader.load();
        controleurClassementEquipes.init(liste);
    }
}
