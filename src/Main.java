import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Offer o1 = new Offer("Offer1", 123.23, 50);
        Offer o2 = new Offer("Offer2", 23.45, 26);
        ArrayList<Offer> offers = new ArrayList<Offer>();
        offers.add(o1);
        offers.add(o2);
        Database db = new Database(offers);
        Buyer buyer = new Buyer(10000);
        ArrayList<Client> clients = new ArrayList<Client>();
        clients.add(buyer);

        System.out.println(buyer.printAssets());
        Server server = new Server(db, clients);

        System.out.println(db.printOffers());
        server.operate();
        System.out.println(db.printOffers());
        System.out.println(buyer.printAssets());


    }
}
