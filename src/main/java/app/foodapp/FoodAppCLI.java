package app.foodapp;

import recipe.FavoriteRecipe;

import java.util.Scanner;

public class FoodAppCLI {

    public static void main(String[] args) {
        System.out.println("--Welcome to the food app--\n");

        System.out.println("1:Found Recipe with Ingredient ");
        System.out.println("2:Favorite Recipe ");
        System.out.println("3:Exit");

        System.out.println("Choose your menu");
        Scanner scanner = new Scanner(System.in);
        while(true) {
        if (scanner.next().equals("1")){
        } else if(scanner.next().equals("2")) {
            System.out.println(FavoriteRecipe.getRecipes());

        } else if(scanner.next().equals("3")){
            System.exit(0);

        }
        }
    }
}
