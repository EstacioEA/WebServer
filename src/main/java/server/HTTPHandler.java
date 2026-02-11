package server;

import http.*;

import java.io.*;
import java.net.Socket;

public class HTTPHandler implements Runnable {
    private Socket socket;

    public HTTPHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os))){

            //Aquí manejo el socket del cliente.

            // Leo la solicitud
            String requestLine = br.readLine();
            System.out.println("Request: " + requestLine);

            String header;
            while ((header = br.readLine()) != null && !header.isEmpty()) {
                System.out.println(header);
            }

            // creamos la solicitud HTTP
            HTTPRequest httpRequest = parseRequest(requestLine);

            // Genero respuesta (response)
            HTTPResponse httpResponse = new HTTPResponse(
                    200,
                    "OK",
                    "text/html",
                    "<html><body>hola mundo</body></html>".getBytes()
            );

            // Envío respuesta
            sendResponse(bw, httpResponse);
        } catch (IOException e) {
            System.err.println("Error manejando solicitud: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error cerrando socket: " + e.getMessage());
            }
        }
    }

    private HTTPRequest parseRequest(String requestLine) {
        String[] parts = requestLine.split(" ");
        String method = parts[0];
        String resource = parts[1];
        String httpVersion = parts[2];

        return new HTTPRequest(method, resource, httpVersion);
    }

    private void sendResponse(BufferedWriter bw, HTTPResponse response) throws IOException {
        // Línea de estado
        bw.write("HTTP/1.0 " + response.getStatusCode() + " " + response.getStatusMessage() + "\r\n");

        // Headers
        bw.write("Content-Type: " + response.getContentType() + "\r\n");
        bw.write("Content-Length: " + response.getContentLength() + "\r\n");
        bw.write("\r\n");

        // Cuerpo
        bw.flush();
        socket.getOutputStream().write(response.getBody());
        socket.getOutputStream().flush();
    }
}
