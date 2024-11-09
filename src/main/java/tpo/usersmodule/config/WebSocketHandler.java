package tpo.usersmodule.config;  // Asegúrate de que este sea el paquete correcto

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tpo.usersmodule.model.entity.Producto;
import tpo.usersmodule.model.entity.Venta;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Esta clase extiende TextWebSocketHandler para manejar las conexiones WebSocket
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper
    private final Map<String, String> userSessions = new HashMap<>();

    // Método para obtener todas las sesiones (exponer las sesiones)
    public Map<String, WebSocketSession> getSessions() {
        return sessions;
    }

    // Este método se llama cuando se establece una conexión WebSocket
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Aquí puedes realizar cualquier acción después de que el cliente se conecta
        sessions.put(session.getId(), session);
        System.out.println("Conexión WebSocket establecida: " + session.getId());
    }

    // Este método maneja los mensajes que recibe el servidor desde el cliente
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Aquí puedes procesar el mensaje recibido desde el cliente
        System.out.println("Mensaje recibido: " + message.getPayload());
        if (message.getPayload().startsWith("USER:")) {
            String userId = message.getPayload().substring(5);  // Extraemos el ID de usuario
            userSessions.put(session.getId(), userId);  // Asociamos el ID con la sesión WebSocket
            System.out.println("Usuario autenticado con ID: " + userId);
        }
        // Responder al cliente con un mensaje
        //session.sendMessage(new TextMessage("Mensaje recibido: " + message.getPayload()));
    }

    // Este método se llama cuando se cierra la conexión WebSocket
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        // Eliminar la WebSocketSession cuando el cliente se desconecta
        sessions.remove(session.getId());
        userSessions.remove(session.getId());  // También eliminamos la asociación con el usuario
        System.out.println("Conexión WebSocket cerrada: " + session.getId());
    }

    // Método para enviar productos al cliente usando su sessionId
    public void sendProducts(String sessionId, List<Producto> products) throws IOException {
        WebSocketSession session = sessions.get(sessionId);  // Obtener la sesión usando el sessionId
        if (session != null && session.isOpen()) {
            // Si la sesión está abierta, enviamos el mensaje con los productos
            String productsJson = objectMapper.writeValueAsString(products);
            System.out.println(productsJson);
            // Enviar los productos como un mensaje JSON al cliente
            session.sendMessage(new TextMessage(productsJson));
        } else {
            System.out.println("No se pudo encontrar la sesión o la sesión está cerrada.");
        }
    }

    // Método para enviar ventas a un usuario específico
    public void sendSalesToUser(String userId, List<Venta> sales) throws IOException {
        // Buscar la sesión WebSocket asociada con el userId
        for (Map.Entry<String, String> entry : userSessions.entrySet()) {
            if (entry.getValue().equals(userId)) {
                WebSocketSession session = sessions.get(entry.getKey());
                if (session != null && session.isOpen()) {
                    // Convertimos la lista de ventas a JSON
                    String salesJson = objectMapper.writeValueAsString(sales);
                    System.out.println(salesJson);

                    // Enviamos el JSON con las ventas al usuario específico
                    session.sendMessage(new TextMessage(salesJson));
                    System.out.println("Enviando ventas a usuario con ID: " + userId);
                }
            }
        }
    }

}
