import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args){
        Offer o1 = new Offer("BT", 133.42, 5);
        Offer o2 = new Offer("Colgate", 98.32, 5);
        List<Offer> offers = new ArrayList<Offer>();
        Database db = new Database(offers);
        Server sv = new Server(db);

        Buyer buyer1 = new Buyer("BT", 5, sv, "Gigel");
        Buyer buyer2 = new Buyer("BT", 5, sv, "Marcel");
        Buyer buyer3 = new Buyer("BT", 5, sv, "Denis");
        Buyer buyer4 = new Buyer("BT", 5, sv, "Danieeeel");
        Seller seller1 = new Seller(o1, sv);
        Seller seller2 = new Seller(o2, sv);

        Thread thread1 = new Thread(seller1);
        Thread thread2 = new Thread(seller2);
        Thread thread3 = new Thread(buyer1);
        Thread thread4 = new Thread(buyer2);
        Thread thread5 = new Thread(buyer3);
        Thread thread6 = new Thread(buyer4);

        thread1.start();
        thread2.start();
        for(int i=0; i<500000; i++) {}
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

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
            thread5.join();
            thread6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sv.printOffers());
        System.out.println(buyer1.getName() + " -> " + buyer1.printAssets());
        System.out.println(buyer2.getName() + " -> " + buyer2.printAssets());
        System.out.println(buyer3.getName() + " -> " + buyer3.printAssets());
        System.out.println(buyer4.getName() + " -> " + buyer4.printAssets());

    }
}
