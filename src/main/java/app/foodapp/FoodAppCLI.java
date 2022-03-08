package app.foodapp;

import recipe.FavoriteRecipe;
import recipe.JsonReader;
import recipe.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodAppCLI {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        JsonReader.reader();
        FavoriteRecipe.favoriteRecipe.clear();

        while(true) {
            System.out.println("---welcome on food app---\n");
            System.out.print("1) Find Recipe By Ingredient \n");
            System.out.print("2) Favorite Recipe\n");
            System.out.print("3) Exit\n");

            choice = input.nextInt();

            switch(choice) {

                case 1:
                    int chooseFavorite1;
                    int count1 = 0;
                    String number;
                    List<Recipe> chooseRecipes = new ArrayList<>();

                    System.out.println("Ingredients (Ecrivez 'stop' quand vous avez noté tous vos ingrédients) : ");

                    Scanner inputfindByIngredient = new Scanner(System.in);
                    Scanner inputNumberfindByIngredient = new Scanner(System.in);
                    if(JsonReader.allIngredients.contains(inputfindByIngredient.next())) {
                        JsonReader.ingredients.add(inputfindByIngredient.next());
                    }
                    if(!JsonReader.allIngredients.contains(inputfindByIngredient.next())) {
                        System.out.println("L'ingredient n'existe pas");
                    }
                    System.out.println("Nombre de recette : ");
                    number = inputNumberfindByIngredient.next();
                    JsonReader.setRecipes(number);
                    List<Recipe> recipes = JsonReader.recipes;

                    for(Recipe recipe : recipes) {
                        count1++;
                        chooseRecipes.add(recipe);
                        System.out.println(count1 + ")"  + recipe.getName());
                    }

                    System.out.println("Choisissez une recette !");

                    Scanner inputChooseFavorite1 = new Scanner(System.in);
                    chooseFavorite1 = inputChooseFavorite1.nextInt();

                    if(chooseFavorite1 > chooseRecipes.size()) {
                        System.out.println("NOT A VALID NUMBER");
                        break;
                    }

                    else {
                        Recipe recipeChoose = chooseRecipes.get(chooseFavorite1 - 1);
                        chooseRecipes.clear();
                        System.out.println(recipeChoose.getName() +"\n" + "Used Ingredients : " + recipeChoose.getUsedIngerdients() +"\n" + "Missed Ingredients : " + recipeChoose.getMissedIngredients()
                                + "\nInstructions : " + JsonReader.listToString((int) recipeChoose.getId()));
                        System.out.println("Est-ce que vous voulez l'ajouter aux favoris ? (1/2) ");

                        if(inputChooseFavorite1.nextInt() == (1)) {
                            FavoriteRecipe.addFavorite(recipeChoose, "C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json");
                        }
                        if(inputChooseFavorite1.nextInt() == 2) {
                            break;
                        }
                        break;
                    }

                case 2:
                    int chooseFavorite;
                    Scanner inputChooseFavorite = new Scanner(System.in);
                    int count = 0;
                    List<Recipe> favoriteBuff = new ArrayList<>(FavoriteRecipe.getRecipes("C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json"));

                    for(Recipe recipe : favoriteBuff) {
                        count++;
                        System.out.println(count+".)"  + recipe.getName());
                    }
                    System.out.println("Choisissez une recette !");

                    chooseFavorite = inputChooseFavorite.nextInt();
                    if(chooseFavorite > favoriteBuff.size()) {
                        System.out.println("NOT A VALID NUMBER");
                        favoriteBuff.clear();
                        break;
                    }

                    else {
                        Recipe recipeChoose = favoriteBuff.get(chooseFavorite - 1);
                        favoriteBuff.clear();
                        System.out.println("Voulez-vous enlever la recette des favoris ? (Yes/No)");
                        if(inputChooseFavorite.next().equals("Yes") || inputChooseFavorite.next().equals("yes")) {
                            FavoriteRecipe.removeFavorite(recipeChoose, "C:\\Users\\tata1\\IdeaProjects\\food-app-groupe-zz\\src\\main\\resources\\Favorite.json");
                        }
                        favoriteBuff.clear();
                        break;
                    }

                case 3:
                    System.out.println("Exiting");
                    System.exit(0);
                    break;

                default :
                    System.out.println("This is not a valid Menu Option! Please Select Another");
                    break;
            }
        }
    }
}
