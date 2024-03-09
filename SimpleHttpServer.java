

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/pizza", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Pizza pizza = new Pizza("Medium", "1001", "Pepperoni", "8.99");

            ObjectMapper objectMapper = new ObjectMapper();
            String pizzaJson = "";
            Pizza deserializedPizza = null; // Declare outside the try block

            try {
                pizzaJson = objectMapper.writeValueAsString(pizza);
                System.out.println("main.Pizza object serialized to JSON string:");
                System.out.println(pizzaJson);

                // Construct response 1 after JSON serialization
                String response1 = "Pizza JSON: " + pizzaJson;

                // Deserialization: Converting the JSON string back to a student object
                deserializedPizza = objectMapper.readValue(pizzaJson, Pizza.class);
                System.out.println("\nmain.Pizza object deserialized from JSON string:");
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

