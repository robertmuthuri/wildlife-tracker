import static spark.Spark.*;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        Map<String, Object> model = new HashMap<>();
        String index = "index.hbs";

        staticFileLocation("/public");

        get("/", (req, res) -> {
            model.put("animals", Animal.getAll());
            model.put("endangeredanimals", EndangeredAnimal.getAll());
            model.put("locations", Location.getAll());
            model.put("rangers", Ranger.getAll());
            return new ModelAndView(model, index);
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (req, res) -> {
            model.put("animals", Animal.getAll());
            model.put("endangeredanimals", EndangeredAnimal.getAll());
            model.put("locations", Location.getAll());
            model.put("rangers", Ranger.getAll());
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/new", (req, res) -> {
            String name = req.queryParams("name");
            Animal newAnimal = new Animal(name);
            newAnimal.save();
            model.put("animals", Animal.getAll());
            model.put("endangeredanimals", EndangeredAnimal.getAll());
            model.put("locations", Location.getAll());
            model.put("rangers", Ranger.getAll());
            return new ModelAndView(model, index);
        }, new HandlebarsTemplateEngine());

        get("/endangeredanimals/new", (req, res) -> {
            model.put("Healthy",EndangeredAnimal.HEALTHY);
            model.put("Okay",EndangeredAnimal.OKAY);
            model.put("Ill",EndangeredAnimal.ILL);
            model.put("Young",EndangeredAnimal.YOUNG);
            model.put("Adult",EndangeredAnimal.ADULT);
            model.put("Newborn",EndangeredAnimal.NEWBORN);
            model.put("animals", Animal.getAll());
            model.put("locations", Location.getAll());
            model.put("rangers", Ranger.getAll());
            model.put("endangeredanimals", EndangeredAnimal.getAll());
            return new ModelAndView(model, "endangered-animal-form.hbs");
        }, new HandlebarsTemplateEngine());

//        post("/endangeredanimals/new", (req, res) -> {
//            String name = req.queryParams("name");
//            String age = req.queryParams("age");
//            String health = req.queryParams("health");
//            EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(name,health,age);
//            newEndangeredAnimal.save();
//            model.put("animals", Animal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            return new ModelAndView(model, "animals.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/locations", (req, res) -> {
//            model.put("sightings", Sighting.getAll());
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "locations.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        get("/locations/new", (req, res) -> {
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "location-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/locations/new", (req, res) -> {
//            String name = req.queryParams("name");
//            Location newLocation = new Location(name);
//            newLocation.save();
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "locations.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        get("/rangers", (req, res) -> {
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "rangers.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/rangers/new", (req, res) -> {
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "ranger-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/rangers/new", (req, res) -> {
//            String name = req.queryParams("name");
//            Ranger newRanger = new Ranger(name);
//            newRanger.save();
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "rangers.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/sightings", (req, res) -> {
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "sightings.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        get("/sightingsnonendangered/new", (req, res) -> {
//            model.put("animals", Animal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "sightNonEndangered-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        get("/sightingsendangered/new", (req, res) -> {
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            model.put("endangered", true);
//            return new ModelAndView(model, "sighting-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/sightingsnonendangered/new", (req, res) -> {
//            int animal_id = Integer.parseInt(req.queryParams("animal"));
//            int ranger_id = Integer.parseInt(req.queryParams("ranger"));
//            int location_id = Integer.parseInt(req.queryParams("location"));
//            Sighting newSighting = new Sighting(animal_id, ranger_id,location_id);
//            newSighting.save();
//            String animal = Animal.find(animal_id).getName();
//            String type = Animal.find(animal_id).getType();
//            String ranger = Ranger.find(ranger_id).getName();
//            String location = Location.find(location_id).getName();
//            int sighting_id = newSighting.getId();
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "sightings.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        post("/sightingsendangered/new", (req, res) -> {
//            int animal_id = Integer.parseInt(req.queryParams("animal"));
//            int ranger_id = Integer.parseInt(req.queryParams("ranger"));
//            int location_id = Integer.parseInt(req.queryParams("location"));
//            Sighting newSighting = new Sighting(animal_id, ranger_id,location_id);
//            newSighting.save();
//            String animal = EndangeredAnimal.find(animal_id).getName();
//            String type = EndangeredAnimal.find(animal_id).getType();
//            String ranger = Ranger.find(ranger_id).getName();
//            String location = Location.find(location_id).getName();
//            int sighting_id = newSighting.getId();
//            model.put("animals", Animal.getAll());
//            model.put("endangeredanimals", EndangeredAnimal.getAll());
//            model.put("locations", Location.getAll());
//            model.put("rangers", Ranger.getAll());
//            return new ModelAndView(model, "sightings.hbs");
//        }, new HandlebarsTemplateEngine());
    }
}