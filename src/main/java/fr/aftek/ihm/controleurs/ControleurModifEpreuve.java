package fr.aftek.ihm.controleurs;
import java.util.Collection;
import java.util.List;

import fr.aftek.Epreuve;
import fr.aftek.ihm.*;
import fr.aftek.ihm.controleurs.ControleurClassementEpreuve.EpreuveLigne;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleurModifEpreuve extends Controleur {
    @FXML
    private TableView<EpreuveLigne> table;
    @FXML
    private TableColumn<EpreuveLigne, String> sport;
    @FXML
    private TableColumn<EpreuveLigne, String> nom;
    @FXML
    private TableColumn<EpreuveLigne, Character> sexe;
    @FXML
    private TableColumn<EpreuveLigne, Integer> nbAthletes;

    public ControleurModifEpreuve(ApplicationJO application) {
        this.application = application;
    }

    public void init(Collection<Epreuve> collect) {
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        nbAthletes.setCellValueFactory(new PropertyValueFactory<>("nbAthletes"));

        for (Epreuve e : collect){
            table.getItems().add(new EpreuveLigne(e.getSport().getNomSport().getNom(), e.getNom(), e.getSexe(), e.getParticipants().size()));
        }
    }

    public void modifierNom(){

    }
    public void modifierSport(){

    }
    public void modifierSexe(){

    }
    public void ajouterAthlete(){

    }
    public void supprimerAthlete(){
        
    }
    public void supprimerEpreuve(){

    }
}
