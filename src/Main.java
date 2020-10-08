import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){

        Client buyer = new Buyer();

        Offer o1 = new Offer("Offer1", 100, 500);
        Offer o2 = new Offer("Offer2", 2345.55, 500);
        Offer o3 = new Offer("Offer3", 101.2, 500);
        Client seller = new Seller(o3);
        ArrayList<Client> clients = new ArrayList<Client>();
        clients.add(seller);
        ArrayList<Offer> offers = new ArrayList<Offer>();
        offers.add(o1);offers.add(o2);
        Database db = new Database(offers);
        Server server = new Server(db, clients);

        //server.operate(buyer);
        server.operate(seller);
        System.out.println(db.printOffers());

    }
}
