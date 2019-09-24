import org.sql2o.*;
import java.util.List;
import java.util.Objects;

import java.sql.Timestamp;
import java.util.Date;

public class Sighting {
    private int id;
    private int animal_id;
    private int ranger_id;
    private int location_id;
    private Timestamp sighted_at;

    public Sighting(int animal_id, int ranger_id, int location_id) {
        this.animal_id = animal_id;
        this.ranger_id = ranger_id;
        this.location_id = location_id;
    }

    public int getId() { return id; }
    public int getAnimal_id() { return animal_id; }
    public int getRanger_id() { return ranger_id; }
    public int getLocation_id() { return location_id; }
    public Timestamp getSightedAt() { return sighted_at; }

    public static Sighting findById(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id = :id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animal_id, ranger_id, location_id, sighted_at) VALUES (:animal_id, :ranger_id, :location_id, now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("ranger_id", this.ranger_id)
                    .addParameter("location_id", this.location_id)
                    .executeUpdate()
                    .getKey();
        }
    }

}
