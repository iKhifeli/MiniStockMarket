import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Offer {
    private enum state{
        INACTIVE,
        ACTIVE,
        PRICE_UPDATED,
        AMOUNT_UPDATED;
    }
    private String name;
    private double value;
    private int quantity;
    private String date;
    private Company company;
    private boolean availability = true;
    private state offer_state = state.INACTIVE;

    public Offer(String name, double value, int quantity, String date) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.date = date;
    }

    public synchronized state getState() {
        return offer_state;
    }

    public synchronized void setState(state offer_state) {
        this.offer_state = offer_state;
    }

    public synchronized String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public synchronized double getValue() {
        return value;
    }

    public synchronized void setValue(double value) {
        this.value = value;
    }

    public synchronized int getQuantity() {
        return quantity;
    }

    public synchronized void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public synchronized boolean hasAvailability() {
        return availability;
    }

    public synchronized void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String toString(){
        return name + ", " + value + ", " + quantity + "| ";
    }

    /*
    public synchronized void attachObserver(Buyer buyer){
        company.attachObserver(buyer);
    }
     */
}

