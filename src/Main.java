import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<Offer> offers1 = new ArrayList<Offer>();
        List<Offer> offers2 = new ArrayList<Offer>();


        Company c1 = new Company("Company", offers1);
        Company c2 = new Company("Other Company", offers2);

        Server server = new Server();

        List<Company> companies = new ArrayList<Company>();
        Dispatcher dispatcher = new Dispatcher(companies, server);



        Offer o1 = new Offer("Colgate", 133.42, 100, "2011");
        Offer o2 = new Offer("BT", 98.32, 100, "2017");
        Offer o3 = new Offer("Lenovo", 32.2, 100, "2013");
        Offer o4 = new Offer("Redragon", 45.32, 100, "1998");

        c1.addOffer(o1);
        c1.addOffer(o2);

        c2.addOffer(o3);
        c2.addOffer(o4);

        Buyer b1 = new Buyer(server, o1, "Gigel");
        Buyer b2 = new Buyer(server, o2, "Marcel");
        Buyer b3 = new Buyer(server, o3, "Cornel");

        dispatcher.registerListener(b1, new Event( b1.getWantedOffer() ,106.32, 56, Event.event.PRICE_DECREASE));
        dispatcher.registerListener(b2, new Event( b2.getWantedOffer() ,109.54, 55, Event.event.PRICE_DECREASE));
        dispatcher.registerListener(b2, new Event( b2.getWantedOffer() ,112.45, 53, Event.event.PRICE_DECREASE));
        dispatcher.registerListener(b3, new Event( b3.getWantedOffer() ,120.31, 52, Event.event.PRICE_DECREASE));

        c1.modifyOffer(o1, 105.33, 52);
        c2.modifyOffer(o3, 105.33, 52);

    }
}
