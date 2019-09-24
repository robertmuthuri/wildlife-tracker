import static org.junit.Assert.*;
import org.junit.*;

public class LocationTest {

    // Setup helper method
    public Location setupLocation(){
        return new Location("Zone A");
    }
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Location testLocation = setupLocation();
        assertEquals(true, testLocation instanceof Location);
    }
    @Test
    public void Location_instantiatesWithName_String() {
        Location testLocation = setupLocation();
        assertEquals("Zone A", testLocation.getName());
    }
    @Test
    public void equals_returnsTrueIfNameAndTypeAreSame_true() {
        Location testLocation = setupLocation();
        Location anotherLocation = setupLocation();
        assertTrue(testLocation.equals(anotherLocation));
    }
    @Test
    public void save_successfullyAddsLocationToDatabase_List() {
        Location testLocation =  setupLocation();
        testLocation.save();
        assertTrue(Location.getAll().get(0).equals(testLocation));
    }
}