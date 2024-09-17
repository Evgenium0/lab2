import java.io.Serializable;

public class motocycleman implements Serializable {
    private int num;
    private String name;

    public motocycleman(int num, String name ) {
       this.num = num;
        this.name = name;

    }

    public int num() {
        return num;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "motocycleman: "  +  num  + " " + name ;
    }
}

