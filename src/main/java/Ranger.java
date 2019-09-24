import java.util.Objects;

public class Ranger {

    String name;
    private int id;

    public Ranger(String name) {
        this.name = name;

    }
    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ranger location = (Ranger) o;
        return getName().equals(location.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
