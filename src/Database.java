import java.util.ArrayList;

public class Database {
    private boolean available;
    private ArrayList<Offer> offers = new ArrayList<>();

    public Database(ArrayList<Offer> offers) {
        this.offers = offers;
        available = true;
    }

    public Boolean isAvailable(){
        return this.available;
    }

    public void makeUnavailable(){
        this.available = false;
    }

    public void makeAvailable(){
        this.available = true;
    }

    public String printOffers(){
        StringBuilder res = new StringBuilder();
        for (Offer offer:offers) {
            res.append(offer.getName()).append(" ").append(offer.getValue()).append(" ").append(offer.getQuantity()).append("\n");
        }
        return res.toString();
    }

    public boolean buyOffer(Client client, String wanted_offer, int wanted_quantity){
        for (Offer offer:offers) {
            if(wanted_offer.equals(offer.getName())){
                if(offer.getQuantity() >= wanted_quantity) {
                    if (client.isBuyer) {
                        if (((Buyer) client).getBalance() >= wanted_quantity * offer.getValue()) {
                            int current_quantity = offer.getQuantity();
                            offer.setQuantity(current_quantity - wanted_quantity);
                            ((Buyer) client).addAsset(offer);
                            double newBalance = ((Buyer) client).getBalance() - (wanted_quantity * offer.getValue());
                            ((Buyer) client).setBalance(newBalance);
                            if (offer.getQuantity() == 0) {
                                offers.remove(offer);
                            }
                            System.out.println("The buyer has purchased a number of " + wanted_quantity + " of " + offer.getName());
                            return true;
                        }
                        System.out.println("The buyer does not have enough money for the quantity of shares he wants");
                        return false;
                    }
                }
                System.out.println("The buyer wants to buy a larger amount of shares that the ones available");
                return false;
            }
            System.out.println("The buyer did not find something he liked");
        }
        return false;
    }

    public void sellOffer(Client client, Offer new_offer){
        for (Offer offer:offers) {
            if(new_offer.getName().equals(offer.getName())){
                return;
            }
        }
        offers.add(new_offer);
    }



}
