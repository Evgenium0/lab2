import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.util.Enumeration;

public class Client {
    public static void main(String[] args) throws NamingException {
        Context context = new InitialContext();

        Enumeration<NameClassPair> e = context.list("rmi://localhost");
        while (e.hasMoreElements())
            System.out.println(e.nextElement().getName());

        Server.MotoImpl motoimpl = (Server.MotoImpl)context.lookup("rmi://localhost");
        int result = Server.MotoImpl.add(1);
        String res = Server.MotoImpl.add("helmet");
        System.out.println(result);
        System.out.println(res);
    }

}