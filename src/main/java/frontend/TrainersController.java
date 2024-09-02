package frontend;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import frontend.models.Trainer;
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
import java.net.URL;

public class TrainersController implements Initializable{
    
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button back;
    @FXML
    private TableColumn<Trainer, String> specializationColumn;

    @FXML
    private TableColumn nameColumn;

    @FXML
    private TableColumn trainingSessionsColumn;

    @FXML
    private TableView<Trainer> trainerstbl;

    private ObservableList<Trainer> trainers;
    

    @FXML
    void addTrainer(ActionEvent event) {
        if (event.getSource().equals(add)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogTrainer.fxml"));
                Parent root = loader.load();
    
                DialogTrainerController controller = loader.getController();
                controller.init(trainers);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
    
                Trainer e = controller.getrainer();
                
                if(e != null) {
                    this.trainers.add(e);
                    this.trainerstbl.setItems(trainers);
                    trainerstbl.refresh();
                    e.save(this.trainers);
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
    void updateTrainer(ActionEvent event) {
        if (event.getSource().equals(update)) {
            Trainer e = this.trainerstbl.getSelectionModel().getSelectedItem();
    
        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Select a trainer");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/DialogtrainerController.fxml"));
                Parent root = loader.load();

                DialogTrainerController controller = loader.getController();
                controller.init(trainers, e);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Trainer aux = controller.getrainer();
                
                if(aux != null) {
                    trainerstbl.refresh();
                    e.save(trainers);
                }

            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
        }
        
    }

    @FXML
    void deleteTrainer(ActionEvent event) {
        if (event.getSource().equals(delete)) {
            Trainer e = this.trainerstbl.getSelectionModel().getSelectedItem();
    
        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Select a trainer");
            alert.showAndWait();
        } else {
            this.trainers.remove(e);
            this.trainerstbl.refresh();
            e.save(trainers);
        }
        }
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        trainers = FXCollections.observableArrayList();
        this.trainerstbl.setItems(trainers);
        
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.specializationColumn.setCellValueFactory(cellData -> {
            Trainer trainer = cellData.getValue();
            String nameSpecialization = trainer.getSpecialization() != null ? trainer.getSpecialization().getName() : "None";
            return new SimpleStringProperty(nameSpecialization);
        });
        this.trainingSessionsColumn.setCellValueFactory(new PropertyValueFactory<>("trainingSession"));
        List<Trainer> listaE = Serialize.deserializeList(Serialize.daoRoute()+"trainers.txt", Trainer.class);
        if (listaE != null && !listaE.isEmpty()) {
            trainers.addAll(listaE); 
        }
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
            new Exception("Error in change of window");
        }
    }    

    @FXML
    public void backOnAction(@SuppressWarnings("exports") ActionEvent event) throws IOException{
        loadStage("admin.fxml", event);
    }
}
