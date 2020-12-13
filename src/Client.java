import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Client {
    String name;
    double balance;
    Random rand = new Random();
    abstract String getName();
    abstract void setBalance(double newBalance);
    abstract double getBalance();
}

class Company extends Client implements Runnable{

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
            Event.event event = Event.event.ACTIVE_OFFER;
            if(quantity == 0){
                event = Event.event.INACTIVE_OFFER;
            }
            else if (offer.getQuantity() > quantity){
                event = Event.event.AMOUNT_DECREASE;
                offer.setQuantity(quantity);
            }
            else if(offer.getValue() > value){
                event = Event.event.PRICE_DECREASE;
                offer.setValue(value);
            }
            else if(offer.getValue() < value){
                event = Event.event.PRICE_INCREASE;
                offer.setValue(value);
            }
            else {
                System.out.println("Amount should not increase!");
            }

            Event new_event = new Event(offer, value, quantity, event);

            Dispatcher.eventHandler(new_event);
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

        Dispatcher.eventHandler(new_event);
    }

    public synchronized void removeOffer(Offer offer){
        offers.remove(offer);
    }

    @Override
    public void run() {
        int tries = rand.nextInt(4);
        while( tries > 0) {
            // we pick a random index from the offers array
            int randOfferIndex = rand.nextInt(offers.size());
            // we get the instance of the offer from the index we randomly selected
            Offer tmpOffer = offers.get(randOfferIndex);
            // We randomly increase or decrease the price of the random picked offer OR we decrease its amount.
            if (rand.nextBoolean()) {
                // if we are on this branch, we will either increase the offer's price by 10% or decrease it by 10%
                if (rand.nextBoolean()) {
                    modifyOffer(tmpOffer, tmpOffer.getValue() + 0.1 * tmpOffer.getValue(), tmpOffer.getQuantity());
                } else {
                    modifyOffer(tmpOffer, tmpOffer.getValue() - 0.1 * tmpOffer.getValue(), tmpOffer.getQuantity());
                }
            } else {
                // if we are on this branch, we will decrease the offer's amount by 5 units
                modifyOffer(tmpOffer, tmpOffer.getValue(), tmpOffer.getQuantity() - 5);
            }
            tries --;
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (Offer o : offers) {
            res += o.toString();
        }
        return res;
    }
}

class Buyer extends Client implements Runnable{
    private Random rand = new Random();
    private Offer wantedOffer;
    private int wantedQuantity;
    private ArrayList<Offer> assets = new ArrayList<Offer>();
    private Database db;
    Thread thread = new Thread(this);

    public Buyer(Offer wantedOffer, int wantedQuantity, String name, Database db) {
        balance = 1 + (10000 - 1) * rand.nextDouble();
        this.wantedOffer = wantedOffer;
        this.wantedQuantity = wantedQuantity;
        //wantsToTrade = rand.nextBoolean();
        this.name = name;
        this.db = db;
    }

    public Offer getWantedOffer() {
        return wantedOffer;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance){
        balance = newBalance;
    }
/*
    public int getWantedQuantity() {
        return wantedQuantity;
    }

    public void setWantedQuantity(int wantedQuantity) {
        this.wantedQuantity = wantedQuantity;
    }
*/
    // The buyer will add to its assets and offer after the purchase;
    public void addAsset(Offer offer){
        assets.add(offer);
    }
    public String printAssets(){
        StringBuilder res = new StringBuilder();
        for (Offer offer:assets) {
            res.append(offer.getName()).append(" ");
        }
        return res.toString();
    }

    public void startThread(){
        thread.start();
    }

    public void joinThread(){
        try{
            thread.join();
        }catch(InterruptedException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void run() {
        db.buyOffer(this, wantedOffer, wantedQuantity);
    }
}
