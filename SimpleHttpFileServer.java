import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.InetSocketAddress;

public class SimpleHttpFileServer {
    public static void main(String[] args) throws IOException {
        int port = 8081;
        String directory = args.length > 0 ? args[0] : ".";
        
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String filePath = directory + exchange.getRequestURI().getPath();
                if (Files.exists(Paths.get(filePath)) && !Files.isDirectory(Paths.get(filePath))) {
                    byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
                    exchange.sendResponseHeaders(200, fileBytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(fileBytes);
                    os.close();
                } else {
                    String response = "404 (Not Found)\n";
                    exchange.sendResponseHeaders(404, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            }
        });
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started at http://localhost:" + port);
    }
}
