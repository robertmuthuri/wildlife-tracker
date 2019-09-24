import org.sql2o.*;

import java.util.List;
import java.util.Objects;

public class Location {

    private String name;
    private int id;

    public Location(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public int getId() { return id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return getName().equals(location.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
    public static List<Location> getAll() {
        String sql = "SELECT * FROM locations;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Location.class);
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO locations (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Location findById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM locations WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
        }
    }
    public static void update(int id, String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE locations SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }

    }
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM locations WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }
}
