package blackjack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppController {

    @FXML private Button quitGameButton;
    @FXML private AnchorPane anchorMenu;
    @FXML private AnchorPane anchorGame;
    @FXML private AnchorPane anchorSettings;

    @FXML
    public void StartGame(ActionEvent event) {
        anchorMenu.setVisible(false);
        anchorGame.setVisible(true);
        anchorSettings.setVisible(false);
    }

    @FXML
    public void Settings(ActionEvent event) {
        anchorMenu.setVisible(false);
        anchorGame.setVisible(false);
        anchorSettings.setVisible(true);
    }

    @FXML
    public void Menu(ActionEvent event) {
        anchorMenu.setVisible(true);
        anchorGame.setVisible(false);
        anchorSettings.setVisible(false);
    }

    @FXML
    public void quitGame(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Quit Game");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.setContentText("Probably only one bet away from a million...");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) anchorMenu.getScene().getWindow();
            stage.close();
        }
    }
}
