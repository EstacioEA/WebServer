import http.HTTPRequest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.util.concurrent.Executors;

public class Main {

    static final int PORT = 5000;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
    }

    public Main() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT); // Abro el servidor

        System.out.println("Waiting for connection in port "+PORT+"...");

        // Creo mi thread pool para manejar concurrencia
        var webService = Executors.newFixedThreadPool(5);

        while (true) {
            Socket socket = serverSocket.accept(); // Escucho una nueva conexión
            System.out.println("New client connected to server");

            webService.execute(() -> { // Delego responsabilidad del socket al thread pool
                try {
                    userService(socket); // Gestiono socket
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            });
        }

    }

    private static void userService(Socket socket) throws IOException {
        try {
            InputStream is = socket.getInputStream(); // Este es el request
            OutputStream os = socket.getOutputStream(); //Este es el response

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is));

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(os));

            //Leo lo que me mandó el cliente

            String requestLine = br.readLine();
            System.out.println("Server received: " + requestLine);

            // Ahora construimos la request

            String[] request = requestLine.split(" ");
            String method = request[0];
            String resource = request[1];
            String version = request[2];

            HTTPRequest httpRequest = new HTTPRequest(method, resource, version);

            //Respuesta al cliente

            // Inicio de la estructura de una respuesta HTTP

            bw.write("HTTP/1.1 200 OK\r\n");
            bw.write("Content-Type: text/html\r\n");
            bw.write("Content-Length: 34\r\n");
            bw.write("\r\n");

            // Final

            // Recurso a enviar
            bw.write("<html><body>hola mundo</body></html>");
            bw.flush();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            socket.close();
        }
    }
}
