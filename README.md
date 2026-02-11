# Servidor Web Multihilo en Java

Un servidor web HTTP/1.0 multihilo desarrollado en Java como proyecto académico. El servidor es capaz de manejar múltiples conexiones simultáneas, servir archivos estáticos (HTML, imágenes) y responder apropiadamente ante errores.

##  Características

-  **Multihilo**: Maneja hasta 5 conexiones simultáneas
-  **HTTP/1.0**: Interpreta solicitudes GET correctamente
-  **Servicio de Archivos**: Sirve HTML, JPG, GIF y otros tipos MIME
-  **Manejo de Errores**: Responde con 404 para recursos inexistentes
-  **Validación HTTP**: Valida solicitudes y responde con errores apropiados
-  **Puerto Configurable**: El puerto puede cambiarse fácilmente

## Estructura del Proyecto

```
WebServer/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/webserver/
│   │   │       ├── Main.java                    # Punto de entrada
│   │   │       ├── server/
│   │   │       │   └── WebServer.java           # Gestión del servidor
│   │   │       ├── handler/
│   │   │       │   └── HttpRequestHandler.java  # Procesamiento de solicitudes
│   │   │       ├── http/
│   │   │       │   ├── HttpRequest.java         # Representación de solicitud
│   │   │       │   └── HttpResponse.java        # Representación de respuesta
│   │   │       └── service/
│   │   │           └── FileService.java         # Lectura de archivos
│   │   └── resources/
│   │       ├── index.html                       # Página principal
│   │       ├── imagen1.jpg                      # Imagen de ejemplo
│   │       └── imagen2.gif                      # Imagen de ejemplo
│   └── test/                                    # Tests (si aplica)
├── pom.xml                                      # Configuración Maven
└── README.md                                    # Este archivo
```

## Cómo Ejecutar

### Desde IntelliJ IDEA

1. Abre el proyecto en IntelliJ
2. Localiza la clase `Main.java` en `src/main/java/`
3. Click derecho → **Run 'Main.main()'**
4. El servidor debería mostrar:
   ```
   Servidor escuchando en puerto 5000...
   ```

## 📡 Cómo Usar el Servidor

Una vez que el servidor esté ejecutándose, puedes hacer solicitudes HTTP desde:

### Cliente 1: Navegador Web

Abre tu navegador y accede a:

```
http://localhost:5000/index.html
```

### Cliente 2: `curl` (Terminal)

```bash
# Solicitud a un archivo HTML
curl http://localhost:5000/index.html

# Solicitud a una imagen
curl http://localhost:5000/imagenEjemplo.jpg -o imagenEjemplo2.jpg

# Solicitud a un recurso inexistente (retorna 404)
curl http://localhost:5000 (o cualquier otra que no cuente con un archivo en resources)
```


## Agregar Archivos a Servir

Simplemente coloca tus archivos en la carpeta `src/main/resources/`:

```
src/main/resources/
├── index.html
├── imagen1.jpg
├── imagen2.gif
└── styles.css          ← Nuevo archivo
```

## Arquitectura del Código

```
Main
  ↓
WebServer (aceptar conexiones, thread pool)
  ↓
HttpRequestHandler (procesar cada solicitud en un thread)
  ↓
  ├─ HttpRequest (parsear y validar solicitud)
  ├─ FileService (leer archivos, determinar MIME)
  └─ HttpResponse (generar respuesta HTTP)
```

- [RFC 1945 - HTTP/1.0](https://tools.ietf.org/html/rfc1945)
- [Java NIO y Sockets](https://docs.oracle.com/javase/tutorial/networking/)
- [ExecutorService y Thread Pools](https://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html)
