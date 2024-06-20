package fr.aftek.ihm.controleurs;

import java.util.List;

import fr.aftek.Epreuve;
import fr.aftek.ihm.ApplicationJO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControleurClassementEpreuve extends Controleur {
    @FXML
    private TableView<EpreuveLigne> table;
    @FXML
    private TableColumn<EpreuveLigne, String> sport;
    @FXML
    private TableColumn<EpreuveLigne, String> nom;
    @FXML
    private TableColumn<EpreuveLigne, Character> sexe;

    public ControleurClassementEpreuve(ApplicationJO appli){
        this.application = appli;
    }

    public void init(List<Epreuve> liste){
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));

        for (Epreuve e : liste){
            table.getItems().add( new EpreuveLigne(e.getSport().getNomSport().getNom(), e.getNom(), e.getSexe()));
        }
    }

    public class EpreuveLigne{
        public String sport;
        public String nom;
        public Character sexe;
    

        public EpreuveLigne(String sport, String nom, Character sexe){
            this.sport = sport;
            this.nom = nom;
            this.sexe = sexe;
        }

        public String getSport(){
            return sport;
        }

        public String getNom(){
            return nom;
        }

        public Character getSexe(){
            return sexe;
        }
    }
}
