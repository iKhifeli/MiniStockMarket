import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Offer o1 = new Offer("Intel", 122.22, 500);
        Offer o2 = new Offer("Apple", 129.22, 500);
        Offer o3 = new Offer("Netflix", 123.42, 500);
        Offer o4 = new Offer("BT", 133.42, 500);
        Offer o5 = new Offer("BCR", 137.42, 500);
        ArrayList<Offer> offers = new ArrayList<Offer>();
        offers.add(o1);
        offers.add(o2);
        offers.add(o3);
        Database db = new Database(offers);
        Server sv = new Server(db);

        ArrayList<Thread> threads = new ArrayList<Thread>();

        Buyer buyer1 = new Buyer("BT", 5, sv);
        Buyer buyer2 = new Buyer("BT", 5, sv);
        Seller seller1 = new Seller(o4, sv);
        Seller seller2 = new Seller(o5, sv);

        Thread thread1 = new Thread(seller1);
        Thread thread2 = new Thread(seller2);
        Thread thread3 = new Thread(buyer1);
        Thread thread4 = new Thread(buyer2);

        threads.add(thread1);
        threads.add(thread2);
        threads.add(thread3);
        threads.add(thread4);

        for (Thread t: threads) {
            t.start();
        }

        /*
        while(thread.isAlive() || thread1.isAlive()){
            // waiting for threads to end their execution
        }
        */
        // or
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sv.printOffers());
    }
}
