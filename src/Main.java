import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<Offer> offers1 = new ArrayList<Offer>();
        List<Offer> offers2 = new ArrayList<Offer>();


        Company c1 = new Company("Company", offers1);
        Company c2 = new Company("Other Company", offers2);

        //List<Company> companies = new ArrayList<Company>();
        Dispatcher dispatcher = new Dispatcher();


        //@TODO : decide wether the date stays or it goes away
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
        dispatcher.registerListener(b2, new Event( b2.getWantedOffer() ,109.54, 0, Event.event.PRICE_DECREASE));
        dispatcher.registerListener(b3, new Event( b3.getWantedOffer() ,120.31, 0, Event.event.PRICE_DECREASE));
        dispatcher.registerListener(b4, new Event( b4.getWantedOffer() ,120.31, 0, Event.event.PRICE_DECREASE));
        dispatcher.registerListener(b1, new Event( b1.getWantedOffer() ,170.32, 0, Event.event.PRICE_INCREASE));
        dispatcher.registerListener(b2, new Event( b2.getWantedOffer() ,110.54, 0, Event.event.PRICE_INCREASE));
        dispatcher.registerListener(b3, new Event( b3.getWantedOffer() ,200.11, 0, Event.event.PRICE_INCREASE));
        dispatcher.registerListener(b4, new Event( b4.getWantedOffer() ,145.90, 0, Event.event.PRICE_INCREASE));
        dispatcher.registerListener(b1, new Event( b1.getWantedOffer() ,0, 95, Event.event.AMOUNT_DECREASE));
        dispatcher.registerListener(b2, new Event( b2.getWantedOffer() ,0, 95, Event.event.AMOUNT_DECREASE));
        dispatcher.registerListener(b3, new Event( b3.getWantedOffer() ,0, 95, Event.event.AMOUNT_DECREASE));
        dispatcher.registerListener(b4, new Event( b4.getWantedOffer() ,0, 95, Event.event.AMOUNT_DECREASE));

        /*
        c1.modifyOffer(o1, 105.33, 52);
        c1.modifyOffer(o2, 95.31, 50);
        c2.modifyOffer(o3, 30, 52);
        */

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        t1.start();t2.start();
        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            System.out.println(e.toString());
        }
//        b1.joinThread();
//        b2.joinThread();
//        b3.joinThread();
//        b4.joinThread();

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
