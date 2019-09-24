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
    private int sightedAt;

    public Sighting(int animal_id, int ranger_id, int location_id) {
        this.animal_id = animal_id;
        this.ranger_id = ranger_id;
        this.location_id = location_id;
    }

    public int getId() { return id; }
    public int getAnimal_id() { return animal_id; }
    public int getRanger_id() { return ranger_id; }
    public int getLocation_id() { return location_id; }
    public int getSightedAt() { return sightedAt; }
}
