import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server implements Interface {

    private static final String ENGLISH = "[a-zA-Z]*";
    private static final String RUSSIAN = "[а-яА-ЯёЁ]*";
    private static Pattern patternR, patternE;
    private static Matcher matcherR, matcherE;

    public Server() {}

    public String sayHello(String letters) {

        boolean ELet = false;
        boolean RLet = false;
        int length = letters.length();

        if(length==2){

            patternR = Pattern.compile(RUSSIAN);
            patternE = Pattern.compile(ENGLISH);

            matcherR = patternR.matcher(letters);
            matcherE = patternE.matcher(letters);

                if(matcherR.matches()){
                    return "Russian alph";
                }
                else {

                    if(matcherE.matches()){
                        return "English alph";
                    }

                    char [] strLet = letters.toCharArray(); 
                    for(int i = 0; i < strLet.length; i++) {

                        matcherE = patternE.matcher(Character.toString(strLet[i]));
                        matcherR = patternR.matcher(Character.toString(strLet[i]));

                        if(matcherE.matches() && !matcherR.matches()){
                            ELet = true;
                        }
                        if(!matcherE.matches() && matcherR.matches()){
                            RLet = true;
                        }
                    }

                    if(ELet && RLet){
                        return "Russian and English alph";
                    }
                    else{
                        return "You should input only letters";
                    }
                }
        }
       else {
            return "You should input 2 letters";
        }
    }

    public static void main(String args[]) {
        try {
            Server obj = new Server();

            Interface stub =(Interface) UnicastRemoteObject.exportObject(obj, 0); 
            Registry registry = LocateRegistry.createRegistry(1087);
            registry.bind("Interface", stub); 

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
