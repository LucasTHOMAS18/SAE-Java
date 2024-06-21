package fr.aftek.ihm.controleurs;

import java.io.File;
import java.util.Optional;

import fr.aftek.data.DataProvider;
import fr.aftek.ihm.ApplicationJO;
import fr.aftek.ihm.pages.PageClassementEquipes;
import fr.aftek.ihm.pages.PopUp;
import fr.aftek.ihm.pages.PageModifier.TypeModification;
import fr.aftek.ihm.pages.PopUp.PopUpType;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

public class ControleurAdmin extends Controleur{
    @FXML
    private Button btnChargerCSV;
    @FXML
    private Button btnShowUsers;
    @FXML
    private Button btnExportData;
    @FXML
    private Button btnAjouterData;
    @FXML
    private Button btnModifierData;
    @FXML
    private ToggleGroup modif;
    @FXML
    private ToggleGroup ajout;
    
    public ControleurAdmin(ApplicationJO appli) {
        this.application = appli;
    }

    @FXML
    private void chargerCSV() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT","*.txt"));
        File file = chooser.showOpenDialog(application.getStage());
        if(file!= null) {
            final DataProvider provider = ApplicationJO.PROVIDER;
            Task<Void> task = new Task<Void>() {
                protected Void call() throws Exception {
                    provider.loadCSV(file.getAbsolutePath());
                    return null;
                };
            };
            PopUp<ButtonType> popUp = new PopUp<>(PopUpType.PROGRESS, "Chargement", "Chargement des données...");
            task.setOnSucceeded((wse)->{
                popUp.close();
                Platform.runLater(() -> {
                new PopUp<>(PopUpType.INFORMATION, "Succès !" , "Le chargement des données à été effectué !").showAndWait();
                });
            });
            task.setOnFailed((wse)->{
                popUp.close();
                wse.getSource().getException().printStackTrace();
            });
            Thread th = new Thread(task,"Progress");
            th.start();
            Optional<ButtonType> res = popUp.showAndWait();
            res.ifPresent((e)-> {
                if(e == ButtonType.CANCEL){
                    th.interrupt();
                }
            });
        }
    }

    @FXML
    private void showUsers() {
    }

    @FXML
    private void exportData() {
    }

    @FXML
    private void ajouter() {
    }

    @FXML
    private void modifier() {
        String text = ((RadioButton) modif.getSelectedToggle()).getText();
        if(text.equals("Epreuve")){
            application.modifier(TypeModification.EPREUVE);
        }else if(text.equals("Athlete")){
            application.modifier(TypeModification.ATHLETE);
        }
    }
}
