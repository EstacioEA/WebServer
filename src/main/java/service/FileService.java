package service;

// El index y esta clase fueron realizadas con ayuda de IA

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {
    // Ruta base
    private static final String BASE_PATH = "src/main/resources";

    public static byte[] readFile(String resource) throws IOException {
        // Validar que la ruta empiece con /
        if (!resource.startsWith("/")) {
            throw new IOException("Recurso debe empezar con /");
        }

        // Remover el / inicial y construir la ruta segura
        String filename = resource.substring(1); // Ej: "index.html"
        Path filePath = Paths.get(BASE_PATH, filename); // Ej: "src/main/resources/index.html"

        // Validación de seguridad: evitar que accedan a "../" para salirse del directorio
        File file = filePath.toFile();
        if (!file.getCanonicalPath().startsWith(new File(BASE_PATH).getCanonicalPath())) {
            throw new IOException("Acceso denegado: intento de traversal de directorios");
        }

        // Si el archivo no existe, lanzar excepción
        if (!Files.exists(filePath)) {
            throw new IOException("Archivo no encontrado: " + resource);
        }

        // Leer y retornar el contenido
        return Files.readAllBytes(filePath);
    }


    public static String getContentType(String resource) {
        if (resource.endsWith(".html")) {
            return "text/html";
        } else if (resource.endsWith(".jpg") || resource.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (resource.endsWith(".gif")) {
            return "image/gif";
        } else if (resource.endsWith(".png")) {
            return "image/png";
        } else if (resource.endsWith(".txt")) {
            return "text/plain";
        } else if (resource.endsWith(".css")) {
            return "text/css";
        } else if (resource.endsWith(".js")) {
            return "application/javascript";
        }
        // Por defecto, octet-stream (descarga)
        return "application/octet-stream";
    }
}