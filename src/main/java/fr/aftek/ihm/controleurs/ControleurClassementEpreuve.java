package fr.aftek.ihm.controleurs;

import java.util.List;

import fr.aftek.Epreuve;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
        table.setRowFactory( tv -> {
            TableRow<EpreuveLigne> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    EpreuveLigne el = row.getItem();
                    Epreuve e = ApplicationJO.PROVIDER.getManager().getEpreuve(el.nom, el.sport);
                    if(e.getClassement().size() == 0){
                        new PopUp<>(PopUpType.ERREUR, "Erreur !", "L'épreuve n'a pas encore été simulée").showAndWait();
                    }else{
                        application.afficherResultatsEpreuve(e);
                    }
                }
            });
            return row ;
        });

        for (Epreuve e : liste){
            table.getItems().add(new EpreuveLigne(e.getSport().getNomSport().getNom(), e.getNom(), e.getSexe()));
        }
    }

    @FXML
    public void lancerEpreuve(){
        EpreuveLigne el = table.getSelectionModel().getSelectedItem();
        Epreuve e = ApplicationJO.PROVIDER.getManager().getEpreuve(el.nom, el.sport);
        e.simuleEpreuve();
        new PopUp<>(PopUpType.INFORMATION, "Simulation terminée !", "La simulation de l'épreuve " + e.getNom() + " est terminée").showAndWait();
    }
    @FXML
    public void lancerTout(){
        for (Epreuve e : ApplicationJO.PROVIDER.getManager().getEpreuves()){
            e.simuleEpreuve();
        }
        new PopUp<>(PopUpType.INFORMATION, "Simulation terminée!", "Toutes les épreuves ont été simulées").showAndWait();
    }
    @FXML
    public void saveEpreuve(){

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
