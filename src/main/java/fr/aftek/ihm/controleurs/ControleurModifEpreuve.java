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
    private Collection<Epreuve> epreuves;
    private Map<EpreuveLigne, Epreuve> epreuvesMap = new HashMap<>();

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
                init(epreuves);
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
                init(epreuves);
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
                    init(epreuves);
                } else {
                    popUp = new PopUp<>(PopUpType.ERREUR, "Erreur", "Sexe invalide. Veuillez entrer 'H' pour Homme ou 'F' pour Femme.");
                    popUp.showAndWait();
                }
            }
        }
    }
    public void ajouterAthlete(){
        if(selectionner()){
            final ApplicationJO application = this.application;
            final Collection<Athlete> li = new ArrayList<Athlete>();
            Epreuve e = epreuvesMap.get(table.getSelectionModel().getSelectedItem());
            for(Athlete a : ApplicationJO.PROVIDER.getManager().getAthletes()){
                if(!e.getParticipants().contains(a)){
                    li.add(a);
                }
            }
            final Collection<Epreuve> listeMax = this.epreuves;
            Task<PageSelectionAthlete> task = new Task<PageSelectionAthlete>() {
                protected PageSelectionAthlete call() throws Exception {
                    return new PageSelectionAthlete(application, li, true, (liste) -> {
                        for(Athlete a : liste){
                            Epreuve e = epreuvesMap.get(table.getSelectionModel().getSelectedItem());
                            e.ajouteAthlete(a);
                            a.ajouteEpreuve(e);
                        }
                        init(listeMax);
                    });
                };
            };
            application.afficherPage(task, "Création des athlètes", "Veuillez patienter...");
        }
    }
    public void supprimerAthlete(){
        if(selectionner()){
            final ApplicationJO application = this.application;
            final Collection<Athlete> li = new ArrayList<Athlete>();
            Epreuve e = epreuvesMap.get(table.getSelectionModel().getSelectedItem());
            for(Athlete a : ApplicationJO.PROVIDER.getManager().getAthletes()){
                if(e.getParticipants().contains(a)){
                    li.add(a);
                }
            }
            final Collection<Epreuve> listeMax = this.epreuves;
            Task<PageSelectionAthlete> task = new Task<PageSelectionAthlete>() {
                protected PageSelectionAthlete call() throws Exception {
                    return new PageSelectionAthlete(application, li, true, (liste) -> {
                        for(Athlete a : liste){
                            Epreuve e = epreuvesMap.get(table.getSelectionModel().getSelectedItem());
                            e.retireAthlete(a);
                            a.retireEpreuve(e);
                        }
                        init(listeMax);
                    });
                };
            };
            application.afficherPage(task, "Création des athlètes", "Veuillez patienter...");
        }
    }
    public void supprimerEpreuve(){
        if(selectionner()){
            Epreuve e = epreuvesMap.get(table.getSelectionModel().getSelectedItem());
            Optional<ButtonType> res = new PopUp<ButtonType>(PopUpType.CONFIRMATION, "Attention !", "Voulez-vous vraiment supprimer cette épreuve?").showAndWait();
            res.ifPresent((bt)->{
                if(bt == ButtonType.OK){
                    System.out.println("supprimer");
                    for(Athlete a : e.getParticipants()){
                        a.retireEpreuve(e);
                    }
                    System.out.println(ApplicationJO.PROVIDER.getManager().getEpreuves().size());
                    ApplicationJO.PROVIDER.getManager().getEpreuves().remove(e);
                    epreuves.remove(e);
                    System.out.println(ApplicationJO.PROVIDER.getManager().getEpreuves().size());
                    init(epreuves);
                }
            });
        }
    }
    private boolean selectionner(){
        EpreuveLigne el = table.getSelectionModel().getSelectedItem();
        if(el == null){
            new PopUp<>(PopUpType.INFORMATION, "Erreur", "Veuillez sélectionner une épreuve").showAndWait();
            return false;
        }
        return true;
    }
}
