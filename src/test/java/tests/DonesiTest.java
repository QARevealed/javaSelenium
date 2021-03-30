package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DonesiTest extends BaseTest
{

    String address="Beogradska 10";

    @Test
    public void donesiComFilterTest() throws InterruptedException
    {
        DonesiHomePage homepage = new DonesiHomePage(driver);
        homepage.donesiComFilter(address);

        DonesiMapPage map = new DonesiMapPage(driver);
        map.continueFromMap();

        DonesiRestaurantsPage restaurants = new DonesiRestaurantsPage(driver);
        restaurants.filterItalianRestaurants();

        int allRestaurants = Integer.parseInt(restaurants.numberOfAllRestaurants);
        int italianRestaurants = Integer.parseInt(restaurants.numberOfItalianRestaurants);
        System.out.println();
        System.out.println("All int:"+allRestaurants+" italian int:"+italianRestaurants);

        Assert.assertTrue("problem with filters...",italianRestaurants<allRestaurants);

        List<WebElement> restaurantsList = restaurants.restaurantsList;
        System.out.println();
        System.out.println("List size:"+restaurants.restaurantsList.size());

        Assert.assertEquals("Counter of restaurants is wrong...",restaurantsList.size(),italianRestaurants);


        WebElement randomRestaurant = restaurants.chooseRandomRestaurant();
        js.executeScript("arguments[0].scrollIntoView();",randomRestaurant);
        randomRestaurant.click();



        Thread.sleep(4000); // ostavljeno zbog vizuelne potvrde
    }


    @Test
    public void orderMealsFromRestaurants() throws InterruptedException {
        driver.get("https://www.donesi.com/shops?address=Beogradska%2010%2C%2011000%20Beograd%2C%20Vra%C4%8Dar&city=Vra%C4%8Dar&county=Beograd&latitude=44.803475&longitude=20.4673438&nomap=0&street=Beogradska&street_no=10&zip=11000&area=Vra%C4%8Dar&city_transliterated=&slug=%2F&deliverytype=0&scope=personal&t=1609268593401");
        driver.get("https://www.donesi.com/dostava/beograd/bigpizza-centar");
        List<Meal> addedMeals = new ArrayList<>();

        Random rndNr = new Random();
        int turns = rndNr.nextInt(5)+1;
        System.out.println("Narucice se u komatima "+turns+" jela");
        double subtotalAddedMeals = 0.00;
        for(int i =1;i<=turns;i++)
        {
            DonesiMealsPage meals = new DonesiMealsPage(driver);
            meals.createMealsList();
            WebElement randomMeal = meals.chooseRandomMeal();
            // System.out.println(randomMeal.getText());


            js.executeScript("arguments[0].scrollIntoView();", randomMeal);
            wdWait.until(ExpectedConditions.visibilityOf(randomMeal));
            // randomMeal.click();
            js.executeScript("arguments[0].click();", randomMeal);

            wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("popup_menu_item")));
            WebElement chosenMeal = driver.findElement(By.id("popup_menu_item"));

            //System.out.println(chosenMeal1.getText());
            Meal meal = new Meal();
            WebElement chosenMealName = chosenMeal.findElement(By.className("modal-heading"));
            System.out.println("Meal name:" + chosenMealName.getText());
            WebElement chosenMeal1Price = chosenMeal.findElement(By.id("item-price"));
            System.out.println("Meal price:" + chosenMeal1Price.getText().replace("RSD", ""));

            meal.name = chosenMealName.getText().toUpperCase();
            meal.price = chosenMeal1Price.getText().replace("RSD","").replace(".","").replace(",",".").trim();
            addedMeals.add(meal);

            driver.findElement(By.className("submit")).findElement(By.tagName("span")).click();
            wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("cart-total-prica"),Double.toString(subtotalAddedMeals)));
            subtotalAddedMeals = subtotalAddedMeals + Double.parseDouble(meal.price);
        }
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-product-list")));
        List<WebElement> cartMeals = driver.findElements(By.className("cart-product-list-item"));
        double subtotal=0.00;
        for(int i=0;i<turns;i++)
        {
            WebElement cartMeal = cartMeals.get(i);
            WebElement cartMealName = cartMeal.findElement(By.className("cart-product-list-item-name"));
            System.out.println("CartMealName:" + cartMealName.getText().toUpperCase());
            System.out.println("AddedMealName:" + addedMeals.get(i).name);
            WebElement cartMealPrice = cartMeal.findElement(By.className("cart-product-list-item-price"));
            double cena = Double.parseDouble (cartMealPrice.getText().replace("RSD","").replace(".","").replace(",",".").trim());
            subtotal = subtotal+cena;
            System.out.println("CartMealPrice:" + cena);
            System.out.println("AddedMealPrice:" + addedMeals.get(i).price);
            Assert.assertEquals("Nisu ista imena!",cartMealName.getText().toUpperCase(),addedMeals.get(i).name);
            Assert.assertEquals("Nisu iste cene!",cena,Double.parseDouble(addedMeals.get(i).price),0.01);

        }
        Thread.sleep(2000);
        WebElement totalPrice = driver.findElement(By.className("cart-total-price"));
        Assert.assertEquals("Ne valja subtotal!",subtotal,Double.parseDouble(totalPrice.getText().replace("RSD","").
                replace(".","").replace(",",".").trim()),0.01);
        System.out.println("Subtotal:"+subtotal);


    }

}
