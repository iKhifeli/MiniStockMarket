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
    private Offer wantedOffer;
    private int wantedQuantity;
    private ArrayList<Offer> assets = new ArrayList<Offer>();

    public Buyer(Offer wantedOffer, int wantedQuantity, String name) {
        balance = 1 + (10000 - 1) * rand.nextDouble();
        this.wantedOffer = wantedOffer;
        this.wantedQuantity = wantedQuantity;
        //wantsToTrade = rand.nextBoolean();
        this.name = name;
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

    public int getWantedQuantity() {
        return wantedQuantity;
    }

    public void setWantedQuantity(int wantedQuantity) {
        this.wantedQuantity = wantedQuantity;
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
