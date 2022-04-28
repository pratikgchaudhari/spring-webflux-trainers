package server;

public class App {
    public static void main(String[] args) throws Exception {
        HelloServer helloServer = new HelloServer("127.0.0.1", 9090);
        helloServer.start();
    }
}
