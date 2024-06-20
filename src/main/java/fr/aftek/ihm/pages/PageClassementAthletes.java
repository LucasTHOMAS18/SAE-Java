package fr.aftek.ihm.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.aftek.ihm.*;
import fr.aftek.ihm.controleurs.ControleurClassementAthlete;
import fr.aftek.*;
import fr.aftek.data.DataProvider;

/**
 * Classe PageModifAthlete qui étend BorderPane.
 * Affiche la page de choix de sport de l'application.
 */
public class PageClassementAthletes extends BorderPane {
    
    private ControleurClassementAthlete controleurClassementAthlete;

    /**
     * Constructeur de la classe PageModifAthlete.
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */
    public PageClassementAthletes(List<Athlete> liste) throws IOException {
        
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassementAthlete.fxml"));
        controleurClassementAthlete = new ControleurClassementAthlete();
        loader.setController(controleurClassementAthlete);
        loader.setRoot(this);
        loader.load();
        controleurClassementAthlete.init(liste);
    }

    public PageClassementAthletes(Set<Athlete> set) throws IOException {
        this(set.stream().collect(Collectors.toList()));
    }

    public PageClassementAthletes() throws IOException{
        this(ApplicationJO.PROVIDER.getManager().getAthletes());
    }
}
