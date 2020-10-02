import java.util.ArrayList;

public class Database {
    private ArrayList<Offer> offers = new ArrayList<>();

    public Database(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public String printOffers(){
        StringBuilder res = new StringBuilder();
        for (Offer offer:offers) {
            res.append(offer.getName()).append(" ").append(offer.getValue()).append(" ").append(offer.getQuantity()).append("\n");
        }
        return res.toString();
    }

    public void buyOffer(Client client ,String wanted_offer, int wanted_quantity){
        for (Offer offer:offers) {
            if(wanted_offer.equals(offer.getName())){
                if(offer.getQuantity() >= wanted_quantity) {
                    if (client.isBuyer) {
                        if (((Buyer) client).getBalance() >= wanted_quantity * offer.getValue()) {
                            int current_quantity = offer.getQuantity();
                            offer.setQuantity(current_quantity - wanted_quantity);
                            ((Buyer) client).addAsset(offer);
                            if (offer.getQuantity() == 0) {
                                offers.remove(offer);
                            }
                        }
                    }
                }
            }
        }
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
