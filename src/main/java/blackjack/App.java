package blackjack;

import java.security.Key;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, 600, 600,Color.LIGHTSKYBLUE);

        Image test = new Image(getClass().getResourceAsStream("/blackjack/stageIcon.jpg"));

        Text text = new Text();
        text.setText("WOOOAH!");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Verdana",50));
        text.setFill(Color.LIMEGREEN);

        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStrokeWidth(50);
        line.setStroke(Color.RED);
        line.setOpacity(0.5);
        line.setRotate(45);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setWidth(100);
        rectangle.setHeight(100);
        rectangle.setFill(Color.BLUE);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.BLACK);
        rectangle.setRotate(33);

        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
    200.0,200.0,
                300.0,300.0,
                200.0,300.0
                );
        triangle.setFill(Color.GREEN);

        Circle circle = new Circle();
        circle.setCenterX(350);
        circle.setCenterY(350);
        circle.setRadius(50);
        circle.setFill(Color.ORANGE);

        Image image = new Image(getClass().getResourceAsStream("/blackjack/Deadpool/CB.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(400);

        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);
        root.getChildren().add(imageView);
        stage.getIcons().add(test);
        stage.setTitle("BLACKJACK");
        stage.setWidth(420);
        stage.setHeight(420);
        stage.setResizable(false);
        //stage.setX(50);
        //stage.setY(50);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("YOU CAN'T ESCAPE unless you press q");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

        stage.setScene(scene);
        stage.show();
    }


}
