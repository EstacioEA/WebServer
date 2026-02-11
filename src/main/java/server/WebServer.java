package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class WebServer {
    private static final int PORT = 5000;
    private static final int THREAD_POOL_SIZE = 5;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public WebServer() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        System.out.println("Servidor escuchando en puerto " + PORT + "...");
    }

    public void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");
                threadPool.execute(new HTTPHandler(socket));
            } catch (IOException e) {
                System.err.println("Error aceptando conexión: " + e.getMessage());
            }
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
        threadPool.shutdown();
    }
}
