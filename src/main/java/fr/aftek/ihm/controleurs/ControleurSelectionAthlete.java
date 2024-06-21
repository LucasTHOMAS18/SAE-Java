package fr.aftek.ihm.controleurs;

import java.util.List;
import java.util.Map;

import fr.aftek.Athlete;
import fr.aftek.Epreuve;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurClassementAthlete.AthleteLigne;
import fr.aftek.ihm.controleurs.ControleurClassementEpreuve.EpreuveLigne;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ControleurSelectionAthlete {
    private ApplicationJO application;
    @FXML
    private TableView<AthleteLigne> table;
    @FXML
    private TableColumn<AthleteLigne, ImageView> pays;
    @FXML
    private TableColumn<AthleteLigne, String> nom;
    @FXML
    private TableColumn<AthleteLigne, String> prenom;
    @FXML
    private TableColumn<AthleteLigne, String> sport;
    private Map<AthleteLigne, Athlete> athletes;
    private boolean multi;

    public ControleurSelectionAthlete(ApplicationJO appli, boolean multi) {
        this.application = appli;
        this.multi = multi;
    }

    public void init(List<Athlete> liste){
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        pays.setComparator((o1, o2) -> o1.getAccessibleText().compareTo(o2.getAccessibleText()));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        if(multi) table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (Athlete a : liste){
            AthleteLigne al = new AthleteLigne(a.getNom(), a.getPrenom(), a.getSport().getNomSport().getNom(), a.getPays().getNom());
            table.getItems().add(al);
            this.athletes.put(al, a);
        }
    }

    public void valider(){
        if(multi){
            ObservableList<AthleteLigne> atl = table.getSelectionModel().getSelectedItems();
            List<Athlete> athletes = new java.util.ArrayList<>();
        }else{
            AthleteLigne al = table.getSelectionModel().getSelectedItem();
            Athlete a = athletes.get(al);
        }
        
    }
    public void annuler(){

    }
}
