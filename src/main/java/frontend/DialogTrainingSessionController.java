package frontend;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import frontend.models.Sport;
import frontend.models.StateSession;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DialogTrainingSessionController implements Initializable{
    
    @FXML
    private Button accept;

    @FXML
    private Button cancel;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox sportComboBox;

    @FXML
    private TextField durationTF;

    @SuppressWarnings("rawtypes")
    @FXML
    private ComboBox trainerComboBox;

    @FXML
    private DatePicker datePicker;

    private TrainingSession trainingSession;
    private ObservableList<TrainingSession> trainingSessions;

    private ObservableList<Sport> sports = FXCollections.observableArrayList();
    private ObservableList<Trainer> trainers = FXCollections.observableArrayList();

    @FXML
    void accept(ActionEvent event) {

        LocalDate date = this.datePicker.getValue();
        int duration = Integer.parseInt(durationTF.getText());
        StateSession state = StateSession.AVAILABLE;
        Sport sport = (Sport) this.sportComboBox.getSelectionModel().getSelectedItem();
        Trainer trainer = (Trainer) this.trainerComboBox.getSelectionModel().getSelectedItem();

        TrainingSession s = new TrainingSession(date, duration, state, sport, trainer);

        if(!trainingSessions.contains(s)) {
    
            if(this.trainingSession != null) {
                this.trainingSession.setDate(date);
                this.trainingSession.setDuration(duration);
                this.trainingSession.setSport(sport);
                this.trainingSession.setTrainer(trainer);

            } else {
                this.trainingSession = s;
            }

            Stage stage = (Stage) this.accept.getScene().getWindow();
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("The trainingSession already exists!!");
            alert.showAndWait();
        }
    }

    @FXML
    void cancel(ActionEvent event) {

        this.trainingSession = null;
        Stage stage = (Stage) this.cancel.getScene().getWindow();
        stage.close();
    }

    //Con este método mando todas las trainingSessiones a esta clase para comprobar que no se repita
    public void init(ObservableList<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }

    //Pone la sesión seleccionada en los campos de texto
    public void init(ObservableList<TrainingSession> trainingSessions, TrainingSession s) {
        this.trainingSessions = trainingSessions;
        this.trainingSession = s;
        this.datePicker.setValue(s.getDate());
        this.durationTF.setText(s.getDuration() + "");
    }

    public TrainingSession getTrainingSession() {
        return this.trainingSession;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        // Deserializar la lista de deportes
        List<Sport> listaD = Serialize.deserializeList(Serialize.daoRoute()+"sports.txt", Sport.class);
        sports.setAll(listaD); 
        // Configurar el TableView con la lista observable
        sportComboBox.setItems(sports);
        
        // Configurar un StringConverter para mostrar los nombres de los deportes en el ComboBox
        sportComboBox.setConverter(new StringConverter<Sport>() {
            @Override
            public String toString(Sport sport) {
                // Verifica si el deporte es null antes de acceder a su nombre
                return sport != null ? sport.getName() : "";//Convierte el entrenador en string
            }
            @Override
            public Sport fromString(String nombre) {
                // el ComboBox ya sabe internamente qué objeto fue seleccionado, por lo que no se requiere una conversión desde texto (String) a objeto (Deporte).
                return null;
            }
        });

        // Deserializar la lista de entrenadores desde el archivo
        List<Trainer> listaE = Serialize.deserializeList(Serialize.daoRoute()+"trainers.txt", Trainer.class);
        trainers.setAll(listaE);

            // Configurar el ComboBox tfEntrenador con la lista observable de entrenadores
        trainerComboBox.setItems(trainers);

        // Configurar un StringConverter para mostrar los nombres de los entrenadores en el ComboBox
        trainerComboBox.setConverter(new StringConverter<Trainer>() {
            @Override
            public String toString(Trainer trainer) {
                // Verifica si el entrenador es null antes de acceder a su nombre
                return trainer != null ? trainer.getName() : "";//Convierte el entrenador en string
            }

            @Override
            public Trainer fromString(String name) {
                // el ComboBox ya sabe internamente qué objeto fue seleccionado, por lo que no se requiere una conversión desde texto (String) a objeto (Entrenador).
                return null;
            }
        });
    }
}
