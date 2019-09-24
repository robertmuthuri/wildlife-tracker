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

}