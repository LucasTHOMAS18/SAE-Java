package fr.aftek.ihm.controleurs;
import java.util.List;
import java.util.Optional;

import fr.aftek.Athlete;
import fr.aftek.ihm.*;
import fr.aftek.ihm.pages.PageModifier.TypeModification;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ControleurModifAthlete extends Controleur {
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
    private List<Athlete> liste;

    public ControleurModifAthlete(ApplicationJO application) {
        this.application = application;
    }

    public void init(List<Athlete> liste){
        this.liste = liste;
        table.getItems().clear();
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        pays.setComparator((o1, o2) -> o1.getAccessibleText().compareTo(o2.getAccessibleText()));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));

        for (Athlete a : liste){
            table.getItems().add(new AthleteLigne(a.getNom(), a.getPrenom(), a.getSport().getNomSport().getNom(), a.getPays().getNom()));
        }
    }

    @FXML
    public void modifierNom(){
        if(selectionner()){
            PopUp<String> popUp = new PopUp<>(PopUpType.DEMANDER, "Modifier nom", "Quel nom voulez-vous donner à cet athlète?");
            popUp.showAndWait();
            if(!popUp.getTf().getText().isBlank()){
                AthleteLigne al = table.getSelectionModel().getSelectedItem();
                Athlete a = ApplicationJO.PROVIDER.getManager().getAthlete(al.getNom(), al.getPrenom());
                a.setNom(popUp.getTf().getText());
                init(liste);
            }
        }
    }
    @FXML
    public void modifierPrenom(){
        if(selectionner()){
            PopUp<String> popUp = new PopUp<>(PopUpType.DEMANDER, "Modifier prenom", "Quel prenom voulez-vous donner à cet athlète?");
            popUp.showAndWait();
            if(!popUp.getTf().getText().isBlank()){
                AthleteLigne al = table.getSelectionModel().getSelectedItem();
                Athlete a = ApplicationJO.PROVIDER.getManager().getAthlete(al.getNom(), al.getPrenom());
                a.setNom(popUp.getTf().getText());
                init(liste);
            }
        }
    }
    @FXML
    public void modifierPays(){
        if(selectionner()){
            PopUp<String> popUp = new PopUp<>(PopUpType.DEMANDER, "Modifier nom", "Quel nom voulez-vous donner à cet athlète?");
            popUp.showAndWait();
            if(!popUp.getTf().getText().isBlank()){
                AthleteLigne al = table.getSelectionModel().getSelectedItem();
                Athlete a = ApplicationJO.PROVIDER.getManager().getAthlete(al.getNom(), al.getPrenom());
                a.setNom(popUp.getTf().getText());
                init(liste);
            }
        }
    }
    @FXML
    public void modifierStats(){
        if(selectionner()){
            PopUp<String> popUp = new PopUp<>(PopUpType.DEMANDER, "Modifier nom", "Quel nom voulez-vous donner à cet athlète?");
            popUp.showAndWait();
            if(!popUp.getTf().getText().isBlank()){
                AthleteLigne al = table.getSelectionModel().getSelectedItem();
                Athlete a = ApplicationJO.PROVIDER.getManager().getAthlete(al.getNom(), al.getPrenom());
                a.setNom(popUp.getTf().getText());
                init(liste);
            }
        }
    }
    @FXML
    public void modifierSport(){
        if(selectionner()){
            PopUp<String> popUp = new PopUp<>(PopUpType.DEMANDER, "Modifier nom", "Quel nom voulez-vous donner à cet athlète?");
            popUp.showAndWait();
            if(!popUp.getTf().getText().isBlank()){
                AthleteLigne al = table.getSelectionModel().getSelectedItem();
                Athlete a = ApplicationJO.PROVIDER.getManager().getAthlete(al.getNom(), al.getPrenom());
                a.setNom(popUp.getTf().getText());
                init(liste);
            }
        }
    }
    @FXML
    public void supprimerAthlete(){
        if(selectionner()){
            Optional<ButtonType> res = new PopUp<ButtonType>(PopUpType.CONFIRMATION, "Suppression", "Voulez-vous vraiment supprimer cet athlète?").showAndWait();
            res.ifPresent((r)->{
                if(r == ButtonType.OK){
                    AthleteLigne al = table.getSelectionModel().getSelectedItem();
                    Athlete a = ApplicationJO.PROVIDER.getManager().getAthlete(al.getNom(), al.getPrenom());
                    ApplicationJO.PROVIDER.getManager().getAthletes().remove(a);
                    this.liste.remove(a);
                    init(liste);
                }
            });
        }
    }
    private boolean selectionner(){
        AthleteLigne al = table.getSelectionModel().getSelectedItem();
        if(al == null){
            new PopUp<>(PopUpType.INFORMATION, "Erreur", "Veuillez sélectionner un athlète").showAndWait();
            return false;
        }
        return true;
    }

    public class AthleteLigne {
        public ImageView pays;
        public String nom;
        public String prenom;
        public String sport;

        public AthleteLigne(String nom, String prenom, String sport, String nomPays){
            this.nom = nom;
            this.prenom = prenom;
            this.pays = new ImageView(FlagProvider.getFlag(nomPays));
            this.pays.setAccessibleHelp(nomPays);
            this.pays.setAccessibleText(nomPays);
            Tooltip.install(this.pays, new Tooltip(nomPays));
            this.sport = sport;
        }
        public String getNom() {
            return nom;
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
