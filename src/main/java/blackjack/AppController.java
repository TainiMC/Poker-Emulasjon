package blackjack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.List;

public class AppController {

    @FXML private BorderPane borderPaneMenu;
    @FXML private BorderPane borderPaneGame;
    @FXML private BorderPane borderPaneSettings;

    @FXML private Button quitGameButton;
    @FXML private Button startButton;
    @FXML private Button hitButton;
    @FXML private Button standButton;

    @FXML private Label dealerLabel;

    

    @FXML
    public void StartNewGame(ActionEvent event) {
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

    @FXML
    public void Start(ActionEvent event) {
        table.initGame();

        startButton.setVisible(false);
        hitButton.setVisible(true);
        standButton.setVisible(true);
        dealerLabel.setVisible(true);

    }


    @FXML
    public void onHit(ActionEvent event) {

    }

    @FXML
    public void onStand(ActionEvent event) {
        
    }
}
