import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {

    public static void  main(String[] args) {
        // Creating an instance of the Student class
        Pizza pizza = new Pizza("Medium", "1001", "Plain", "8.99");

        // Serialization: Converting the student object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String pizzaJson = objectMapper.writeValueAsString(pizza);
            System.out.println("main.Pizza object serialized to JSON string:");
            System.out.println(pizzaJson);

            // Deserialization: Converting the JSON string back to a student object
            Pizza deserializedPizza = objectMapper.readValue(pizzaJson, Pizza.class);
            System.out.println("\nmain.Pizza object deserialized from JSON string:");
            System.out.println("Size: " + deserializedPizza.getSize());
            System.out.println("Id: " + deserializedPizza.getId());
            System.out.println("Toppings: " + deserializedPizza.getToppings());
            System.out.println("Price: " + deserializedPizza.getPrice());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}