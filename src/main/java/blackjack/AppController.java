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
    @FXML private Button continueButton;
    
    @FXML private Label dealerLabel;
    @FXML private Label winsLabel;
    @FXML private Label lossesLabel;
    @FXML private Label chipsLabel;
    
    @FXML private ImageView playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6;
    @FXML private ImageView dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6;

    private ImageView[] dealerCards;
    private ImageView[] playerCards;

    private Table table;
    private Player player;

    private String textureName = "TWI SINS";

    private int decks = 4;



    public void initialize() {
        playerCards = new ImageView[]{playerCard1, playerCard2, playerCard3, playerCard4, playerCard5, playerCard6};
        dealerCards = new ImageView[]{dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6};
    }

    @FXML
    public void startNewGame(ActionEvent event) {
        player = new Player(500);
        SaveManager.save(player);
        winsLabel.setText("Wins: " + player.getWins());
        lossesLabel.setText("Losses: " + player.getLosses());
        chipsLabel.setText("Chips: " + player.getChips()+"$");
        
        hitButton.setVisible(false);
        standButton.setVisible(false);
        mainMenuButton.setVisible(true);
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
    public void settings(ActionEvent event) {
        borderPaneMenu.setVisible(false);
        borderPaneGame.setVisible(false);
        borderPaneSettings.setVisible(true);
    }

    @FXML
    public void menu(ActionEvent event) {
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
/*         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Back to Main Menu");
        alert.setHeaderText("Save and go back to Main Menu?");
        alert.setContentText("Pressing ok will save the game and bring you back to the main menu");

        if (alert.showAndWait().get() == ButtonType.OK) { */
            SaveManager.save(player);
            borderPaneMenu.setVisible(true);
            borderPaneGame.setVisible(false);
            borderPaneSettings.setVisible(false);
            borderPaneGameOver.setVisible(false);
        //}
    }

    @FXML
    public void start(ActionEvent event) {
        table = new Table(List.of(player), decks);
        table.initGame();

        startButton.setVisible(false);
        hitButton.setVisible(true);
        standButton.setVisible(true);
        dealerLabel.setVisible(true);
        mainMenuButton.setVisible(false);

        updateDisplay();
    }

    @FXML
    public void Continue(ActionEvent event) {
        int[] data = SaveManager.load();
        player = new Player(data[0]);
        player.setWins(data[1]);
        player.setLosses(data[2]);

        mainMenuButton.setVisible(true);
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

    private void updateDisplay() {
        updateCardRow(player.getHand(), playerCards);
        updateCardRow(table.getDealerHand(), dealerCards);
        winsLabel.setText("Wins: " + player.getWins());
        lossesLabel.setText("Losses: " + player.getLosses());
        chipsLabel.setText("Chips: " + player.getChips()+"$");
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
                    PauseTransition pause = new PauseTransition(Duration.millis(400* (index + 1)));
                    pause.setOnFinished(e -> {views[index].setVisible(true);}); 
                    pause.play();
                    }
            }     
            else {
                views[i].setVisible(false);
            }
        }
    hitButton.setDisable(false);
    }
    
    

    @FXML
    public  void  onHit(ActionEvent event) {
        hitButton.setDisable(true);
        player.hit(table.getTableDeck());

        if (player.checkBust()) {
            int playerLastCard = 500 + 400 * player.getHand().size();
            PauseTransition pause = new PauseTransition(Duration.millis(playerLastCard));
            pause.setOnFinished(e -> {
                updateDisplay();
                String result = table.showResult();
                restartButton.setText(result);
                restartButton.setVisible(true);
                borderPaneGameOver.setVisible(true);
                mainMenuButton.setVisible(true);
                SaveManager.save(player);
            }); 
            pause.play();
        }
        else { updateDisplay(); }
    }

    @FXML
    public void onStand(ActionEvent event) {
        hitButton.setVisible(false);
        standButton.setVisible(false);

        table.dealerDraw();
        String result = table.showResult();
        updateDisplay();
        SaveManager.save(player);
        int dealersLastCard = 500 + 400 * table.getDealerHand().size();
        PauseTransition pause = new PauseTransition(Duration.millis(dealersLastCard));
        pause.setOnFinished(e -> {
            restartButton.setText(result);
            restartButton.setVisible(true);
            borderPaneGameOver.setVisible(true);
            mainMenuButton.setVisible(true);
        }); 
        pause.play();
    }


    //Cards Textures
    @FXML
    public void cards2020(ActionEvent event) { this.textureName = "2020"; }

    @FXML
    public void cardsAtlantis(ActionEvent event) { this.textureName = "Atlantis"; }

    @FXML
    public void cardsDeadPool(ActionEvent event) { this.textureName = "DeadPool"; }

    @FXML
    public void cardsGemini(ActionEvent event) { this.textureName = "Gemini Game Over"; }

    @FXML
    public void cardsKetchup(ActionEvent event) { this.textureName = "Ketchup"; }

    @FXML
    public void cardsTheOffice(ActionEvent event) { this.textureName = "The Office"; }

    @FXML
    public void cardsTwiSins(ActionEvent event) { this.textureName = "TWI SINS"; }

    //Amount of decks
    @FXML
    public void decks1(ActionEvent event) { decks = 1; }

    @FXML
    public void decks2(ActionEvent event) { decks = 2; }

    @FXML
    public void decks3(ActionEvent event) { decks = 3; }

    @FXML
    public void decks4(ActionEvent event) { decks = 4; }

    @FXML
    public void decks5(ActionEvent event) { decks = 5; }

    @FXML
    public void decks6(ActionEvent event) { decks = 6; }

    @FXML
    public void decks7(ActionEvent event) { decks = 7; }

    @FXML
    public void decks8(ActionEvent event) { decks = 8; }
}
