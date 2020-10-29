import java.util.ArrayList;
import java.util.Random;

public abstract class Client {
    boolean isBuyer = false;
    boolean isSeller = false;
    boolean wantsToTrade = false;
    protected String name;
    Server server;

    public abstract boolean wantsToTrade();
    public abstract void doesNotWantToTradeAnymore();
    public abstract String getName();
}

class Buyer extends Client implements Runnable{
    private Random rand = new Random();
    private boolean wantsToTrade;
    private String wantedOffer;
    private int wantedQuantity;
    private double balance = 1 + (10000 - 1) * rand.nextDouble(); // the Buyer will get a random amount of currency between 1 and 10000
    private ArrayList<Offer> assets = new ArrayList<Offer>();

    public Buyer(String wantedOffer, int wantedQuantity, Server server, String name) {
        this.wantedOffer = wantedOffer;
        this.wantedQuantity = wantedQuantity;
        isBuyer = true;
        isSeller = false;
        //wantsToTrade = rand.nextBoolean();
        wantsToTrade = true;
        this.server = server;
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

    public String getWantedOffer() {
        return wantedOffer;
    }

    public int getWantedQuantity() {
        return wantedQuantity;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance){
        balance = newBalance;
    }

    @Override
    public boolean wantsToTrade(){
        return this.wantsToTrade;
    }
    @Override
    public void doesNotWantToTradeAnymore(){
        if(wantsToTrade){
            wantsToTrade=false;
        }
    }

    // The buyer will add to its essets and offer after the purchase;
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

    @Override
    public void run() {
        server.operate(this);
    }
}

class Seller extends Client implements Runnable{
    private Random rand = new Random();
    private Offer offer;
    private double balance = 0;

    public Seller(Offer offer, Server server, String name) {
        isBuyer = false;
        isSeller = true;
        this.offer = offer;
        //wantsToTrade = rand.nextBoolean();
        wantsToTrade = true;
        this.server = server;
        offer.setSeller(this);
        this.name = name;
    }
    public boolean wantsToTrade(){
        return this.wantsToTrade;
    }

    public void doesNotWantToTradeAnymore(){
        if(wantsToTrade){
            wantsToTrade = false;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public Offer getOffer(){
        return offer;
    }

    public void setBalance(double acquity){
        balance = balance + acquity;
    }

    public double getBalance(){
        return balance;
    }

    @Override
    public void run() {
        server.operate(this);
    }
}
