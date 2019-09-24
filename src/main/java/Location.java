import java.util.Objects;

public class Location {

    private String name;
    private int id;

    public Location(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public int getId() { return id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return getName().equals(location.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}
