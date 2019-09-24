import static org.junit.Assert.*;
import org.junit.*;

public class AnimalTest {

    //set up animal
    public Animal setupAnimal() { return new Animal("Rhino"); }

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = setupAnimal();
        assertEquals(true, testAnimal instanceof Animal);
    }
}