import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Ranger {

    String name;
    private int id;

    public Ranger(String name) {
        this.name = name;

    }
    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranger location = (Ranger) o;
        return getName().equals(location.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
    public static List<Ranger> getAll() {
        String sql = "SELECT * FROM rangers;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Ranger.class);
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Ranger findById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
        }
    }
    public static void update(int id, String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE rangers SET name = :name WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM rangers WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }
//    public List<Sighting> getAllSightingsByRanger() {
//        try(Connection con = DB.sql2o.open()){
//            return con.createQuery("SELECT * FROM sightings WHERE ranger_id = :id")
//                    .addParameter("id", this.id)
//                    .executeAndFetch(Sighting.class);
//        }
//    }
}
