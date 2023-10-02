package com.fruitsdemo.fruitsdemo;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/fruits")
@RegisterForReflection
public class FruitResource {
    private List<Fruit> fruits = new ArrayList<>();
    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Banana", "Tropical fruit"));
        fruits.add(new Fruit("Cherry", "Tropical fruit"));
        fruits.add(new Fruit("Grape", "Tropical fruit"));
        fruits.add(new Fruit("Lemon", "Tropical fruit"));
        fruits.add(new Fruit("Mango", "Tropical fruit"));
        fruits.add(new Fruit("Orange", "Tropical fruit"));
    }

    //Get list of all fruits
    @GET
    @Path("/")
    @Produces("application/json")
    public List<Fruit> getFruits() {
        return fruits;
    }

    //Get a single fruit
    @GET
    @Path("/{name}")
    @Produces("application/json")
    public Fruit getFruit(@PathParam("name") String name) {
        for (Fruit fruit : fruits) {
            if (fruit.getName().equals(name)) {
                return fruit;
            }
        }
        return null;
    }

    //Add a new fruit
    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Fruit addFruit(@QueryParam("name") String name, @QueryParam("description") String description) {
        Fruit fruit = new Fruit(name, description);
        fruits.add(fruit);
        return fruit;
    }

    @DELETE
    @Path("/delete")
    @Produces("application/json")
    public String deleteFruit(@QueryParam("name") String name) {
        for (Fruit fruit : fruits) {
            if (fruit.getName().equals(name)) {
                fruits.remove(fruit);
                return name + " deleted successfully";
            }
        }
        return "Fruit not found";
    }
}