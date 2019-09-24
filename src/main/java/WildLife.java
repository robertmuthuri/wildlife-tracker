import org.sql2o.*;
import java.util.Objects;

public abstract class WildLife {
    public int id;
    public String name;
    public String age;
    public String health;
    public String type;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAge() { return age; }
    public String getHealth() { return health; }
    public String getType() { return type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WildLife wildLife = (WildLife) o;
        return  getName().equals(wildLife.getName()) &&
                getType().equals(wildLife.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType());
    }
}


