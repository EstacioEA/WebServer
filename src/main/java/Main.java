import server.WebServer;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        WebServer webServer = new WebServer();
        webServer.start();
    }
}