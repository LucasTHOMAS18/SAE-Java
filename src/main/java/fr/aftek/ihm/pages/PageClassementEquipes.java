package fr.aftek.ihm.pages;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.aftek.Equipe;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurClassementEquipes;
import javafx.fxml.FXMLLoader;

public class PageClassementEquipes extends Page {
    private ControleurClassementEquipes controleurClassementEquipes;

    public PageClassementEquipes(ApplicationJO application, List<Equipe> liste) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassementEquipes.fxml"));
        this.controleurClassementEquipes = new ControleurClassementEquipes(application);
        loader.setController(controleurClassementEquipes);
        loader.setRoot(this);
        loader.load();
        controleurClassementEquipes.init(liste);
    }

    public PageClassementEquipes(ApplicationJO application, Set<Equipe> set) throws IOException {
        this(application, set.stream().collect(Collectors.toList()));
    }

    public PageClassementEquipes(ApplicationJO application) throws IOException{
        this(application, ApplicationJO.PROVIDER.getManager().getEquipes());
    }
}
