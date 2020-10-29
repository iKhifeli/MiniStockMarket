import java.util.Collections;
import java.util.List;


public class Database {
    private List<Offer> offers;

    public Database(List<Offer> offers) {
        this.offers = Collections.synchronizedList(offers);
    }

    public String printOffers(){
        StringBuilder res = new StringBuilder();
        if(!offers.isEmpty()) {
            for (Offer offer : offers) {
                res.append(offer.getName()).append(" ").append(offer.getValue()).append(" ").append(offer.getQuantity()).append("\n");
            }
        }
        return res.toString();
    }

    private synchronized void accessOffer(Client client, int wanted_quantity, Offer offer) {
        while(!offer.hasAvailability()){
            try{
                wait();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted" + e);
            }
        }
        offer.setAvailability(false);

        int current_quantity = offer.getQuantity();
        offer.setQuantity(current_quantity - wanted_quantity);
        Offer temp_offer = new Offer(offer.getName(), offer.getValue(), wanted_quantity);
        ((Buyer) client).addAsset(temp_offer);
        double newBalance = ((Buyer) client).getBalance() - (wanted_quantity * offer.getValue());
        ((Buyer) client).setBalance(newBalance);
        //offer.getSeller().setBalance(wanted_quantity * offer.getValue()); // TODO: think about this
        if (offer.getQuantity() == 0) {
            offers.remove(offer);
        }
        System.out.println("The buyer " + ((Buyer)client).getName() + " has purchased a number of " + wanted_quantity + " of " + offer.getName() + "-----------------------------------------------");

        offer.setAvailability(true);
        notify();
    }

    public boolean buyOffer(Client client, String wanted_offer, int wanted_quantity){
        synchronized (offers) {
            for (Offer offer : offers) {
                if (wanted_offer.equals(offer.getName())) {
                    if (offer.getQuantity() >= wanted_quantity) {
                        if (((Buyer) client).getBalance() >= wanted_quantity * offer.getValue()) {
                            accessOffer(client, wanted_quantity, offer);
                            return true;
                        }
                        System.out.println("The buyer " + ((Buyer) client).getName() + " does not have enough money for the quantity of shares he wants");
                        return false;
                    }
                    System.out.println("The buyer " + ((Buyer) client).getName() + " wants to buy a larger amount of shares that the ones available");
                    return false;
                }
            }
            System.out.println("The buyer " + ((Buyer) client).getName() + " did not find something he liked: " + wanted_offer);
            return false;
        }
    }

    public boolean listNewOffer(Offer new_offer){
        synchronized (offers) {
            for (Offer offer : offers) {
                if (offer.getName().equals(new_offer.getName())) {
                    return false;
                }
            }
        }
        offers.add(new_offer);
        System.out.println("The new offer " + new_offer.getName() + " has been added to DB :)");
        return true;
    }
}
