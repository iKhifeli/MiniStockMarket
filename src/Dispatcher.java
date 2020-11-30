import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Dispatcher {
    private static CopyOnWriteArrayList<Company> companies;
    private static CopyOnWriteArrayList<Event> events;
    private static Server sv;

    public Dispatcher(List<Company> companies, Server sv) {
        Dispatcher.companies = new CopyOnWriteArrayList<Company>(companies);
        Dispatcher.events = new CopyOnWriteArrayList<Event>();
        Dispatcher.sv = sv;
    }

    public static void sendEvent(Company company, Event e){
        for (Event event : events) {
            if(event.getOffer().getName().equals(e.getOffer().getName())){
                System.out.println("Match!");
            }
        }
    }

    public void registerListener(Buyer buyer, Event e){
        events.add(e);
        //e.getOffer().attachObserver(buyer);
    }

    public void addCompany(Company c){
        companies.add(c);
    }
}
