import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Send {
    private final static String QUEUE_NAME = "pizza";

    public static void main(String[] argv) throws Exception {
        // Initialize ObjectMapper
        Pizza pizza = new Pizza("Medium", "1001", "Plain", "8.99");

        // Serialization: Converting the student object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String pizzaJson = objectMapper.writeValueAsString(pizza);
        System.out.println("main.Pizza object serialized to JSON string:");
        System.out.println(pizzaJson);

        // Define your message or provide a sample JSON string to deserialize


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