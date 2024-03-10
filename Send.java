/** Project: IST 242 LAB 4
 * Purpose Details: Serialize and Deserialize a Pizza Object and send through flat files, web services, and RabbotMQ
 * Course:IST 242
 * Author: Jacobo Medina
 * Date Developed: March 3, 2024
 * Last Date Changed: March 9, 2024
 * Rev: 2.0 (second application developed after first one got ruined)

 */


import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Jacobo Medina
 */
public class Send {

/**
 * sets up to send JSON string as well as deserialized version as a message to Receive class. IF receive class isn't
 * running then it will leave the message in queue for whenever it is started
 */
    private final static String QUEUE_NAME = "pizza";

    public static void main(String[] argv) throws Exception {
        // Initialize ObjectMapper
        Pizza pizza = new Pizza("Medium", "1001", "Plain", "8.99");

        // Serialization
        ObjectMapper objectMapper = new ObjectMapper();
        String pizzaJson = objectMapper.writeValueAsString(pizza);
        System.out.println("main.Pizza object serialized to JSON string:");
        System.out.println(pizzaJson);


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Specify the hostname or IP address of the RabbitMQ server
        factory.setPort(5672); // Specify the port number of the RabbitMQ server (default is 5672)

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Deserialize the message into a Pizza object
            Pizza deserializedPizza = objectMapper.readValue(pizzaJson, Pizza.class);

            // Construct the message
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("\nPizza object deserialized from JSON string:\n");
            messageBuilder.append(pizzaJson);
            messageBuilder.append("Size: ").append(deserializedPizza.getSize()).append("\n");
            messageBuilder.append("Id: ").append(deserializedPizza.getId()).append("\n");
            messageBuilder.append("Toppings: ").append(deserializedPizza.getToppings()).append("\n");
            messageBuilder.append("Price: ").append(deserializedPizza.getPrice()).append("\n");

            // Convert the StringBuilder to a String
            String message = messageBuilder.toString();

            // Send the JSON string over RabbitMQ
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent " + pizzaJson + message);
        }
    }}