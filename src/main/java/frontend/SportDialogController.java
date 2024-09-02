package frontend;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import frontend.models.Difficulty;
import frontend.models.Sport;
import frontend.models.Trainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class SportDialogController implements Initializable{
    @FXML
    private Button accept;

    @FXML
    private Button cancel;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField descriptionTF;
    
    
    @FXML
    private ComboBox difficultyBox;

    
    @FXML
    private ComboBox trainerBox;

    private Sport sport;
    private ObservableList<Sport> sports;

    private ObservableList<Trainer> trainers = FXCollections.observableArrayList();

    @FXML
    void accept(ActionEvent event) {

        String name = this.nameTF.getText();
        String description = this.descriptionTF.getText();
        Difficulty difficulty = (Difficulty)this.difficultyBox.getSelectionModel().getSelectedItem();
        Trainer trainer = (Trainer)this.trainerBox.getSelectionModel().getSelectedItem();

        Sport s = new Sport(name, description, difficulty, trainer);

        if(!sports.contains(s)) {

            if(this.sport != null) {
                this.sport.setName(name);
                this.sport.setDescription(description);
                this.sport.setDifficulty(difficulty);
                this.sport.setTrainer(trainer);

            } else {
                this.sport = s;
            }

            Stage stage = (Stage) this.accept.getScene().getWindow();
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("This sport already exists!!");
            alert.showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        this.sport = null;
        Stage stage = (Stage) this.cancel.getScene().getWindow();
        stage.close();
    }

    public void init(ObservableList<Sport> sports) {
        this.sports = sports;
    }

    @SuppressWarnings("unchecked")
    public void init(ObservableList<Sport> sports, Sport d) {
        this.sports = sports;
        this.sport = d;
        this.nameTF.setText(d.getName());
        this.descriptionTF.setText(d.getDescription());
        this.difficultyBox.setValue(d.getDifficulty());
    }

    public Sport getSport() {
        return this.sport;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
 
    ObservableList<Difficulty> list = FXCollections.observableArrayList(Difficulty.values());
    difficultyBox.setItems(list);
        
    List<Trainer> listE = Serialize.deserializeList(Serialize.daoRoute()+"trainers.txt", Trainer.class);
    trainers.setAll(listE);
    trainerBox.setItems(trainers);
   
    trainerBox.setConverter(new StringConverter<Trainer>() {
        @Override
        public String toString(Trainer Trainer) {
            return Trainer != null ? Trainer.getName() : "";
        }

        @Override
        public Trainer fromString(String nombre) {
            return null;
        }
    });
    }

}
