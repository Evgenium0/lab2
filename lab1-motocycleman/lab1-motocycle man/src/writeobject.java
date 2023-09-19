import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class writeobject {
    public static void main(String[] args){
        motocycleman motocycleman1 = new motocycleman(1, "helmet");
        motocycleman motocycleman2 = new motocycleman(2, "jacket");
        motocycleman motocycleman3 = new motocycleman(3, "pants");
        motocycleman motocycleman4 = new motocycleman(4, "glaves");
        motocycleman motocycleman5 = new motocycleman(5, "boots");

        try {
            FileOutputStream fos = new FileOutputStream("amb.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(motocycleman1);
            oos.writeObject(motocycleman2);
            oos.writeObject(motocycleman3);
            oos.writeObject(motocycleman4);
            oos.writeObject(motocycleman5);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
