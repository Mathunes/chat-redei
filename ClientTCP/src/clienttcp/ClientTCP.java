package clienttcp;

public class ClientTCP {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        
        if (args.length > 0)
            try {
                host = args[0];
                port = Integer.parseInt(args[1]);
            } catch (Exception e) {}
            
        LoginWindow frame = new LoginWindow(host, port);
        frame.setVisible(true);
    }
    
}
