import static org.junit.Assert.*;
import org.junit.*;

public class SightingTest {

    // Setup helper method
    public Sighting setupSighting(){
        return new Sighting();
    }
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sighting testSighting = setupSighting();
        assertEquals(true, testSighting instanceof Sighting);
    }
}