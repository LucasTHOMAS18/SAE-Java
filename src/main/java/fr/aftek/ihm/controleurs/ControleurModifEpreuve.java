package fr.aftek.ihm.controleurs;
import java.util.Collection;
import java.util.List;

import fr.aftek.Athlete;
import fr.aftek.Epreuve;
import fr.aftek.Sport;
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
    private Collection<Epreuve> liste;

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
        if(selectionner()){
            PopUp<String> popUp = new PopUp<>(PopUpType.DEMANDER, "Modifier sport", "Quel sport voulez-vous attribuer à cette Epreuve?");
            popUp.showAndWait();
            if(!popUp.getTf().getText().isBlank()){
                EpreuveLigne el = table.getSelectionModel().getSelectedItem();
                Epreuve e = ApplicationJO.PROVIDER.getManager().getEpreuve(el.getNom(), el.getSport());
                Sport s = ApplicationJO.PROVIDER.getManager().getSport(popUp.getTf().getText());
                if (s == null){
                    popUp = new PopUp<>(PopUpType.ERREUR, "Erreur", "Ce sport n'existe pas");
                    popUp.showAndWait();
                    return;
                }
                e.setSport(s);
                init(liste);
            }
        }
    }
    
    public void modifierSexe() {
        if (selectionner()) {
            PopUp<String> popUp = new PopUp<>(PopUpType.DEMANDER, "Modifier sexe", "Quel sexe voulez-vous attribuer à cette Epreuve? (H/F)");
            popUp.showAndWait();
            if (!popUp.getTf().getText().isBlank()) {
                EpreuveLigne el = table.getSelectionModel().getSelectedItem();
                Epreuve e = ApplicationJO.PROVIDER.getManager().getEpreuve(el.getNom(), el.getSport());
                char sexe = popUp.getTf().getText().charAt(0);
                if (sexe == 'H' || sexe == 'F') {
                    e.setSexe(sexe);
                    init(liste);
                } else {
                    popUp = new PopUp<>(PopUpType.ERREUR, "Erreur", "Sexe invalide. Veuillez entrer 'H' pour Homme ou 'F' pour Femme.");
                    popUp.showAndWait();
                }
            }
        }
    }
    public void ajouterAthlete() {
    }
    public void supprimerAthlete() {
    }
    public void supprimerEpreuve() {
        if (selectionner()) {
            EpreuveLigne el = table.getSelectionModel().getSelectedItem();
            Epreuve e = ApplicationJO.PROVIDER.getManager().getEpreuve(el.getNom(), el.getSport());
            ApplicationJO.PROVIDER.getManager().removeEpreuve(e); 
            liste.remove(e);
            init(liste);
        }
    }

    private boolean selectionner(){
        EpreuveLigne al = table.getSelectionModel().getSelectedItem();
        if(al == null){
            new PopUp<>(PopUpType.INFORMATION, "Erreur", "Veuillez sélectionner une épreuve").showAndWait();
            return false;
        }
        return true;
    }
}
