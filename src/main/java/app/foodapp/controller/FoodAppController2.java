package app.foodapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML private ImageView image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recipeName.setText(FoodAppController.recipeName.toUpperCase(Locale.ROOT));
        recipeName.setStyle("-fx-text-fill: white");
        for(Recipe recipe : FavoriteRecipe.getRecipes("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json")) {
            if(recipe.getName().toUpperCase(Locale.ROOT).equals(recipeName.getText())) {
                if(FavoriteRecipe.favoriteRecipe.contains(recipe)) {
                    favoriteButton.setStyle("-fx-background-color: #A4413A");
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
                if(JsonReader.listToString((int) recipe.getId()) != null) {
                    instructionsArea.setText(JsonReader.listToString((int) recipe.getId()).toUpperCase(Locale.ROOT));
                }
            }
        }
        for(Recipe recipe : JsonReader.recipes) {
            image.setImage(new Image(JsonReader.urlImage));
            if(recipe.getName().toUpperCase(Locale.ROOT).equals(recipeName.getText())) {
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
                if(JsonReader.listToString((int) recipe.getId()) != null) {
                    instructionsArea.setText(JsonReader.listToString((int) recipe.getId()).toUpperCase(Locale.ROOT));
                }
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
        if(favoriteButton.getStyle().equals("-fx-background-color: #A4413A")) {
            for(Recipe recipe : FavoriteRecipe.getRecipes("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json")) {
                if(recipe.getName().toUpperCase(Locale.ROOT).equals(recipeName.getText())) {
                    FavoriteRecipe.removeFavorite(recipe, "C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json");
                    favoriteButton.setStyle("-fx-background-color: white");
                }
            }
        }
        if(favoriteButton.getStyle().equals("-fx-background-color: white")) {
            for(Recipe recipe : FavoriteRecipe.fav) {
                if(recipe.getName().toUpperCase(Locale.ROOT).equals(recipeName.getText())) {
                    FavoriteRecipe.addFavorite(recipe, "C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json");
                    favoriteButton.setStyle("-fx-background-color: #A4413A");
                }
            }
            for(Recipe recipe : JsonReader.recipes) {
                if(recipe.getName().toUpperCase(Locale.ROOT).equals(recipeName.getText())) {
                    FavoriteRecipe.addFavorite(recipe, "C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json");
                    favoriteButton.setStyle("-fx-background-color: #A4413A");
                }
            }
        }
    }
}
