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
                    String findByIngredient;
                    String number;
                    List<Recipe> chooseRecipes = new ArrayList<>();

                    System.out.println("Ingredients (forme : ingredient,+ingredient,+ingredient... : ");

                    Scanner inputfindByIngredient = new Scanner(System.in);
                    Scanner inputNumberfindByIngredient = new Scanner(System.in);
                    findByIngredient = inputfindByIngredient.next();

                    System.out.println("Nombre de recette : ");

                    number = inputNumberfindByIngredient.next();
                    JsonReader.setRecipes(findByIngredient, number);
                    List<Recipe> recipes = JsonReader.recipes;

                    for(Recipe recipe : recipes) {
                        count1++;
                        chooseRecipes.add(recipe);
                        System.out.println(count1 + ")"  + recipe.getName());
                    }

                    System.out.println("Choisissez une recette par pitié !");

                    Scanner inputChooseFavorite1 = new Scanner(System.in);
                    chooseFavorite1 = inputChooseFavorite1.nextInt();

                    if(chooseFavorite1 > chooseRecipes.size()) {
                        System.out.println("NOT A VALID NUMBER");
                        break;
                    }

                    else {
                        Recipe recipeChoose = chooseRecipes.get(chooseFavorite1 - 1);
                        chooseRecipes.clear();
                        System.out.println(recipeChoose.getName() +"\n" + "Used Ingredients : " + recipeChoose.getUsedIngerdients() +"\n" + "Missed Ingredients : " + recipeChoose.getMissedIngredients());
                        System.out.println("Est-ce que vous voulez l'ajouter aux favoris ? (Y/N) ");

                        if(inputChooseFavorite1.next().equals("Y") || inputChooseFavorite1.next().equals("y") ) {
                            FavoriteRecipe.addFavorite(recipeChoose);
                        }
                        if(inputChooseFavorite1.next().equals("N") || inputChooseFavorite1.next().equals("n")) {
                            FavoriteRecipe.removeFavorite(recipeChoose);
                        }
                        break;
                    }

                case 2:
                    int chooseFavorite;
                    Scanner inputChooseFavorite = new Scanner(System.in);
                    int count = 0;
                    List<Recipe> favoriteBuff = new ArrayList<>(FavoriteRecipe.getRecipes());

                    for(Recipe recipe : favoriteBuff) {
                        count++;
                        System.out.println(count+".)"  + recipe.getName());
                    }

                    chooseFavorite = inputChooseFavorite.nextInt();
                    if(chooseFavorite > favoriteBuff.size()) {
                        System.out.println("NOT A VALID NUMBER");
                        favoriteBuff.clear();
                        break;
                    }

                    else {
                        System.out.println("Choisis une recette chef");
                        Recipe recipeChoose = favoriteBuff.get(chooseFavorite - 1);
                        favoriteBuff.clear();
                        System.out.println("Tu veux l'enlever le sang ? (Y/N)");
                        if(inputChooseFavorite.next().equals("Y") || inputChooseFavorite.next().equals("y")) {
                            FavoriteRecipe.removeFavorite(recipeChoose);
                        }
                        favoriteBuff.clear();
                        //Recipe recipeChoose = favoriteBuff.get(chooseFavorite - 1);
                        //favoriteBuff.clear();
                        //System.out.println(recipeChoose.getName() +"\n" + "Used Ingredients : " + recipeChoose.getUsedIngerdients() +"\n" + "Missed Ingredients : " + recipeChoose.getMissedIngredients());
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
