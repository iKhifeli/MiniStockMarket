import java.util.ArrayList;

public class Server {
    private Database db;

    public Server(Database db) {
        this.db = db;
    }

    public synchronized void operate(Client client){
        if(db.isAvailable()){
            System.out.println("DB is available for trading!");
            db.makeUnavailable();
            System.out.println("DB is unavailable because one client is currently trading");
            if(client.isBuyer){
                System.out.println("The client is a buyer");
                if(((Buyer)client).wantsToTrade()){
                    System.out.println("The buyer wants to trade");
                    if(db.buyOffer(client, ((Buyer) client).getWantedOffer(), ((Buyer) client).getWantedQuantity())){ // this is SUBJECT TO CHANGE
                        ((Buyer)client).doesNotWantToTradeAnymore();
                        System.out.println("The buyer has bought an offer and does not want to trade anymore");
                        db.makeAvailable();
                        System.out.println("DB is available again!");
                        return;
                    }
                }
                System.out.println("The buyer does not want to trade right now!");
            }
            else{
                System.out.println("The client is a seller");
                if(((Seller)client).wantsToTrade()) {
                    System.out.println("The seller wants to list his offer");
                    if(db.listNewOffer(((Seller) client).getOffer())){
                        ((Seller)client).doesNotWantToTradeAnymore();
                        System.out.println("The seller has successfully list his offer");
                        db.makeAvailable();
                        System.out.println("DB is available again!");
                        return;
                    }
                    System.out.println("The offer already exists");
                }
                System.out.println("Seller does not want to trade!");
            }
        }
        if(!db.isAvailable()) {
            db.makeAvailable();
            System.out.println("DB is available again!");
        }
    }

    public String printOffers(){
        return db.printOffers();
    }
}
