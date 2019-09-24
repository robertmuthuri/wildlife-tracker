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
}