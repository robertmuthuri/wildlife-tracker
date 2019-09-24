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
    public void findById_returnsSightingWithSameId() {
        Sighting testSighting = setupSighting();
        testSighting.save();
        Sighting anotherSighting = new Sighting(1, 10, 10);
        anotherSighting.save();
        assertEquals(Sighting.findById(anotherSighting.getId()), anotherSighting);

    }
     @Test
    public void sighting_InstantiatesWithSightingTime() {
        Sighting testSighting = setupSighting();
        testSighting.save();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        Timestamp sightTime = Sighting.findById(testSighting.getId()).getSightedAt();
        assertEquals(rightNow.getDay(), sightTime.getDay());
    }
    @Test
    public void save_successfullyAddsSightingToDatabase_List() {
        Sighting testSighting = setupSighting();
        testSighting.save();
        assertTrue(Sighting.getAll().get(0).equals(testSighting));
    }
    @Test
    public void equals_returnsTrueIfAnimalRangerLocationAndSightingTimeAreSame_true() {
        Sighting testSighting = setupSighting();
        Sighting anotherSighting = setupSighting();
        assertTrue(testSighting.equals(anotherSighting));
    }
    @Test
    public void getAll_returnsAllInstancesOfSighting_false() {
        Sighting testSighting = setupSighting();
        testSighting.save();
        Sighting otherSighting = new Sighting(1, 2, 3);
        otherSighting.save();
        assertEquals(true, Sighting.getAll().get(0).equals(testSighting));
        assertEquals(true, Sighting.getAll().get(1).equals(otherSighting));
    }
    @Test
    public void updateChangesExistingSighting() {
        Sighting testSighting = setupSighting();
        testSighting.save();
        Sighting.update(testSighting.getId(), 2, 2, 3);
        assertFalse(Sighting.findById(testSighting.getId()).getAnimal_id()==(testSighting.getAnimal_id()));
    }
}