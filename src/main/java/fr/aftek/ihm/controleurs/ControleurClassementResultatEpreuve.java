package fr.aftek.ihm.controleurs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import fr.aftek.Athlete;
import fr.aftek.Epreuve;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.FlagProvider;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ControleurClassementResultatEpreuve extends Controleur {
    @FXML
    private TableView<ResultatEpreuveLigne> table;
    @FXML
    private TableColumn<ResultatEpreuveLigne, ImageView> pays;
    @FXML
    private TableColumn<ResultatEpreuveLigne, String> nom;
    @FXML
    private TableColumn<ResultatEpreuveLigne, String> prenom;
    @FXML
    private TableColumn<ResultatEpreuveLigne, Integer> position;

    public ControleurClassementResultatEpreuve(ApplicationJO appli, Epreuve e){
        this.application = appli;
    }

    public void init(Epreuve epreuve) {
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));

        List<Entry<Athlete, Integer>> classement = new ArrayList<>(epreuve.getClassement().entrySet());
        classement.sort(Entry.comparingByValue());
        List<Athlete> res = classement.stream().map(Entry::getKey).collect(Collectors.toList());

        for (int i = 0; i < res.size(); i++) {
            Athlete a = res.get(i);
            table.getItems().add(new ResultatEpreuveLigne(a.getPays().getNom(), a.getNom(), a.getPrenom(),i+1));
        }
    }

    public class ResultatEpreuveLigne {
        private ImageView pays;
        private String nom;
        private String prenom;
        private int position;

        public ResultatEpreuveLigne(String nomPays, String nom, String prenom, int position){
            this.pays = new ImageView(FlagProvider.getFlag(nomPays));
            this.pays.setAccessibleHelp(nomPays);
            this.pays.setAccessibleText(nomPays);
            Tooltip.install(this.pays, new Tooltip(nomPays));
            this.nom = nom;
            this.prenom = prenom;
            this.position = position;
        }

        public String getNom() {
            return nom;
        }
        public ImageView getPays() {
            return pays;
        }
        public int getPosition() {
            return position;
        }
        public String getPrenom() {
            return prenom;
        }
        
    }
}
