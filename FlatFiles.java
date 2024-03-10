/** Project: IST 242 LAB 4
 * Purpose Details: Serialize and Deserialize a Pizza Object and send through flat files, web services, and RabbotMQ
 * Course:IST 242
 * Author: Jacobo Medina
 * Date Developed: March 3, 2024
 * Last Date Changed: March 9, 2024
 * Rev: 2.0 (second application developed after first one got ruined)

 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Jacobo Medina
 */
public class FlatFiles {
/**
 * FLat files class. Creates a txt file with the pizzas that are being stored
 */


        public static void main(String[] args) {
            // Example data
            List<Pizza> pizzas = new ArrayList<>();
            pizzas.add(new Pizza("Medium", "1001", "Plain", "8.99"));
            pizzas.add(new Pizza("Large", "1002", "Pepperoni", "10.99"));

            // Write pizzas to a flat file
            try (PrintWriter writer = new PrintWriter(new FileWriter("pizzas.txt"))) {
                for (Pizza pizza : pizzas) {
                    writer.println(pizza.toFixedFormatString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Read pizzas from the flat file
            List<Pizza> loadedPizzas = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("pizzas.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    loadedPizzas.add(Pizza.fromFixedFormatString(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Display loaded pizzas
            for (Pizza pizza : loadedPizzas) {
                System.out.println("Size: " + pizza.getSize() + ", ID: " + pizza.getId() + ", Toppings: " + pizza.getToppings() + ", Price: " + pizza.getPrice());
                System.out.println("Fixed Format String: " + pizza.toFixedFormatString());
            }
        }
    }
