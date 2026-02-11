import server.WebServer;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        WebServer webServer = new WebServer();
        webServer.start();
    }

    //Para ejecutar el programa ingrese a las siguientes URLS:
    // http://localhost:5000/index.html
    // http://localhost:5000/imagenEjemplo.jpg
    // http://localhost:5000/imagenEjemplo2.jpg

    // o en su defecto: http://localhost:5000
    // Este último debería dar error 404
}