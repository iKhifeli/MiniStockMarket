import java.util.ArrayList;

public abstract class Client {
    boolean isBuyer = false;
    boolean isSeller = false;

}

class Buyer extends Client{
    private int balance;
    private ArrayList<Offer> assets = new ArrayList<>();

    public Buyer(int balance) {
        isBuyer=true;
        isSeller=false;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

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
    private Offer offer;

    public Seller(Offer offer) {
        isBuyer=false;
        isSeller=true;
        this.offer = offer;
    }

}
