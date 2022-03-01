package app.foodapp.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import recipe.FavoriteRecipe;
import recipe.JsonReader;
import recipe.Recipe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class FoodAppController implements Initializable {

    @FXML private Spinner spinner;
    @FXML private TextField ingredientTextField;
    @FXML private Label ingredientsText;
    @FXML private VBox vboxRecipesCenter;
    @FXML private VBox vboxFavorite;
    public static String recipeName;
    private List<String> recipeNames = new ArrayList<>();

    public void initialize(URL location, ResourceBundle resourceBundle) {
        for(Recipe recipe : FavoriteRecipe.getRecipes()) {
            HBox hbox = new HBox();
            hbox.setLayoutX(10.0);
            hbox.setPrefHeight(25.0);
            hbox.setPrefWidth(244.0);
            hbox.setPadding(new Insets(10,10,10,10));

            Hyperlink hyperlink = new Hyperlink();
            Button button = new Button();

            hyperlink.setText(recipe.getName().toUpperCase(Locale.ROOT));
            hyperlink.setPrefHeight(32.0);
            hyperlink.setPrefWidth(156.0);
            hyperlink.setId(recipe.getName());
            hyperlink.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    recipeName = hyperlink.getText().toUpperCase(Locale.ROOT);
                    hyperlink.setVisited(false);
                    hyperlink.setUnderline(false);
                    displayPage2(event);
                }
            });

            button.setMnemonicParsing(false);
            button.setPrefHeight(31.0);
            button.setPrefWidth(31.0);
            button.setId("deleteFromFavorite");
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FavoriteRecipe.removeFavorite(recipe);
                    vboxFavorite.getChildren().remove(hbox);
                }
            });

            hbox.getChildren().add(hyperlink);
            hbox.getChildren().add(button);

            vboxFavorite.getChildren().add(hbox);

            recipeNames.add(recipe.getName());
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
    private void clearAll() {
        ingredientsText.setText("");
        vboxRecipesCenter.getChildren().removeAll(vboxRecipesCenter.getChildren());
        spinner.getValueFactory().setValue(0);
    }

    @FXML
    private void displayRecipes() {
        vboxRecipesCenter.getChildren().removeAll(vboxRecipesCenter.getChildren());
        JsonReader.setRecipes(String.valueOf(spinner.getValue()));
        for(Recipe recipe : JsonReader.recipes) {
            //HBOX
            HBox hbox = new HBox();
            hbox.setPrefHeight(0.0);
            hbox.setPrefWidth(568.0);
            hbox.setPadding(new Insets(10,10,10,10));

            //HyperLink et Button

            Hyperlink hyperlink = new Hyperlink();
            Button favoriteButton = new Button();
            hyperlink.setText(recipe.getName().toUpperCase(Locale.ROOT));
            hyperlink.setPrefHeight(0.0);
            hyperlink.setContentDisplay(ContentDisplay.RIGHT);
            hyperlink.setPrefWidth(517);
            hyperlink.setId("recipeName");
            hyperlink.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    recipeName = hyperlink.getText().toUpperCase(Locale.ROOT);
                    hyperlink.setVisited(false);
                    hyperlink.setUnderline(false);
                    displayPage2(event);
                }
            });
            favoriteButton.setAlignment(Pos.CENTER);
            favoriteButton.setMnemonicParsing(false);
            favoriteButton.setPrefHeight(31.0);
            favoriteButton.setPrefWidth(31.0);
            favoriteButton.setId("addToFavorite");
            hyperlink.setVisited(false);
            hyperlink.setUnderline(false);
            if(recipeNames.contains(recipe.getName())) {
                favoriteButton.setStyle("-fx-background-color: red");
            } else {
                favoriteButton.setStyle("-fx-background-color: white");
            }
            favoriteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(recipeNames.contains(recipe.getName()) || FavoriteRecipe.favoriteRecipe.contains(recipe)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ingredient ERROR");
                        alert.setHeaderText("Déjà dans les favoris");
                        alert.showAndWait();
                        ingredientTextField.clear();
                    } else {
                        favoriteButton.setStyle("-fx-background-color: red");
                        FavoriteRecipe.addFavorite(recipe);
                        HBox hbox = new HBox();
                        hbox.setLayoutX(10.0);
                        hbox.setPrefHeight(25.0);
                        hbox.setPrefWidth(244.0);
                        hbox.setPadding(new Insets(10,10,10,10));

                        Hyperlink hyperlink = new Hyperlink();
                        Button button = new Button();

                        hyperlink.setText(recipe.getName().toUpperCase(Locale.ROOT));
                        hyperlink.setPrefHeight(32.0);
                        hyperlink.setPrefWidth(156);
                        hyperlink.setId("recipeName");
                        hyperlink.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                recipeName = hyperlink.getText().toUpperCase(Locale.ROOT);
                                hyperlink.setVisited(false);
                                hyperlink.setUnderline(false);
                                displayPage2(event);
                            }
                        });

                        button.setMnemonicParsing(false);
                        button.setPrefHeight(31.0);
                        button.setPrefWidth(31.0);
                        button.setId("deleteFromFavorite");
                        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                FavoriteRecipe.removeFavorite(recipe);
                                vboxFavorite.getChildren().remove(hbox);
                            }
                        });

                        hbox.getChildren().add(hyperlink);
                        hbox.getChildren().add(button);

                        vboxFavorite.getChildren().add(hbox);
                    }
                }
            });

            //Ajout des deux
            hbox.getChildren().add(hyperlink);
            hbox.getChildren().add(favoriteButton);
            vboxRecipesCenter.getChildren().add(hbox);
        }
    }

    @FXML
    private void displayPage2(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("app/foodapp/view/foodapprecipes.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}