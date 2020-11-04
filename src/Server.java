import java.util.ArrayList;

public class Server {
    private Database db;

    public Server(Database db) {
        this.db = db;
    }

    public void operate(Client client){
        if(client.isBuyer){
            System.out.println("The client " + ((Buyer)client).getName() + " is a buyer");
            if(((Buyer)client).wantsToTrade()){
                System.out.println("The buyer " + ((Buyer)client).getName() + " is looking for " + ((Buyer) client).getWantedOffer() + " offer");
                if(db.buyOffer(client, ((Buyer) client).getWantedOffer(), ((Buyer) client).getWantedQuantity())){ // this is SUBJECT TO CHANGE
                    ((Buyer)client).doesNotWantToTradeAnymore();
                    System.out.println("The buyer " + ((Buyer)client).getName() + " has bought an offer and does not want to trade anymore");
                }
            }
        } else {
            System.out.println("The client is a seller");
            if(((Seller)client).wantsToTrade()) {
                System.out.println("The seller wants to list his offer");
                if(db.listNewOffer(((Seller) client).getOffer())){
                    ((Seller)client).doesNotWantToTradeAnymore();
                    return;
                }
                System.out.println("The offer already exists");
            }
            System.out.println("Seller does not want to trade!");
        }
    }

    String printOffers(){
        return db.printOffers();
    }
}
