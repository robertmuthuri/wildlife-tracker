import static org.junit.Assert.*;
import org.junit.*;

public class EndangeredAnimalTest {

    //set up endangered animal
    public EndangeredAnimal setupEndangeredAnimal(){
        return new EndangeredAnimal("White Rhino", "healthy", "old");
    }

    //Connect to wildlife_tracker_test database
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void endangeredAnimal_instantiatesCorrectly_true() {
        EndangeredAnimal testEndangeredAnimal = setupEndangeredAnimal();
        assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
    }
    @Test
    public void endangeredAnimal_instantiatesWithName_String() {
        EndangeredAnimal testEndangeredAnimal = setupEndangeredAnimal();
        assertEquals("White Rhino", testEndangeredAnimal.getName());
    }
    @Test
    public void endangeredAnimal_instantiatesWithHealth_String() {
        EndangeredAnimal testEndangeredAnimal = setupEndangeredAnimal();
        assertEquals("healthy", testEndangeredAnimal.getHealth());
    }
    @Test
    public void endangeredAnimal_instantiatesWithAge_String() {
        EndangeredAnimal testEndangeredAnimal = setupEndangeredAnimal();
        assertEquals("old", testEndangeredAnimal.getAge());
    }
    @Test
    public void endangeredAnimal_instantiatesWithType_Endangered() {
        EndangeredAnimal testEndangeredAnimal = setupEndangeredAnimal();
        assertEquals("Endangered", testEndangeredAnimal.getType());
    }
    @Test
    public void equals_returnsTrueIfNameAndTypeAreSame_true() {
        EndangeredAnimal testEndangeredAnimal = setupEndangeredAnimal();
        EndangeredAnimal anotherEndangeredAnimal = setupEndangeredAnimal();
        assertTrue(testEndangeredAnimal.equals(anotherEndangeredAnimal));
    }
    @Test
    public void save_successfullyAddsEndangeredAnimalToDatabase_List() {
        EndangeredAnimal testEndangeredAnimal =  setupEndangeredAnimal();
        testEndangeredAnimal.save();
        assertTrue(EndangeredAnimal.getAll().get(0).equals(testEndangeredAnimal));
    }
    @Test
    public void getAll_returnsAllInstancesOfEndangeredAnimal_false() {
        EndangeredAnimal testEndangeredAnimal =  setupEndangeredAnimal();
        testEndangeredAnimal.save();
        EndangeredAnimal otherEndangeredAnimal = new EndangeredAnimal("Dragon","ill","old");;
        otherEndangeredAnimal.save();
        assertEquals(true, EndangeredAnimal.getAll().get(0).equals(testEndangeredAnimal));
        assertEquals(true, EndangeredAnimal.getAll().get(1).equals(otherEndangeredAnimal));
    }
    @Test
    public void findById_returnsEndangeredAnimalWithSameId() {
        EndangeredAnimal testEndangeredAnimal = setupEndangeredAnimal();
        testEndangeredAnimal.save();
        EndangeredAnimal anotherEndangeredAnimal = new EndangeredAnimal("Dragon","ill","Newborn");
        anotherEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.findById(anotherEndangeredAnimal.getId()), anotherEndangeredAnimal);
    }
    @Test
    public void updateChangesExistingEndangeredAnimal (){
        EndangeredAnimal testEndangeredAnimal = setupEndangeredAnimal();
        testEndangeredAnimal.save();
        EndangeredAnimal.update(testEndangeredAnimal.getId(),"Rhino","ill", "young");
        assertFalse(EndangeredAnimal.findById(testEndangeredAnimal.getId()).getName().equals(testEndangeredAnimal.getName()));
    }
    @Test
    public void deleteRemovesEndangeredAnimalFromDatabase(){
        EndangeredAnimal testEndangeredAnimal = setupEndangeredAnimal();
        testEndangeredAnimal.save();
        EndangeredAnimal otherEndangeredAnimal = setupEndangeredAnimal();
        otherEndangeredAnimal.save();
        otherEndangeredAnimal.delete();
        assertEquals(1,EndangeredAnimal.getAll().size());
    }
}