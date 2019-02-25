import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    private Client() {}
    public static void main(String[] args) {

        Scanner in ;
        String letter;

        try {
            Registry registry = LocateRegistry.getRegistry(1087);

            while(true){
                System.out.println("Enter 2 letters:");

                in = new Scanner(System.in);
                letter = in.nextLine();
                Interface stub = (Interface) registry.lookup("Interface"); 

                String response = stub.sayHello(letter);
                System.out.println("response: " + response);
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
