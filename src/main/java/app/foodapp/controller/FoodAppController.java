package app.foodapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import recipe.JsonReader;

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
    @FXML private Text text;
    @FXML private Text textTest;
    @FXML private Label label;

    public void initialize(URL location, ResourceBundle resourceBundle) {}

    @FXML
    private void addIngredient() {
        if(ingredientTextField.getText() != null) {
            if(JsonReader.allIngredients.contains(ingredientTextField.getText())) {
                JsonReader.ingredients.add(ingredientTextField.getText());
                if (text.getText().equals("")) {
                    text.setText(ingredientTextField.getText());
                } else {
                    text.setText(text.getText() + ", " + ingredientTextField.getText());
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
    private void test() {
        JsonReader.setRecipes(String.valueOf(5));
        text.setText(JsonReader.recipes.toString());
    }
}