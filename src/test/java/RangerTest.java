import static org.junit.Assert.*;
import org.junit.*;

public class RangerTest {

    public Ranger setupRanger(){ return new Ranger("Warden"); }

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Ranger testRanger = setupRanger();
        assertEquals(true, testRanger instanceof Ranger);
    }
    @Test
    public void Ranger_instantiatesWithName_String() {
        Ranger testRanger = setupRanger();
        assertEquals("Warden", testRanger.getName());
    }

}