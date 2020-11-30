import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        List<Offer> offers = new ArrayList<Offer>();
        Database db = new Database(offers);
        //Dispatcher d = new Dispatcher();

        System.out.println("\nBuyers and what the managed to buy :");

        System.out.println("------------------------------------------------");
        System.out.println("\nLeft over offers (they appeared to late in the 'data base' (Empty if none)) : \n");
        System.out.println(db.printOffers());
        System.out.println("------------------------------------------------");

        System.out.println("Sellers budget after they sold their offers: \n");

    }
}
