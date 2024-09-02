package frontend;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import frontend.models.Sport;
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

public class SportController implements Initializable{

    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button back;
  
    @FXML
    private TableColumn <Sport, String> name;
    
    @FXML
    private TableColumn <Sport, String> description;

    @FXML
    private TableColumn <Sport, String> difficulty;

    @FXML
    private TableColumn<Sport, String> trainerColumn;
    
    @FXML
    private TableView<Sport> sportstbl;

    private ObservableList<Sport> sports;

    @FXML
    void addSport(ActionEvent event) {
            try {
                System.out.println("hola");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/sportDialog.fxml"));
                Parent root = loader.load();

                SportDialogController controller = loader.getController();
                controller.init(sports);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Sport d = controller.getSport();

                if (d != null) {
                    this.sports.add(d);
                    this.sportstbl.setItems(sports);
                    sportstbl.refresh();
                    d.save(this.sports);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                System.out.println(e);
            }
    }

    @FXML
    void updateSport(ActionEvent event) {
        if (event.getSource().equals(update)) {
            Sport d = this.sportstbl.getSelectionModel().getSelectedItem();

            if (d == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("Select a sport");
                alert.showAndWait();
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/sportDialog.fxml"));
                    Parent root = loader.load();

                    SportDialogController controller = loader.getController();
                    controller.init(sports, d);

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();

                    Sport aux = controller.getSport();

                    if (aux != null) {
                        aux.save(this.sports);
                        sportstbl.refresh();
                    }

                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("ERROR");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        }

    }

    @FXML
    void deleteSport(ActionEvent event) {
        if (event.getSource().equals(delete)) {
            Sport d = this.sportstbl.getSelectionModel().getSelectedItem();

            if (d == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("Select a sport");
                alert.showAndWait();
            } else {
                this.sports.remove(d);
                this.sportstbl.refresh();
                d.save(this.sports);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        sports = FXCollections.observableArrayList();
        this.sportstbl.setItems(sports);

        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.description.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.difficulty.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        
        this.trainerColumn.setCellValueFactory(cellData -> {
            Sport sport = cellData.getValue();
            String trainerName = sport.getTrainer() != null ? sport.getTrainer().getName()
                    : "None";
            return new SimpleStringProperty(trainerName);
        });

        List<Sport> listaD = Serialize.deserializeList(Serialize.daoRoute() + "sports.txt",
                Sport.class);

        // Verificar si la lista deserializada es nula o vacía
        if (listaD != null && !listaD.isEmpty()) {
            sports.addAll(listaD); // Agregar los deportes a la lista observable
        }
    }

    @FXML
    private void loadStage(String url, Event event) {
        try {
            Window window = ((javafx.scene.Node) (event.getSource())).getScene().getWindow();
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
    public void backOnAction(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        loadStage("admin.fxml", event);
    }
}
