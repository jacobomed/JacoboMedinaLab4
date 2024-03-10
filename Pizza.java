/** Project: IST 242 LAB 4
 * Purpose Details: Serialize and Deserialize a Pizza Object and send through flat files, web services, and RabbotMQ
 * Course:IST 242
 * Author: Jacobo Medina
 * Date Developed: March 3, 2024
 * Last Date Changed: March 9, 2024
 * Rev: 2.0 (second application developed after first one got ruined)

 */


/**
 * Jacobo Medina
 */
public class Pizza {
/**
 * Pizza class. Has the constructor, setters, and getters. USed to construct the pizzas that are beings ent over as JSON
 * files
 */

    public String size;
    public String id;
    public String toppings;
    public String price;

    // Default constructor
    public Pizza() {
    }

    // Constructors, getters, setters
    public Pizza(String size, String id, String toppings, String price) {
        this.size = size;
        this.id = id;
        this.toppings = toppings;
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }




    // Constructors, getters, setters
    public String toFixedFormatString() {
        return String.format("%-10s%-10s%-20s%-10s", size, id, toppings, price);
    }

    public static Pizza fromFixedFormatString(String line) {
        String size = line.substring(0, 10).trim();
        String id = line.substring(10, 20).trim();
        String toppings = line.substring(20, 40).trim();
        String price = line.substring(40, 50).trim();

        return new Pizza(size, id, toppings, price);
    }
}

