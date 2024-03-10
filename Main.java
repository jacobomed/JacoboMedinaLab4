/** Project: IST 242 LAB 4
 * Purpose Details: Serialize and Deserialize a Pizza Object and send through flat files, web services, and RabbotMQ
 * Course:IST 242
 * Author: Jacobo Medina
 * Date Developed: March 3, 2024
 * Last Date Changed: March 9, 2024
 * Rev: 2.0 (second application developed after first one got ruined)

 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {

    /**
     * Jacobo Medina
     */
    public static void  main(String[] args) {
        /** Main funtion. Just used to have serialization and deserialization set up
         *
         */
        // Creating an instance of the Pizza class
        Pizza pizza = new Pizza("Medium", "1001", "Plain", "8.99");

        // Serialization: Converting the Pizza object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String pizzaJson = objectMapper.writeValueAsString(pizza);
            System.out.println("Pizza object serialized to JSON string:");
            System.out.println(pizzaJson);

            // Deserialization: Converting the JSON string back to a Pizza object
            Pizza deserializedPizza = objectMapper.readValue(pizzaJson, Pizza.class);
            System.out.println("\nPizza object deserialized from JSON string:");
            System.out.println("Size: " + deserializedPizza.getSize());
            System.out.println("Id: " + deserializedPizza.getId());
            System.out.println("Toppings: " + deserializedPizza.getToppings());
            System.out.println("Price: " + deserializedPizza.getPrice());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}