package fr.aftek.ihm.controleurs;

import java.util.List;

import fr.aftek.Equipe;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.FlagProvider;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ControleurClassementEquipes extends Controleur {
    @FXML
    private TableView<EpreuveLigne> table;
    @FXML
    private TableColumn<EpreuveLigne, ImageView> pays;
    @FXML
    private TableColumn<EpreuveLigne, String> nom;
    @FXML
    private TableColumn<EpreuveLigne, String> sport;
    @FXML
    private TableColumn<EpreuveLigne, Integer> or;
    @FXML
    private TableColumn<EpreuveLigne, Integer> argent;
    @FXML
    private TableColumn<EpreuveLigne, Integer> bronze;

    public ControleurClassementEquipes(ApplicationJO appli) {
        this.application = appli;
    }

    public void init(List<Equipe> liste){
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        pays.setComparator((o1, o2) -> o1.getAccessibleText().compareTo(o2.getAccessibleText()));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        or.setCellValueFactory(new PropertyValueFactory<>("or"));
        argent.setCellValueFactory(new PropertyValueFactory<>("argent"));
        bronze.setCellValueFactory(new PropertyValueFactory<>("bronze"));
        
        for (Equipe a : liste) {
            table.getItems().add( new EpreuveLigne(a.getNom(), a.getSport().getNomSport().getNom(), a.getPays().getNom()));
        }
    }

    public class EpreuveLigne {
        public ImageView pays;
        public String nom;
        public String prenom;
        public String sport;
        public int or;
        public int argent;
        public int bronze;

        public EpreuveLigne(String nom, String sport, String nomPays){
            this.nom = nom;
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
