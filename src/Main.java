import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        Offer o4 = new Offer("BT", 133.42, 5);
        ArrayList<Offer> offers = new ArrayList<>();
        Database db = new Database(offers);
        Server sv = new Server(db);

        Buyer buyer1 = new Buyer("BT", 5, sv, "Gigel");
        Buyer buyer2 = new Buyer("BT", 5, sv, "Marcel");
        Seller seller1 = new Seller(o4, sv);

        Thread thread1 = new Thread(seller1);
        Thread thread3 = new Thread(buyer1);
        Thread thread4 = new Thread(buyer2);

        thread1.start();
        for(int i=0; i<50000; i++) {}
        thread3.start();
        thread4.start();

        /*
        while(thread.isAlive() || thread1.isAlive()){
            // waiting for threads to end their execution
        }
        */
        // or
        try {
            thread1.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sv.printOffers());
    }
}
