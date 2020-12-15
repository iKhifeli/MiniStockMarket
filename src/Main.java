import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<Offer> offers1 = new ArrayList<Offer>();
        List<Offer> offers2 = new ArrayList<Offer>();


        Company c1 = new Company("Company", offers1);
        Company c2 = new Company("Other Company", offers2);

        Dispatcher dispatcher = new Dispatcher();

        Offer o1 = new Offer("Colgate", 130, 100, "2011");
        Offer o2 = new Offer("BT", 100, 100, "2017");
        Offer o3 = new Offer("Lenovo", 30, 100, "2013");
        Offer o4 = new Offer("Redragon", 50, 100, "1998");

        c1.addOffer(o1);
        c1.addOffer(o2);

        c2.addOffer(o3);
        c2.addOffer(o4);

        Database db = new Database();

        Buyer b1 = new Buyer(o1, 50, "Gigel", db);
        Buyer b2 = new Buyer(o2, 50, "Marcel", db);
        Buyer b3 = new Buyer(o3, 50,"Cornel", db);
        Buyer b4 = new Buyer(o4, 50,"Marinel", db);

        dispatcher.registerListener(b1, new Event( b1.getWantedOffer() ,106.32, 0, Event.event.PRICE_DECREASE));
        dispatcher.registerListener(b1, new Event( b1.getWantedOffer() ,0, 90, Event.event.AMOUNT_DECREASE));
        dispatcher.registerListener(b2, new Event( b2.getWantedOffer() ,109.54, 0, Event.event.PRICE_DECREASE));
        dispatcher.registerListener(b3, new Event( b3.getWantedOffer() ,35.11, 0, Event.event.PRICE_INCREASE));
        dispatcher.registerListener(b4, new Event( b4.getWantedOffer() ,0, 95, Event.event.AMOUNT_DECREASE));

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        t1.start();t2.start();
        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e) {
            System.out.println(e.toString());
        }

        System.out.println("--------------------------------------------");
        System.out.println("Number of unhandled events left : " + dispatcher.numberOfUnhandledEvents());
        System.out.println("--------------------------------------------");
        System.out.println(c1.getName());
        System.out.println(c1.toString());
        System.out.println("--------------------------------------------");
        System.out.println(c2.getName());
        System.out.println(c2.toString());

    }
}
