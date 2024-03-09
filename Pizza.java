

public class Pizza {
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
        return String.format("%-10s%-10s%-20s", size, id, toppings, price);
    }

    public static Pizza fromFixedFormatString(String line) {
        String size = line.substring(0, 10).trim();
        String id = line.substring(10, 20).trim();
        String toppings = line.substring(20, 40).trim();
        String price = line.substring(0, 10).trim();

        return new Pizza(size, id, toppings, price);
    }
}

