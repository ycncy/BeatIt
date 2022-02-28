package app.foodapp.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import recipe.FavoriteRecipe;
import recipe.JsonReader;
import recipe.Recipe;

import java.net.URL;
import java.util.ResourceBundle;

public class FoodAppController implements Initializable {

    @FXML private Button addIngredientButton;
    @FXML private Button researchButton;
    @FXML private Button addToFavoriteButton;
    @FXML private Button deleteFromFavorite;
    @FXML private Spinner spinner;
    @FXML private TextField ingredientTextField;
    @FXML private Hyperlink recipeName;
    @FXML private Label ingredientsText;
    @FXML private VBox vboxRecipesCenter;
    @FXML private VBox vboxFavorite;

    public void initialize(URL location, ResourceBundle resourceBundle) {
        for(Recipe recipe : FavoriteRecipe.getRecipes()) {
            HBox hbox = new HBox();
            hbox.setLayoutX(10.0);
            hbox.setPrefHeight(25.0);
            hbox.setPrefWidth(244.0);
            hbox.setPadding(new Insets(10,10,10,10));

            Hyperlink hyperlink = new Hyperlink();
            Button button = new Button();

            hyperlink.setText(recipe.getName());
            hyperlink.setPrefHeight(32.0);
            hyperlink.setPrefWidth(156.0);
            hyperlink.setId(recipe.getName());

            button.setText("-");
            button.setMnemonicParsing(false);
            button.setPrefHeight(31.0);
            button.setPrefWidth(31.0);
            button.setId("deleteFromFavorite");

            hbox.getChildren().add(hyperlink);
            hbox.getChildren().add(button);

            vboxFavorite.getChildren().add(hbox);
        }
    }

    @FXML
    private void addIngredient() {
        if(ingredientTextField.getText() != null) {
            if(JsonReader.allIngredients.contains(ingredientTextField.getText())) {
                JsonReader.ingredients.add(ingredientTextField.getText());
                if (ingredientsText.getText().equals("")) {
                    ingredientsText.setText(ingredientTextField.getText());
                } else {
                    ingredientsText.setText(ingredientsText.getText() + ", " + ingredientTextField.getText());
                }
                ingredientTextField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ingredient ERROR");
                alert.setHeaderText("Pas d'ingredient");
                alert.showAndWait();
                ingredientTextField.clear();
            }
        }
    }

    @FXML
    private void displayRecipes() {
        JsonReader.setRecipes(String.valueOf(spinner.getValue()));
        for(Recipe recipe : JsonReader.recipes) {
            //HBOX
            HBox hbox = new HBox();
            hbox.setPrefHeight(0.0);
            hbox.setPrefWidth(517);
            hbox.setPadding(new Insets(10,10,10,10));

            //HyperLink et Button
            Hyperlink hyperlink = new Hyperlink();
            Button button = new Button();
            hyperlink.setText(recipe.getName());
            hyperlink.setPrefHeight(0.0);
            hyperlink.setContentDisplay(ContentDisplay.RIGHT);
            hyperlink.setPrefWidth(530);
            hyperlink.setId("recipeName");
            button.setAlignment(Pos.CENTER);
            button.setMnemonicParsing(false);
            button.setPrefHeight(31.0);
            button.setPrefWidth(31.0);
            button.setId("addToFavorite");
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FavoriteRecipe.addFavorite(recipe);
                    if(!FavoriteRecipe.getRecipes().contains(recipe)) {
                        HBox hbox = new HBox();
                        hbox.setLayoutX(10.0);
                        hbox.setPrefHeight(25.0);
                        hbox.setPrefWidth(244.0);
                        hbox.setPadding(new Insets(10,10,10,10));

                        Hyperlink hyperlink = new Hyperlink();
                        Button button = new Button();

                        hyperlink.setText(recipe.getName());
                        hyperlink.setPrefHeight(32.0);
                        hyperlink.setPrefWidth(156);
                        hyperlink.setId("recipeName");
                        button.setText("-");

                        button.setMnemonicParsing(false);
                        button.setPrefHeight(31.0);
                        button.setPrefWidth(31.0);
                        button.setId("deleteFromFavorite");

                        hbox.getChildren().add(hyperlink);
                        hbox.getChildren().add(button);

                        vboxFavorite.getChildren().add(hbox);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ingredient ERROR");
                        alert.setHeaderText("Déjà dans les favoris");
                        alert.showAndWait();
                        ingredientTextField.clear();
                    }
                }
            });

            //Ajout des deux
            hbox.getChildren().add(hyperlink);
            hbox.getChildren().add(button);
            vboxRecipesCenter.getChildren().add(hbox);
        }
    }
}