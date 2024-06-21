package fr.aftek.ihm.controleurs;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import fr.aftek.Athlete;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.controleurs.ControleurClassementAthlete.AthleteLigne;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ControleurSelectionAthlete extends Controleur{
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
    private Map<AthleteLigne, Athlete> athletesMap;
    private Athlete res = null;
    private Collection<Athlete> resMulti = null;
    private boolean multi;
    private Consumer<Collection<Athlete>> cons;

    public ControleurSelectionAthlete(ApplicationJO appli, boolean multi) {
        this.application = appli;
        this.multi = multi;
    }

    public void init(Collection<Athlete> liste, Consumer<Collection<Athlete>> cons){
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        pays.setComparator((o1, o2) -> o1.getAccessibleText().compareTo(o2.getAccessibleText()));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sport.setCellValueFactory(new PropertyValueFactory<>("sport"));
        this.cons = cons;
        if(multi) table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.athletesMap = new HashMap<>();
        for (Athlete a : liste){
            AthleteLigne al = new AthleteLigne(a.getNom(), a.getPrenom(), a.getSport().getNomSport().getNom(), a.getPays().getNom());
            table.getItems().add(al);
            this.athletesMap.put(al, a);
        }
    }

    public void valider(){
        if(multi){
            ObservableList<AthleteLigne> atl = table.getSelectionModel().getSelectedItems();
            List<Athlete> athletes = atl.stream().map(athletesMap::get).collect(Collectors.toList());
            if (athletes.size() == 0) {
                new PopUp<>(PopUpType.INFORMATION, "Erreur", "Veuillez sélectionner au moins un athlète").showAndWait();
                return;
            }
            resMulti = athletes;
            cons.accept(athletes);
        }else{
            AthleteLigne al = table.getSelectionModel().getSelectedItem();
            Athlete a = athletesMap.get(al);
            if(a == null){
                new PopUp<>(PopUpType.INFORMATION, "Erreur", "Veuillez sélectionner un athlète").showAndWait();
                return;
            }
            res = a;
            cons.accept(List.of(a));
        }
        application.retour();
    }
    public void annuler(){
        application.retour();
    }
    public Athlete getRes() {
        return res;
    }
    public Collection<Athlete> getResMulti(){
        return resMulti;
    }
    public boolean isRes(){
        return multi ? resMulti!=null : res!= null;
    }
}
