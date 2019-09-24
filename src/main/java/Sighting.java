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

    public Sighting() {
    }
}
