package fr.aftek.ihm.controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import fr.aftek.NomSport;
import fr.aftek.ihm.ApplicationJO;

public class ControleurChoixSport extends Controleur{
    @FXML
    private ToggleButton btnHomme;
    @FXML
    private ToggleButton btnFemme;
    @FXML
    private ToggleGroup sexe;
    public ControleurChoixSport(ApplicationJO appli) {
        this.application = appli;
    }
    @FXML
    public void retourAcceuil(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void select110mHaies(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        this.choix(NomSport.ATHLETISME, this.btnFemme.isSelected() ? 'F' : 'M');
    }
    @FXML
    public void select4x100mRelais(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        this.choix(NomSport.ATHLETISME_RELAIS, this.btnFemme.isSelected() ? 'F' : 'M');
    }
    @FXML
    public void selectHandball(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        this.choix(NomSport.HANDBALL, this.btnFemme.isSelected() ? 'F' : 'M');
    }
    @FXML
    public void selectVolleyball(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        this.choix(NomSport.VOLLEY_BALL, this.btnFemme.isSelected() ? 'F' : 'M');
    }
    @FXML
    public void selectNatation100mBrasse(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        this.choix(NomSport.NATATION, this.btnFemme.isSelected() ? 'F' : 'M');
    }
    @FXML
    public void selectNatation4x100m(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        this.choix(NomSport.NATATION_RELAIS, this.btnFemme.isSelected() ? 'F' : 'M');
    }
    @FXML
    public void selectEscrimeEpee(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        this.choix(NomSport.ESCRIME_EPEE, this.btnFemme.isSelected() ? 'F' : 'M');
    }
    @FXML
    public void selectEscrimeFleuret(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        this.choix(NomSport.ESCRIME_FLEURET, this.btnFemme.isSelected() ? 'F' : 'M');
    }
    public void init() {
        sexe.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
    }

    private void choix(NomSport sport, char sexe){
        System.out.println(sport + " " + sexe);
    }
}
