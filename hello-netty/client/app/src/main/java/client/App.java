package client;

public class App {
    public static void main(String[] args) throws Exception {
        HelloClient helloClient = new HelloClient("127.0.0.1", 9090);
        try {
            helloClient.start();
        }catch (Exception exception){
            System.out.println(exception);
            System.exit(1);
        }
    }
}
