package frontend;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import frontend.models.Sport;
import frontend.models.Trainer;
import frontend.models.TrainingSession;
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

public class DialogTrainerController implements Initializable{

   @FXML
    private Button accept;

    @FXML
    private Button cancel;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox specializationBox;

    @FXML
    private TextField nameTF;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox trainingSessionsBox;

    private Trainer trainer;
    @SuppressWarnings("unused")
    private ObservableList<TrainingSession> sessions;

    private ObservableList<Trainer> trainers;

    private ObservableList<Sport> sports = FXCollections.observableArrayList();


    @FXML
    void accept(ActionEvent event) {

        String name = this.nameTF.getText();
        Sport sport = (Sport) this.specializationBox.getSelectionModel().getSelectedItem();
        TrainingSession session = (TrainingSession) this.trainingSessionsBox.getSelectionModel().getSelectedItem();

        Trainer t = new Trainer(name, sport);

        if(!trainers.contains(t)) {

            if(this.trainer != null) {
                this.trainer.setName(name);
                this.trainer.setSpecialization(sport);
                this.trainer.setTrainingSession(session);
            } else {
                this.trainer = t;
            }

            Stage stage = (Stage) this.accept.getScene().getWindow();
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("The trainer already exists!!");
            alert.showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        this.trainer = null;
        Stage stage = (Stage) this.cancel.getScene().getWindow();
        stage.close();
    }

    public void init(ObservableList<Trainer> trainers) {
        this.trainers = trainers;
    }

    @SuppressWarnings("unchecked")
    public void init(ObservableList<Trainer> trainers, Trainer t) {
        this.trainers = trainers;
        this.trainer = t;
        this.nameTF.setText(t.getName());
        this.specializationBox.setValue(t.getSpecialization().getName());
        this.trainingSessionsBox.setValue(t.getTrainingSession());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Deserializar la lista de deportes
        List<Sport> listaD = Serialize.deserializeList(Serialize.daoRoute()+"sports.txt", Sport.class);
        sports.setAll(listaD); 

        // Configurar el ComboBox con la lista observable
        specializationBox.setItems(sports);

        // Configurar un StringConverter para mostrar los nombres de los deportes en el ComboBox
        specializationBox.setConverter(new StringConverter<Sport>() {
        @Override
        public String toString(Sport sport) {
            // Verifica si el deporte es null antes de acceder a su nombre
            return sport != null ? sport.getName() : "";//Convierte el entrenador en string
        }

        @Override
        public Sport fromString(String name) {
            // el ComboBox ya sabe internamente qué objeto fue seleccionado, por lo que no se requiere una conversión desde texto (String) a objeto (Deporte).
            return null;
        }
    });
    }

    public Trainer getrainer() {
        return trainer;
    }
    
}
