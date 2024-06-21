package fr.aftek.ihm.pages;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.aftek.Pays;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurClassementPays;
import javafx.fxml.FXMLLoader;

public class PageClassementPays extends Page{
    private ControleurClassementPays controleurClassementPays;

    /**
     * Constructeur de la classe PageClassementPays
     * 
     * @param application L'application principale pour accéder aux fonctionnalités globales.
     * @throws IOException Si une erreur survient lors du chargement du fichier FXML.
     */

     public PageClassementPays(ApplicationJO application, List<Pays> liste) throws IOException{
        // Charge le fichier FXML pour la page de choix de sport et initialise le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClassementPays.fxml"));
        controleurClassementPays = new ControleurClassementPays(application);
        loader.setController(controleurClassementPays);
        loader.setRoot(this);
        loader.load();
        controleurClassementPays.init(liste);
    }

    public PageClassementPays(ApplicationJO application, Set<Pays> set) throws IOException {
        this(application, set.stream().collect(Collectors.toList()));
    }

    public PageClassementPays(ApplicationJO application) throws IOException{
        this(application, ApplicationJO.PROVIDER.getManager().getPays());
    }
}
