import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Database {
    public String printOffers(){
//        StringBuilder res = new StringBuilder();
//        if(!companies.isEmpty()) {
//            for (Company company : companies) {
//                res.append(offer.getName()).append(", Value: ").append(offer.getValue()).append(", Amount available: ").append(offer.getQuantity()).append("\n");
//            }
//        }
//        return res.toString();
        return "";
    }

    private synchronized void accessOffer(Buyer buyer, int wanted_quantity, Offer offer) {
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
        Offer temp_offer = new Offer(offer.getName(), offer.getValue(), wanted_quantity, offer.getDate());
        buyer.addAsset(temp_offer);
        double newBalance = buyer.getBalance() - (wanted_quantity * offer.getValue());
        buyer.setBalance(newBalance);
        offer.getCompany().setBalance(wanted_quantity * offer.getValue());
        Event new_event;
        if (offer.getQuantity() == 0) {
            new_event = new Event(offer, offer.getValue(), offer.getQuantity(), Event.event.INACTIVE_OFFER);
            offer.getCompany().removeOffer(offer);
        }else{
            new_event = new Event(offer, offer.getValue(), offer.getQuantity(), Event.event.AMOUNT_DECREASE);
        }
        Dispatcher.sendEvent(new_event);
        System.out.println("The buyer " + buyer.getName() + " has purchased a number of " + wanted_quantity + " of " + offer.getName() + "-----------------------------------------------");

        offer.setAvailability(true);
        notify();
    }

    public boolean buyOffer(Buyer buyer, Offer wanted_offer, int wanted_quantity){
        if (wanted_offer.getQuantity() >= wanted_quantity){
           synchronized(wanted_offer){
               if (buyer.getBalance() >= wanted_quantity * wanted_offer.getValue()) {
                    accessOffer(buyer, wanted_quantity, wanted_offer);
                    return true;
               }else{
                   System.out.println("The buyer " + buyer.getName() + " does not have enough money for the quantity of shares he wants");
                   return false;
               }
           }
        }else{
            System.out.println("The buyer " + buyer.getName() + " wants to buy a larger amount of shares that the ones available");
            return false;
        }
    }
}
