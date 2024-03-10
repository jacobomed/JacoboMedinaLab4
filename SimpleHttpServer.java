/** Project: IST 242 LAB 4
 * Purpose Details: Serialize and Deserialize a Pizza Object and send through flat files, web services, and RabbotMQ
 * Course:IST 242
 * Author: Jacobo Medina
 * Date Developed: March 3, 2024
 * Last Date Changed: March 9, 2024
 * Rev: 2.0 (second application developed after first one got ruined)

 */

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Jacobo Medina
 */
public class SimpleHttpServer {

/**
 * Creates an HTTP server in order to receive the web service class. Receives the serialized pizza and prints out the
 * JSON String as well as the deserialized objects
 */
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/pizza", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Pizza pizza = new Pizza("Medium", "1001", "Pepperoni", "8.99");

            ObjectMapper objectMapper = new ObjectMapper();
            String pizzaJson = "";
            Pizza deserializedPizza = null;

            try {
                pizzaJson = objectMapper.writeValueAsString(pizza);
                System.out.println("Pizza object serialized to JSON string:");
                System.out.println(pizzaJson);

                // Construct response 1 after JSON serialization
                String response1 = "Pizza JSON: " + pizzaJson;

                // Deserialization: Converting the JSON string back to a pizza object
                deserializedPizza = objectMapper.readValue(pizzaJson, Pizza.class);
                System.out.println("\nPizza object deserialized from JSON string:");
                System.out.println("Size: " + deserializedPizza.getSize());
                System.out.println("Id: " + deserializedPizza.getId());
                System.out.println("Toppings: " + deserializedPizza.getToppings());
                System.out.println("Price: " + deserializedPizza.getPrice());

                String response2 = "Deserialized Pizza: " + deserializedPizza;

                // Send the responses
                OutputStream os = exchange.getResponseBody();
                exchange.sendResponseHeaders(200, response1.getBytes().length);
                os.write(response1.getBytes());
                os.flush(); // Flush the OutputStream to ensure the data is sent immediately

                exchange.sendResponseHeaders(200, response2.getBytes().length);
                os.write(response2.getBytes());
                os.flush(); // Flush the OutputStream again

                os.close();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }}}

