package fr.aftek.ihm.controleurs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class ControleurChoixSport {
    @FXML
    private ToggleButton btnHomme;
    @FXML
    private ToggleButton btnFemme;
    @FXML
    private Button btn110mHaies;
    @FXML
    private Button btn4x100mRelais;
    @FXML
    private Button btnHandball;
    @FXML
    private Button btnVolleyball;
    @FXML
    private Button btnNatation100mBrasse;
    @FXML
    private Button btnNatation4x100m;
    @FXML
    private Button btnEscrimeEpee;
    @FXML
    private Button btnEscrimeFleuret;
    @FXML
    private Button btnRetour;
    @FXML
    private ToggleGroup sexe;
    public ControleurChoixSport(){
        
    }
    @FXML
    public void retourAcceuil(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void changeToHomme(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void changeToFemme(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void select110mHaies(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void select4x100mRelais(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void selectHandball(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void selectVolleyball(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void selectNatation100mBrasse(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void selectNatation4x100m(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void selectEscrimeEpee(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @FXML
    public void selectEscrimeFleuret(){
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
    public void init() {
        sexe.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal == null)
                oldVal.setSelected(true);
        });
    }


}
