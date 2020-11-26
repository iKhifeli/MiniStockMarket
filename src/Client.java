import java.util.ArrayList;
import java.util.Random;

public abstract class Client {
    String name;
    double balance;
    abstract String getName();
    abstract void setBalance(double newBalance);
    abstract double getBalance();
}

class Buyer extends Client implements Runnable{
    private Random rand = new Random();
    private boolean wantsToTrade;
    private String wantedOffer;
    private int wantedQuantity;
    private ArrayList<Offer> assets = new ArrayList<Offer>();

    public Buyer(String wantedOffer, int wantedQuantity, Server server, String name) {
        this.wantedOffer = wantedOffer;
        this.wantedQuantity = wantedQuantity;
        balance = 1 + (10000 - 1) * rand.nextDouble();
        //wantsToTrade = rand.nextBoolean();
        wantsToTrade = true;
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

    @Override
    public void run() {
//        Server.operate(this);
    }
}
