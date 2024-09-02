package frontend;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import frontend.models.TrainingSession;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TrainingSessionController implements Initializable {
    @FXML
    private Button add;
    
    @FXML
    private Button update;
    
    @FXML
    private Button delete;

    @FXML
    private Button back;

    @FXML
    private TableColumn<TrainingSession, String> sportColumn;

    
    @FXML
    private TableColumn durationColumn;

    @FXML
    private TableColumn<TrainingSession, String> trainerColumn;

    
    @FXML
    private TableColumn stateColumn;

    
    @FXML
    private TableColumn dateColumn;

    @FXML
    private TableView<TrainingSession> trainingSessionstbl;

    private ObservableList<TrainingSession> trainingSessions;

    @FXML
    void addTrainingSession(ActionEvent event) {
        if (event.getSource().equals(add)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/DialogTrainingSession.fxml"));
                Parent root = loader.load();
    
                DialogTrainingSessionController controller = loader.getController();
                controller.init(trainingSessions);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
    
                TrainingSession s = controller.getTrainingSession();
                
                if(s != null) {
                    this.trainingSessions.add(s);
                    this.trainingSessionstbl.setItems(trainingSessions);
                    trainingSessionstbl.refresh();
                    s.save(trainingSessions);
                }
    
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                System.out.println(e);
            }
        }
        
    }

    @FXML
    void updateTrainingSession(ActionEvent event) {
        if (event.getSource().equals(update)) {
            TrainingSession s = this.trainingSessionstbl.getSelectionModel().getSelectedItem();
    
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Select a training session");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/DialogTrainingSession.fxml"));
                Parent root = loader.load();

                DialogTrainingSessionController controller = loader.getController();
                controller.init(trainingSessions, s);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                TrainingSession aux = controller.getTrainingSession();
                
                if(aux != null) {
                    aux.save(trainingSessions);
                    trainingSessionstbl.refresh();
                }

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
        }
    }

    @FXML
    void deleteTrainingSession(ActionEvent event) {
        if (event.getSource().equals(delete)) {
            TrainingSession s = this.trainingSessionstbl.getSelectionModel().getSelectedItem();
    
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Select a training session");
            alert.showAndWait();
        } else {
            this.trainingSessions.remove(s);
            this.trainingSessionstbl.refresh();
            s.save(trainingSessions);
        }
        }
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        trainingSessions = FXCollections.observableArrayList();
        this.trainingSessionstbl.setItems(trainingSessions);

        // Deserializar la lista de deportes desde el archivo
        List<TrainingSession> listaS = Serialize.deserializeList(Serialize.daoRoute()+ "trainingSessions.txt", TrainingSession.class);

        // Verificar si la lista deserializada es nula o vacía
        if (listaS != null && !listaS.isEmpty()) {
            // Filtrar las sesiones que no estén completadas y agregarlas a la lista observable
            this.trainingSessions.addAll(
                listaS.stream()
                    .filter(trainingSession -> !trainingSession.isFinalized())  // Filtrar sesiones no completadas
                    .collect(Collectors.toList())              // Convertir de vuelta a lista
            );
        }
        
        this.dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        this.stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        // Configurar las columnas
        this.sportColumn.setCellValueFactory(cellData -> {
            TrainingSession trainingSession = cellData.getValue();
            if (trainingSession.getSport() != null) {
                return new SimpleStringProperty(trainingSession.getSport().getName());
            } else {
                return new SimpleStringProperty("Sin deporte");
            }
        });
        this.trainerColumn.setCellValueFactory(cellData -> {
            TrainingSession trainingSession = cellData.getValue();
            if (trainingSession.getTrainer() != null) {
                return new SimpleStringProperty(trainingSession.getTrainer().getName());
            } else {
                return new SimpleStringProperty("None");
            }
        });
    }

    @FXML
    private void loadStage(String url, Event event){
        try {
            Window window = ((javafx.scene.Node)(event.getSource())).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage newStage = (Stage) window;
            newStage.setScene(scene);
            newStage.show();
            
        } catch (Exception e) {
            new Exception("Error al cambiar de escena");
        }
    }    

    @FXML
    public void backOnAction(@SuppressWarnings("exports") ActionEvent event) throws IOException{
        loadStage("admin.fxml", event);
    }
}
