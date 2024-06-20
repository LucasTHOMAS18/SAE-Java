package fr.aftek.ihm.controleurs;

import java.util.List;
import java.util.Set;

import fr.aftek.ihm.*;
import fr.aftek.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ControleurClassementAthlete extends Controleur {
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
    @FXML
    private TableColumn<AthleteLigne, Integer> or;
    @FXML
    private TableColumn<AthleteLigne, Integer> argent;
    @FXML
    private TableColumn<AthleteLigne, Integer> bronze;

    public ControleurClassementAthlete() {
    }

    public void init(List<Athlete> liste){
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        pays.setComparator((o1, o2) -> o1.getAccessibleText().compareTo(o2.getAccessibleText()));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        or.setCellValueFactory(new PropertyValueFactory<>("or"));
        argent.setCellValueFactory(new PropertyValueFactory<>("argent"));
        bronze.setCellValueFactory(new PropertyValueFactory<>("bronze"));
        
        for (Athlete a : liste){
            table.getItems().add(new AthleteLigne(a.getNom(), a.getPrenom(), a.getSport().getNomSport().getNom(), a.getPays().getNom()));
        }
    }

    public class AthleteLigne{
        public ImageView pays;
        public String nom;
        public String prenom;
        public String sport;
        public int or;
        public int argent;
        public int bronze;

        public AthleteLigne(String nom, String prenom, String sport, String nomPays){
            this.nom = nom;
            this.prenom = prenom;
            this.pays = new ImageView(FlagProvider.getFlag(nomPays));
            this.pays.setAccessibleHelp(nomPays);
            this.pays.setAccessibleText(nomPays);
            Tooltip.install(this.pays, new Tooltip(nomPays));
            this.sport = sport;
            this.or = 0;
            this.argent = 0;
            this.bronze = 0;
        }

        public int getArgent() {
            return argent;
        }
        public int getBronze() {
            return bronze;
        }
        public String getNom() {
            return nom;
        }
        public int getOr() {
            return or;
        }
        public ImageView getPays() {
            return pays;
        }
        public String getPrenom() {
            return prenom;
        }
        public String getSport() {
            return sport;
        }


    }
}
