import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Company extends Client implements Runnable{

    private String name;
    private double balance;
    private final CopyOnWriteArrayList<Offer> offers;
    private final CopyOnWriteArrayList<Buyer> observers;


    public Company(String name, List<Offer> offers, List<Buyer> observers) {
        this.name = name;
        this.offers = new CopyOnWriteArrayList<>(offers);
        this.observers = new CopyOnWriteArrayList<>(observers);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void addOffer(Offer new_offer){
        for (Offer offer : offers) {
            if (offer.getName().equals(new_offer.getName())) {
                return;
            }
        }
        offers.add(new_offer);
    }

    public synchronized void attachObserver(Buyer b) {
        observers.add(b);
    }

    @Override
    public void run() {
//        Server.operate(this);
    }

}
