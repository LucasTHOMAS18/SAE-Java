package fr.aftek.ihm.controleurs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import fr.aftek.Athlete;
import fr.aftek.Epreuve;
import fr.aftek.Sport;
import fr.aftek.ihm.*;
import fr.aftek.ihm.controleurs.ControleurClassementEpreuve.EpreuveLigne;
import fr.aftek.ihm.pages.PageClassementResultatEpreuve;
import fr.aftek.ihm.pages.PageSelectionAthlete;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
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
        this.table.getItems().clear();
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        nbAthletes.setCellValueFactory(new PropertyValueFactory<>("nbAthletes"));
        this.epreuves = collect;
        for (Epreuve e : collect){
            EpreuveLigne el = new EpreuveLigne(e.getSport().getNomSport().getNom(), e.getNom(), e.getSexe(), e.getParticipants().size());
            table.getItems().add(el);
            epreuvesMap.put(el, e);
        }
    }

    public void modifierNom(){
        if(selectionner()){
            PopUp<String> popUp = new PopUp<>(PopUpType.DEMANDER, "Modifier nom", "Quel nom voulez-vous donner à cette Epreuve?");
            popUp.showAndWait();
            if(!popUp.getTf().getText().isBlank()){
                EpreuveLigne el = table.getSelectionModel().getSelectedItem();
                Epreuve e = ApplicationJO.PROVIDER.getManager().getEpreuve(el.getNom(), el.getSport());
                e.setNom(popUp.getTf().getText());
                init(liste);
            }
        } 
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
