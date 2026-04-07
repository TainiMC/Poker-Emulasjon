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
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.List;

public class AppController {

    @FXML private BorderPane borderPaneMenu;
    @FXML private BorderPane borderPaneGame;
    @FXML private BorderPane borderPaneSettings;
    @FXML private BorderPane borderPaneGameOver;



    @FXML private Button quitGameButton;
    @FXML private Button startButton;
    @FXML private Button hitButton;
    @FXML private Button standButton; 
    @FXML private Button restartButton;
    @FXML private Button mainMenuButton;

    @FXML private Label dealerLabel;

    @FXML private ImageView playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6;
    @FXML private ImageView dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6;

    private ImageView[] dealerCards;
    private ImageView[] playerCards;

    private Table table;
    private Player player;

    private String textureName = "TWI SINS";



    public void initialize() {
        playerCards = new ImageView[]{playerCard1, playerCard2, playerCard3, playerCard4, playerCard5};
        dealerCards = new ImageView[]{dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5};
    }

    @FXML
    public void StartNewGame(ActionEvent event) {
        mainMenuButton.setVisible(false);
        borderPaneMenu.setVisible(false);
        borderPaneGame.setVisible(true);
        borderPaneSettings.setVisible(false);
        startButton.setVisible(true);
        for (ImageView card : playerCards) {
            card.setVisible(false);
        }
        for (ImageView card : dealerCards) {
            card.setVisible(false);
        }
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
    public void restart(ActionEvent event) {
        borderPaneGame.setVisible(true);
        mainMenuButton.setVisible(false);
        startButton.setVisible(true);
        borderPaneGameOver.setVisible(false);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        for (ImageView card : playerCards) {
            card.setVisible(false);
        }
        for (ImageView card : dealerCards) {
            card.setVisible(false);
        }
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
    public void backToMainMenu(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Back to Main Menu");
        alert.setHeaderText("Save and go back to Main Menu?");
        alert.setContentText("Pressing ok will save the game and bring you back to the main menu");

        if (alert.showAndWait().get() == ButtonType.OK) {

            borderPaneMenu.setVisible(true);
            borderPaneGame.setVisible(false);
            borderPaneSettings.setVisible(false);
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
        mainMenuButton.setVisible(true);

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
                String cardTexture;
                if (card.getVisibility()) {
                cardTexture = "/blackjack/textures/" + this.textureName + "/" + card.getCardString() + ".jpg";
                }
                else {
                    cardTexture = "/blackjack/textures/" + this.textureName + "/CB.jpg";
                }
                Image img = new Image(getClass().getResourceAsStream(cardTexture));
                views[i].setImage(img);
                if (!views[i].isVisible()) {
                    final int index = i;
                    PauseTransition pause = new PauseTransition(Duration.millis(600));
                    pause.setOnFinished(e -> {views[index].setVisible(true);}); 
                    pause.play();
                    }
            }     
            else {
                views[i].setVisible(false);
            }
        }
    }
    
    

    @FXML
    public  void  onHit(ActionEvent event) {
        player.hit(table.getTableDeck());

        updateDisplay();

        if (player.checkBust()) {
            dealerLabel.setVisible(false);
            restartButton.setText(table.showResult());
            restartButton.setVisible(true);
            borderPaneGameOver.setVisible(true);
            mainMenuButton.setVisible(false);   
        }
    }

    @FXML
    public void onStand(ActionEvent event) {
        mainMenuButton.setVisible(false);   
        hitButton.setVisible(false);
        standButton.setVisible(false);

        table.dealerDraw();
        updateDisplay();
        
        restartButton.setText(table.showResult());
        restartButton.setVisible(true);
        borderPaneGameOver.setVisible(true);
    }



    @FXML
    public void cards2020(ActionEvent event) {
        this.textureName = "2020";
    }

    @FXML
    public void cardsAtlantis(ActionEvent event) {
        this.textureName = "Atlantis";
    }

    @FXML
    public void cardsDeadPool(ActionEvent event) {
        this.textureName = "DeadPool";
    }

    @FXML
    public void cardsGemini(ActionEvent event) {
        this.textureName = "Gemini Game Over";
    }

    @FXML
    public void cardsKetchup(ActionEvent event) {
        this.textureName = "Ketchup";
    }

    @FXML
    public void cardsTheOffice(ActionEvent event) {
        this.textureName = "The Office";
    }

    @FXML
    public void cardsTwiSins(ActionEvent event) {
        this.textureName = "TWI SINS";
    }
}
