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

    @Test
    public void Animal_instantiatesWithAName_String() {
        Animal testAnimal = setupAnimal();
        assertEquals("Rhino", testAnimal.getName());
    }
    @Test
    public void Animal_instantiatesWithType_NonEndangered() {
        Animal testAnimal = setupAnimal();
        assertEquals("Non-Endangered", testAnimal.getType());
    }
    @Test
    public void equals_returnsTrueIfNameAndTypeAreSame_true() {
        Animal testAnimal = setupAnimal();
        Animal anotherAnimal = setupAnimal();
        assertTrue(testAnimal.equals(anotherAnimal));
    }
    @Test
    public void save_successfullyAddsAnimalToDatabase_List() {
        Animal testAnimal =  setupAnimal();
        testAnimal.save();
        assertTrue(Animal.getAll().get(0).equals(testAnimal));
    }
    @Test
    public void findById_returnsAnimalWithSameId() {
        Animal testAnimal = setupAnimal();
        testAnimal.save();
        Animal anotherAnimal = new Animal("Boar");
        anotherAnimal.save();
        assertEquals(Animal.findById(anotherAnimal.getId()), anotherAnimal);
    }
    @Test
    public void update_changesExistingAnimalName_Boar(){
        Animal testAnimal = setupAnimal();
        testAnimal.save();
        Animal.update(testAnimal.getId(), "Boar");
        assertEquals("Boar", Animal.findById(testAnimal.getId()).getName());
    }
}