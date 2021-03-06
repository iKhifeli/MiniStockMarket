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
                    switch(event.getEventType()){
                        case ACTIVE_OFFER:
                            event.getBuyer().startThread();
                            events.remove(event);
                            System.out.println("    EVENT REMOVED! - " + event.getEventType().toString() + " from " + event.getBuyer().getName());
                            break;
                        case INACTIVE_OFFER:
                            System.out.println("Offer " + event.getOffer().getName() + " is not available any more!");
                            break;
                        case PRICE_INCREASE:
                            if(event.getPriceLimit() <= event.getOffer().getValue()){
                                event.getBuyer().startThread();
                                events.remove(event);
                                System.out.println("    EVENT REMOVED! - " + event.getEventType().toString() + " from " + event.getBuyer().getName());
                            } else {
                                System.out.println("EVENT NOT REMOVED! Price did not increased enough for offer " + event.getOffer().getName() + " for " + event. getBuyer().getName());
                            }
                            break;
                        case PRICE_DECREASE:
                            if(event.getPriceLimit() >= event.getOffer().getValue()){
                                event.getBuyer().startThread();
                                events.remove(event);
                                System.out.println("    EVENT REMOVED! - " + event.getEventType().toString() + " from " + event.getBuyer().getName());
                            } else {
                                System.out.println("EVENT NOT REMOVED! Price did not decreased enough for offer " + event.getOffer().getName() + " for " + event.getBuyer().getName());
                            }
                            break;
                        case AMOUNT_DECREASE:
                            if(event.getAmountLimit() >= event.getOffer().getQuantity()){
                                event.getBuyer().startThread();
                                events.remove(event);
                                System.out.println("    EVENT REMOVED! - " + event.getEventType().toString() + " from " + event.getBuyer().getName());
                            } else {
                                System.out.println("EVENT NOT REMOVED! Amount did not decrease enough for offer "  + event.getOffer().getName() + " for " + event.getBuyer().getName());
                            }
                            break;
                    }
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
