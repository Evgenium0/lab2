import javax.naming.*;
import java.rmi.RemoteException;
import java.util.Enumeration;

public class Client {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        Enumeration<NameClassPair> e= context.list("emi://localhost/");
        while (e.hasMoreElements());
            System.out.println(e.nextElement().getName());

            Motocycleman motocycleman = (Motocycleman)context.lookup("rmi://localhost/Motocycleman");
            int one = motocycleman.add(1);
            String ht = motocycleman.add("helmet");
            int two = motocycleman.add(2);
            String jt = motocycleman.add("jakcet");
            int three = motocycleman.add(3);
            String pants = motocycleman.add("pants");
            int four = motocycleman.add(4);
            String gs = motocycleman.add("gloves");
            int five = motocycleman.add(5);
            String bs = motocycleman.add("boots");

            System.out.println(one + ht);
            System.out.println(two + jt);
            System.out.println(three + pants);
            System.out.println(four + gs);
            System.out.println(five + bs);

    }
}
