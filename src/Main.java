import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){

        Client buyer = new Buyer("Offer4", 20);

        Offer o1 = new Offer("Offer1", 100, 500);
        Offer o2 = new Offer("Offer2", 2345.55, 500);
        Offer o3 = new Offer("Offer3", 101.2, 500);
        Offer o4 = new Offer("Offer4", 121.2, 500);
        Client seller = new Seller(o4);
        ArrayList<Client> clients = new ArrayList<Client>();
        clients.add(seller);
        ArrayList<Offer> offers = new ArrayList<Offer>();
        offers.add(o1);offers.add(o2);offers.add(o3);
        Database db = new Database(offers);
        Server server = new Server(db, clients);


        System.out.println(((Buyer)buyer).getBalance());
        System.out.println(((Seller)seller).getBalance());
        System.out.println("--------------------");
        server.operate(buyer);
        System.out.println("--------------------");
        server.operate(seller);
        System.out.println("--------------------");
        server.operate(buyer);
        System.out.println("--------------------");
        System.out.println(db.printOffers());
        System.out.println("--------------------");
        System.out.println(((Buyer)buyer).getBalance());
        System.out.println(((Seller)seller).getBalance());

    }
}
