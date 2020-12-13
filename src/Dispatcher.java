import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Dispatcher {
    private static CopyOnWriteArrayList<Event> events;

    public Dispatcher() {
        Dispatcher.events = new CopyOnWriteArrayList<Event>();
    }

    public static void eventHandler(Event e){
        for (Event event : events) {
            if(event.getOffer().getName().equals(e.getOffer().getName())){
                if(event.getEventType() == e.getEventType()){
                    Database db = new Database();
                    switch(event.getEventType()){
                        case ACTIVE_OFFER:
                            //db.buyOffer(event.getBuyer(), event.getOffer(), event.getBuyer().getWantedQuantity());
                            event.getBuyer().startThread();
                            break;
                        case INACTIVE_OFFER:
                            System.out.println("Offer " + event.getOffer().getName() + " is not available any more!");
                            break;
                        case PRICE_INCREASE:
                            if(event.getPriceLimit() <= event.getOffer().getValue()){
                                //db.buyOffer(event.getBuyer(), event.getOffer(), event.getBuyer().getWantedQuantity());
                                event.getBuyer().startThread();
                            }
                            break;
                        case PRICE_DECREASE:
                            if(event.getPriceLimit() >= event.getOffer().getValue()){
                                //db.buyOffer(event.getBuyer(), event.getOffer(), event.getBuyer().getWantedQuantity());
                                event.getBuyer().startThread();
                            }
                            break;
                        case AMOUNT_DECREASE:
                            if(event.getAmountLimit() >= event.getOffer().getQuantity()){
                                //db.buyOffer(event.getBuyer(), event.getOffer(), event.getBuyer().getWantedQuantity());
                                event.getBuyer().startThread();
                            }
                            break;
                    }
                    events.remove(event);
                    System.out.println("Event removed! - " + event.getBuyer().getName());
                }
            }
        }
    }

    public void registerListener(Buyer buyer, Event e){
        events.add(e);
        e.setBuyer(buyer);
    }

    public int numberOfUnhandledEvents(){
        return events.size();
    }

}
