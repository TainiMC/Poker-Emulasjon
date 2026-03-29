package blackjack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) { //the "stage" is our main window the program is running in
        try {
            Parent root = FXMLLoader.load(getClass().getResource("App.fxml")); //This connects the App.java to the App.fxml fil
            Scene scene = new Scene(root); //This makes the "scene"
            Image icon = new Image(getClass().getResourceAsStream("/blackjack/stageIcon.jpg")); //This is the Image made into the Icon when the app is running.

            String css = this.getClass().getResource("App.css").toExternalForm();
            scene.getStylesheets().add(css);
            

            
            stage.getIcons().add(icon); //This sets the icon
            stage.setTitle("BLACKJACK"); //This is the Title of the game
            stage.setFullScreen(true); //Sets the screen to full screen on lanch
            stage.setScene(scene); //sets the scene
            stage.show(); //show the sene

            //Calles the quitGame method when clicken the X in top right corner of the app.
            stage.setOnCloseRequest(event -> {
                event.consume();
                quitGame(stage);
            });

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    //Quit game method
    public void quitGame(Stage stage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Quit Game");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.setContentText("Probably only one bet away from a million...");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }



    public static void main(String[] args) { //I could not make this work, just use "mvn javafx:run" in the console instead, or add a jsn file using claude.
        Application.launch(args);
    }
}
