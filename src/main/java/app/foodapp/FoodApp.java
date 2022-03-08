package app.foodapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import recipe.JsonReader;

import java.util.Objects;

public class FoodApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        JsonReader.reader();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/app/foodapp/view/main.fxml")));
        primaryStage.setTitle("BEAT IT");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
