import static org.junit.Assert.*;
import org.junit.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SightingTest {

    // Setup helper method
    public Sighting setupSighting(){
        return new Sighting(1, 1, 1);
    }
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sighting testSighting = setupSighting();
        assertEquals(true, testSighting instanceof Sighting);
    }
    @Test
    public void sighting_instantiatesWithAnimalId() {
        Sighting testSighting = setupSighting();
        assertEquals(1, testSighting.getAnimal_id());
    }

    @Test
    public void sighting_instantiatesWithRangerId() {
        Sighting testSighting = setupSighting();
        assertEquals(1, testSighting.getRanger_id());
    }

    @Test
    public void sighting_instantiatesWithLocation() {
        Sighting testSighting = setupSighting();
        assertEquals(1, testSighting.getLocation_id());
    }
     @Test
    public void sighting_InstantiatesWithSightingTime() {
        Sighting testSighting = setupSighting();
        testSighting.save();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        Timestamp sightTime = Sighting.findById(testSighting.getId()).getSightedAt();
        assertEquals(rightNow.getDay(), sightTime.getDay());
    }
}