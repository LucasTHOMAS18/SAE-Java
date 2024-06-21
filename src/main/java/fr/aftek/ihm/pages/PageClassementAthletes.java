package fr.aftek.ihm.pages;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.aftek.Athlete;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurClassementAthlete;
import javafx.fxml.FXMLLoader;

/**
 * Classe PageModifAthlete qui étend BorderPane.
 * Affiche la page de choix de sport de l'application.
 */
public class PageClassementAthletes extends Page {
    
    private ControleurClassementAthlete controleurClassementAthlete;

    /**
     * Constructeur de la classe PageModifAthlete.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */
    public PageClassementAthletes(ApplicationJO application, Collection<Athlete> liste) throws IOException {
        
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassementAthlete.fxml"));
        controleurClassementAthlete = new ControleurClassementAthlete(application);
        loader.setController(controleurClassementAthlete);
        loader.setRoot(this);
        loader.load();
        controleurClassementAthlete.init(liste);
    }

    public PageClassementAthletes(ApplicationJO application) throws IOException{
        this(application, ApplicationJO.PROVIDER.getManager().getAthletes());
    }
}
