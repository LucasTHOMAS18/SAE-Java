package fr.aftek.ihm.pages;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;

import fr.aftek.Athlete;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurSelectionAthlete;
import javafx.fxml.FXMLLoader;

public class PageSelectionAthlete extends Page{
    private ControleurSelectionAthlete controleur;
    public PageSelectionAthlete(ApplicationJO application, Collection<Athlete> liste, boolean multi, Consumer<Collection<Athlete>> cons) throws IOException {
        
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SelectionnerAthlete.fxml"));
        controleur = new ControleurSelectionAthlete(application, multi);
        loader.setController(controleur);
        loader.setRoot(this);
        loader.load();
        controleur.init(liste, cons);
    }

    public PageSelectionAthlete(ApplicationJO application, boolean mutli, Consumer<Collection<Athlete>> cons) throws IOException{
        this(application, ApplicationJO.PROVIDER.getManager().getAthletes(), mutli, cons);
    }

    public boolean isRes() {
        return controleur.isRes();
    }

    public Athlete getRes() {
        return controleur.getRes();
    }

    public Collection<Athlete> getResMulti() {
        return controleur.getResMulti();
    }
}
