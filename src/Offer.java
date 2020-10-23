public class Offer {

    private String name;
    private double value;
    private int quantity;
    private Seller seller;

    public Offer(String name, double value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    public synchronized void requestAccess(Client client) {
        ((Buyer)client).setAccess(true);
    }

    public void denyAccess(Client client) {
        ((Buyer)client).setAccess(false);
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
