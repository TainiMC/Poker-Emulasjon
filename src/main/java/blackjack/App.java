package blackjack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Press the button to draw a card!");

        Button drawButton = new Button("Draw Card");

        Random random = new Random();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        // This runs every time the button is clicked
        drawButton.setOnAction(event -> {
            String suit = suits[random.nextInt(suits.length)];
            String value = values[random.nextInt(values.length)];
            label.setText("You drew: " + value + " of " + suit);
        });

        VBox layout = new VBox(20, label, drawButton); // vertical layout, 20px spacing
        layout.setStyle("-fx-padding: 40; -fx-alignment: center;");

        Scene scene = new Scene(layout, 800, 600);
        stage.setTitle("Poker Emulasjon");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
