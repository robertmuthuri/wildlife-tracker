import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;


public class EndangeredAnimal extends WildLife {
    public static final String ADULT = "Adult";
    public static final String YOUNG = "Young";
    public static final String NEWBORN = "Newborn";
    public static final String HEALTHY = "Healthy";
    public static final String OKAY = "Okay";
    public static final String ILL = "Ill";

    private static final String ANIMAL_TYPE = "Endangered";

    public EndangeredAnimal(String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
        this.type = ANIMAL_TYPE;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EndangeredAnimal that = (EndangeredAnimal) o;
        return getHealth().equals(that.getHealth()) &&
                getAge().equals(that.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHealth(), getAge());
    }

    public static List<EndangeredAnimal> getAll() {
        String sql = "SELECT * FROM animals WHERE type = 'Endangered';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, health, age, type) VALUES (:name, :health, :age, :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static EndangeredAnimal findById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
        }
    }
    public static void update(int id, String name, String health, String age) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET name = :name, health = :health, age = :age WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("health", health)
                    .addParameter("age", age)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
