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

    @FXML private ImageView playerCard1, playerCard2, playerCard3, playerCard4, playerCard5;
    @FXML private ImageView dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5;

    private Table table;
    private Player player;
    private ImageView[] playerCards;
    private ImageView[] dealerCards;

    public void initialize() {
        playerCards = new ImageView[]{playerCard1, playerCard2, playerCard3, playerCard4, playerCard5};
        dealerCards = new ImageView[]{dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5};
    }

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
        player = new Player(500);
        table = new Table(List.of(player));
        table.initGame();

        startButton.setVisible(false);
        hitButton.setVisible(true);
        standButton.setVisible(true);
        dealerLabel.setVisible(true);
        table.initGame();

        updateDisplay();
    }

    private void updateDisplay() {
        updateCardRow(player.getHand(), playerCards);
        updateCardRow(table.getDealerHand(), dealerCards);
    }

    private void updateCardRow(List<Card> hand, ImageView[] views) {
        for (int i = 0; i < views.length; i++) {
            if (i < hand.size()) {
                Card card = hand.get(i);
                String path = "/blackjack/textures/2020/" + card.getCardString() + ".jpg";
                Image img = new Image(getClass().getResourceAsStream(path));
                views[i].setImage(img);
                views[i].setVisible(true);
            }     else {
                views[i].setVisible(false);
            }
        }
    } 

    @FXML
    public void onHit(ActionEvent event) {

    }

    @FXML
    public void onStand(ActionEvent event) {
        
    }
}
