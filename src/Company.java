import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Company extends Client implements Runnable{

    private String name;
    private double balance;
    private final List<Offer> offers;
    //private final List<Buyer> observers;


    public Company(String name, List<Offer> offers) {
        this.name = name;
        this.offers = new CopyOnWriteArrayList<>(offers);
        //this.observers = new CopyOnWriteArrayList<>();
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

    public void modifyOffer(Offer offer, double value, int quantity){
        if (offers.contains(offer)){
            double aux_val = offer.getValue();
            offer.setQuantity(quantity);
            offer.setValue(value);
            Event.event event;
            if(quantity == 0){
                event = Event.event.INACTIVE_OFFER;
            }
            else if(aux_val > value){
                event = Event.event.PRICE_DECREASE;
            }
            else {
                event = Event.event.PRICE_INCREASE;
            }
            Event new_event = new Event(offer, value, quantity, event);

            Dispatcher.sendEvent(new_event);
        }
    }

    public synchronized void addOffer(Offer new_offer){
        for (Offer offer : offers) {
            if (offer.getName().equals(new_offer.getName())) {
                return;
            }
        }
        offers.add(new_offer);
        new_offer.setCompany(this);

        Event new_event = new Event(new_offer, new_offer.getValue(), new_offer.getQuantity(), Event.event.ACTIVE_OFFER);

        Dispatcher.sendEvent(new_event);
    }

    public synchronized void removeOffer(Offer offer){
        offers.remove(offer);
    }

    @Override
    public void run() {
//        Server.operate(this);
    }

}
