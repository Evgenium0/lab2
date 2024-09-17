import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class readobjact {
    public static void main(String[] args){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("amb.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            motocycleman motocycleman1 = (motocycleman) ois.readObject();
            motocycleman motocycleman2 = (motocycleman) ois.readObject();
            motocycleman motocycleman3 = (motocycleman) ois.readObject();
            motocycleman motocycleman4 = (motocycleman) ois.readObject();
            motocycleman motocycleman5 = (motocycleman) ois.readObject();
            System.out.println(motocycleman1);
            System.out.println(motocycleman2);
            System.out.println(motocycleman3);
            System.out.println(motocycleman4);
            System.out.println(motocycleman5);

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
