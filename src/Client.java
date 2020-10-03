import java.util.ArrayList;
import java.util.Random;

public abstract class Client {
    boolean isBuyer = false;
    boolean isSeller = false;
    boolean wantsToTrade = false;

    public abstract boolean wantsToTrade();
    public abstract void doesNotWantToTradeAnymore();
}

class Buyer extends Client{
        private Random rand = new Random();
        private boolean wantsToTrade;
        private double balance = 1 + (10000 - 1) * rand.nextDouble(); // the Buyer will get a random amount of currency between 1 and 10000
        private ArrayList<Offer> assets = new ArrayList<>();

    public Buyer() {
        isBuyer=true;
        isSeller=false;
        wantsToTrade=rand.nextBoolean();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance){
        balance = newBalance;
    }

    public boolean wantsToTrade(){
        return this.wantsToTrade;
    }
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
}

class Seller extends Client{
    private Random rand = new Random();
    private Offer offer;

    public Seller(Offer offer) {
        isBuyer=false;
        isSeller=true;
        this.offer = offer;
        wantsToTrade=rand.nextBoolean();
    }
    public boolean wantsToTrade(){
        return this.wantsToTrade;
    }
    public void doesNotWantToTradeAnymore(){
        if(wantsToTrade){
            wantsToTrade=false;
        }
    }
}
