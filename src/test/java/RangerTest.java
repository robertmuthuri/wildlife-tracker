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
    @Test
    public void equals_returnsTrueIfNameAndTypeAreSame_true() {
        Ranger testRanger = setupRanger();
        Ranger anotherRanger = setupRanger();
        assertTrue(testRanger.equals(anotherRanger));
    }
    @Test
    public void save_successfullyAddsRangerToDatabase_List() {
        Ranger testRanger =  setupRanger();
        testRanger.save();
        assertTrue(Ranger.getAll().get(0).equals(testRanger));
    }
    @Test
    public void getAll_returnsAllInstancesOfRanger_false() {
        Ranger testRanger =  setupRanger();
        testRanger.save();
        Ranger otherRanger = new Ranger("John");;
        otherRanger.save();
        assertEquals(true, Ranger.getAll().get(0).equals(testRanger));
        assertEquals(true, Ranger.getAll().get(1).equals(otherRanger));
    }
    @Test
    public void find_returnsRangerWithSameId() {
        Ranger testRanger = setupRanger();
        testRanger.save();
        Ranger anotherRanger = new Ranger("John");
        anotherRanger.save();
        assertEquals(Ranger.findById(anotherRanger.getId()), anotherRanger);
    }
    @Test
    public void update_changesExistingRangerName_true(){
        Ranger testRanger = setupRanger();
        testRanger.save();
        Ranger.update(testRanger.getId(), "Wangombe");
        assertEquals("Wangombe", Ranger.findById(testRanger.getId()).getName());
    }
    @Test
    public void deleteRemovesRangerFromDatabase(){
        Ranger testRanger = setupRanger();
        testRanger.save();
        Ranger otherRanger = setupRanger();
        otherRanger.save();
        otherRanger.delete();
        assertEquals(1,Ranger.getAll().size());
    }

}