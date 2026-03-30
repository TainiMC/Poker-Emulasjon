package blackjack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppController {

    @FXML private Button quitGameButton;
    @FXML private BorderPane borderPaneMenu;
    @FXML private BorderPane borderPaneGame;
    @FXML private BorderPane borderPaneSettings;

    @FXML
    public void StartGame(ActionEvent event) {
        borderPaneMenu.setVisible(false);
        borderPaneGame.setVisible(true);
        borderPaneSettings.setVisible(false);
    }

    @FXML
    public void Settings(ActionEvent event) {
        borderPaneMenu.setVisible(false);
        borderPaneGame.setVisible(false);
        borderPaneSettings.setVisible(true);
    }

    @FXML
    public void Menu(ActionEvent event) {
        borderPaneMenu.setVisible(true);
        borderPaneGame.setVisible(false);
        borderPaneSettings.setVisible(false);
    }

    @FXML
    public void quitGame(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Quit Game");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.setContentText("Probably only one bet away from a million...");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) borderPaneMenu.getScene().getWindow();
            stage.close();
        }
    }
}
