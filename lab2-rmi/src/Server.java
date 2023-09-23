import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server{
    public static void main(String args[]) throws NamingException, RemoteException {

        Context context = new InitialContext();
        context.bind("rmi://localhost:1099/motoman",new MotoImpl());
    }
interface motoman extends Remote {
        int add(int num) throws RemoteException;
        String add(String name) throws RemoteException;
}
static class MotoImpl extends UnicastRemoteObject implements motoman {
    protected MotoImpl() throws RemoteException {
    }

    @Override
    public String add(String name) {
        return name;
    }
    public int add(int num){
        return num;
    }
}
}
