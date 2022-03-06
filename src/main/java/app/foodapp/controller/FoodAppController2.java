package app.foodapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import recipe.FavoriteRecipe;
import recipe.JsonReader;
import recipe.Recipe;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class FoodAppController2 implements Initializable {

    @FXML private Label recipeName;
    @FXML private Button favoriteButton;
    @FXML private Label ingredientsArea;
    @FXML private Label instructionsArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recipeName.setText(FoodAppController.recipeName.toUpperCase(Locale.ROOT));
        recipeName.setStyle("-fx-text-fill: white");
        for(Recipe recipe : FavoriteRecipe.getRecipes()) {
            if(recipe.getName().toUpperCase(Locale.ROOT).equals(recipeName.getText())) {
                if(FavoriteRecipe.favoriteRecipe.contains(recipe)) {
                    favoriteButton.setStyle("-fx-background-color: red");
                }
                for(int i = 1; i < recipe.getMissedIngredients().size(); i++) {
                    if(ingredientsArea.getText().equals("")) {
                        ingredientsArea.setText(recipe.getMissedIngredients().get(0).toUpperCase(Locale.ROOT));
                    }
                    ingredientsArea.setText(ingredientsArea.getText() + "\n" + recipe.getMissedIngredients().get(i).toUpperCase(Locale.ROOT));
                }
                for(int i = 1; i < recipe.getUsedIngerdients().size(); i++) {
                    if(ingredientsArea.getText().equals("")) {
                        ingredientsArea.setText(recipe.getUsedIngerdients().get(0).toUpperCase(Locale.ROOT));
                    }
                    ingredientsArea.setText(ingredientsArea.getText() + "\n" + recipe.getUsedIngerdients().get(i).toUpperCase(Locale.ROOT));
                }
                instructionsArea.setText(JsonReader.listToString((int) recipe.getId()).toUpperCase(Locale.ROOT));
            }
        }
        for(Recipe recipe : JsonReader.recipes) {
            if(recipe.getName().equals(recipeName.getText())) {
                for(int i = 1; i < recipe.getMissedIngredients().size(); i++) {
                    if(ingredientsArea.getText().equals("")) {
                        ingredientsArea.setText(recipe.getMissedIngredients().get(0));
                    }
                    ingredientsArea.setText(ingredientsArea.getText() + "\n" + recipe.getMissedIngredients().get(i));
                }
                for(int i = 1; i < recipe.getUsedIngerdients().size(); i++) {
                    if(ingredientsArea.getText().equals("")) {
                        ingredientsArea.setText(recipe.getUsedIngerdients().get(0));
                    }
                    ingredientsArea.setText(ingredientsArea.getText() + "\n" + recipe.getUsedIngerdients().get(i));
                }
                instructionsArea.setText(JsonReader.listToString((int) recipe.getId()).toUpperCase(Locale.ROOT));
            }
        }
    }

    @FXML
    private void displayPage1(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("app/foodapp/view/foodapp.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(Objects.requireNonNull(root)));
        stage.show();
    }

    @FXML
    private void disfavorite() {
        if(JsonReader.recipes.isEmpty()) {
            for(Recipe recipe : FavoriteRecipe.getRecipes()) {
                if(recipe.getName().equals(recipeName.getText())) {
                        FavoriteRecipe.removeFavorite(recipe);
                        favoriteButton.setStyle("-fx-background-color: white");
                }
            }
        }
        if(JsonReader.recipes.isEmpty() && FavoriteRecipe.getRecipes().isEmpty()) {
            for(Recipe recipe2 : FavoriteRecipe.fav) {
                if(recipe2.getName().equals(recipeName.getText())) {
                    FavoriteRecipe.addFavorite(recipe2);
                    favoriteButton.setStyle("-fx-background-color: red");
                }
            }
        }
        if(!JsonReader.recipes.isEmpty()) {
            for (Recipe recipe : JsonReader.recipes) {
                if (recipe.getName().equals(recipeName.getText())) {
                    if (JsonReader.recipes.contains(recipe) && !FavoriteRecipe.favoriteRecipe.contains(recipe)) {
                        FavoriteRecipe.addFavorite(recipe);
                        favoriteButton.setStyle("-fx-background-color: red");
                        break;
                    }
                    if (JsonReader.recipes.contains(recipe) && FavoriteRecipe.favoriteRecipe.contains(recipe)) {
                        FavoriteRecipe.removeFavorite(recipe);
                        favoriteButton.setStyle("-fx-background-color: white");
                    }
                }
            }
        }
    }
}
