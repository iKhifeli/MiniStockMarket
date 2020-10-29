public class Offer {

    private String name;
    private double value;
    private int quantity;
    private Seller seller;
    public boolean availability = true;

    public Offer(String name, double value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public synchronized double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public synchronized int getQuantity() {
        return quantity;
    }

    public synchronized void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Seller getSeller() {
        return seller;
    }

    public synchronized void setSeller(Seller seller) {
        this.seller = seller;
    }

    public synchronized boolean hasAvailability() {
        return availability;
    }

    public synchronized void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
