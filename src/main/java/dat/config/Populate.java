package dat.config;


import dat.entities.*;
import dat.enums.TripCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dat.enums.RecipeDifficulty.*;

public class Populate {
    public static void main(String[] args) {

populate();


    }


    public static void populate() {
       EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("exam"); // Change to your persistence unit name


//        // making trips with guides
//        Trip trip7 = new Trip("Desert Safari", LocalDateTime.of(2024, 9, 1, 0, 0), LocalDateTime.of(2024, 9, 10, 0, 0), "Sahara Desert", 499.99, TripCategory.SNOW);
//        Trip trip8 = new Trip("Scuba Diving", LocalDateTime.of(2024, 10, 1, 0, 0), LocalDateTime.of(2024, 10, 7, 0, 0), "Great Barrier Reef", 399.99, TripCategory.SEA);
//        Trip trip9 = new Trip("Historical Sites Tour", LocalDateTime.of(2024, 11, 1, 0, 0), LocalDateTime.of(2024, 11, 5, 0, 0), "Rome", 299.99, TripCategory.CITY);
//
//        // making List with trips for the guides to add
//        List<Trip> trips1 = List.of(trip7, trip8, trip9);
//        List<Trip> trips2 = List.of(trip7, trip8);
//        List<Trip> trips3 = List.of(trip9);

        // making dummy guides
        Guide guide1 = new Guide( "Alice", "Smith", "alice@example.com", "123-456-7890", 5);
        Guide guide2 = new Guide( "Bob", "Johnson", "bob@example.com", "098-765-4321", 8);
        Guide guide3 = new Guide( "Charlie", "Brown", "charlie@example.com", "555-123-4567", 10);
        Guide guide4 = new Guide( "Diana", "Prince", "diana@example.com", "444-987-6543", 6);

        // making dummy trips with guides allready in the constructors
        Trip trip1 = new Trip("Beach Adventure", LocalDateTime.of(2024, 6, 1, 0, 0), LocalDateTime.of(2024, 6, 10, 0, 0), "Sunny Beach", 299.99, TripCategory.BEACH, guide1);
        Trip trip2 = new Trip("Mountain Hike", LocalDateTime.of(2024, 7, 15, 0, 0), LocalDateTime.of(2024, 7, 22, 0, 0), "Rocky Mountains", 399.99, TripCategory.FOREST, guide1);
        Trip trip3 = new Trip("City Tour", LocalDateTime.of(2024, 8, 5, 0, 0), LocalDateTime.of(2024, 8, 12, 0, 0), "New York", 199.99, TripCategory.CITY, guide2);
        Trip trip4 = new Trip("Desert Safari", LocalDateTime.of(2024, 9, 10, 0, 0), LocalDateTime.of(2024, 9, 17, 0, 0), "Sahara Desert", 499.99, TripCategory.SEA, guide3);
        Trip trip5 = new Trip("Winter Wonderland", LocalDateTime.of(2024, 12, 1, 0, 0), LocalDateTime.of(2024, 12, 10, 0, 0), "Snowy Mountains", 599.99, TripCategory.SNOW, guide4);
        Trip trip6 = new Trip("Lake Retreat", LocalDateTime.of(2024, 10, 20, 0, 0), LocalDateTime.of(2024, 10, 25, 0, 0), "Lake Tahoe", 349.99, TripCategory.LAKE, guide3);

        // Create sample guides


        // Associate trips with guides
        guide1.getTrips().add(trip1);
        guide1.getTrips().add(trip2);
        guide2.getTrips().add(trip3);
        guide3.getTrips().add(trip4);
        guide4.getTrips().add(trip5);
        guide3.getTrips().add(trip6);

        // Set the guide in each trip
        trip1.setGuide(guide1);
        trip2.setGuide(guide1);
        trip3.setGuide(guide2);
        trip4.setGuide(guide3);
        trip5.setGuide(guide4);
        trip6.setGuide(guide3);

        // Persist data to the database
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Persist guides, which will also persist their trips due to cascade settings
            em.persist(guide1);
            em.persist(guide2);
            em.persist(guide3);
            em.persist(guide4);


            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


//    public static void populateOld() {
//        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("recipes");
//
//        // setting the recipe-collection-Set method to a variable for emil and janus
//        Set<Recipes> recipesSetForEmil = getRecipes1();
//        Set<Recipes> recipesSetForJanus = getRecipes2();
//
//        try (EntityManager em = emf.createEntityManager()) {
//            em.getTransaction().begin();
//
//            Recipes recipes1 = new Recipes("Browned Butter Caramel", "100 g cream. 100 g chocolate. 100 g browned butter. 80 g sugar", "Boil pasta in water", EASY);
//            //creating users
//            User emil = new User("emil", "user1");
//            User janus = new User("janus", "user1");
//            //creating roles
//            Role regularRole = new Role("regular");
//            Role adminRole = new Role("admin");
//            // adding the roles to the users
//            emil.addRole(adminRole);
//            janus.addRole(regularRole);
//
//            // Add all recipes(Set/collections) for the users and setting the user in the recipes
//            janus.getRecipes().addAll(recipesSetForJanus); // Add all recipes to janus's set of recipes
//            recipesSetForJanus.forEach(recipes -> recipes.setCreatedBy(janus)); // Set the user in the recipe to janus. for the entire Set/collections
//            emil.getRecipes().addAll(recipesSetForEmil);
//            recipesSetForEmil.forEach(recipes -> recipes.setCreatedBy(emil));
//
//            // adding a single recipe to emil and setting the user in the recipe to emil
//            emil.getRecipes().add(recipes1); // Add recipe to emil's set of recipes
//            recipes1.setCreatedBy(emil); // Set the user in the recipe to emil.
//            // no need for persisting the recipe, because it is already getting persisted when persisting user. because of the cascade type(PERSIST).
//
//            // persist the users
//            em.persist(emil);
//            em.persist(janus);
//            em.getTransaction().commit();
//
//            // print the users to the console
//            System.out.println(janus);
//            System.out.println(emil);
//        }
//    }
//
//
//
//    @NotNull
//    private static Set<Recipes> getRecipes1() {
//
//        Recipes recipes1 = new Recipes("Browned Butter Caramel", "100 g cream. 100 g chocolate. 100 g browned butter. 80 g sugar", "Boil pasta in water", EASY);
//        Recipes recipes2 = new Recipes("Lemon Tart", "200 g flour. 100 g butter. 150 g sugar. 3 eggs. 2 lemons", "Make the tart crust with flour and butter, mix the filling with eggs, lemon juice, and sugar, bake until set.", VERY_EASY);
//        Recipes recipes3 = new Recipes("Chocolate Mousse", "150 g dark chocolate. 3 eggs. 50 g sugar. 200 ml cream", "Melt chocolate, fold in whipped cream, and beaten egg whites, chill until set.", HARD);
//
//
//        Set<Recipes> recipesSet = new HashSet<>();
//
//        recipesSet.add(recipes1);
//        recipesSet.add(recipes2);
//        recipesSet.add(recipes3);
//
//
//        return recipesSet;
//    }
//
//    @NotNull
//    private static Set<Recipes> getRecipes2() {
//        Recipes recipes4 = new Recipes("Vanilla Panna Cotta", "400 ml cream. 50 g sugar. 1 vanilla bean. 3 gelatine sheets", "Heat cream with vanilla and sugar, dissolve gelatine, pour into molds and chill until set.", EASY);
//        Recipes recipes5 = new Recipes("Raspberry Cheesecake", "200 g cream cheese. 100 g sugar. 150 g raspberries. 150 g digestive biscuits. 50 g butter", "Crush biscuits and mix with melted butter for the base, blend cream cheese with sugar and fold in raspberries, pour over the base and chill.", MEDIUM);
//        Recipes recipes6 = new Recipes("Tiramisu", "300 g mascarpone. 150 ml espresso. 100 g sugar. 200 g ladyfingers. 3 eggs. Cocoa powder", "Whisk egg yolks with sugar and fold in mascarpone, dip ladyfingers in espresso, layer them with the mascarpone mixture, and dust with cocoa.", HARD);
//
//        Set<Recipes> recipesSet = new HashSet<>();
//
//        recipesSet.add(recipes4);
//        recipesSet.add(recipes5);
//        recipesSet.add(recipes6);
//
//        return recipesSet;
//    }






