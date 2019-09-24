//import java.sql.Connection;
import java.util.List;
import org.sql2o.*;

public class Animal extends WildLife {

    public static final String ANIMAL_TYPE = "Non-Endangered";

    public Animal(String name){
        this.name = name;
        this.type = ANIMAL_TYPE;
    }
    @Override
    public boolean equals(Object otherAnimal) {
        if (otherAnimal instanceof Animal) {
            Animal newAnimal = (Animal) otherAnimal;
            return (this.getName().equals(newAnimal.getName()) &&
                    this.getType().equals(newAnimal.getType()));
        }
        return false;
    }
    public static List<Animal> getAll() {
        String sql = "SELECT * FROM animals WHERE type = 'Non-Endangered';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, type) VALUES (:name, :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Animal findById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
        }
    }
    public static void update(int id, String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
}
