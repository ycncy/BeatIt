package app.foodapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import recipe.JsonReader;

public class FoodApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        JsonReader.reader();
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        primaryStage.setTitle("FoodApp");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
