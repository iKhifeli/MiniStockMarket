public class Server {
    private static Database db;

    //TODO: Warning:(5, 9) Static member 'Server.db' accessed via instance reference
    public Server(Database db) {
        this.db = db;
    }

//    public static void operate(Client client){
//        if(client.isBuyer){
//            System.out.println("The client " + client.getName() + " is a buyer");
//            if(client.wantsToTrade()){
//                System.out.println("The buyer " + client.getName() + " is looking for " + ((Client) client).getWantedOffer() + " offer");
//                if(db.buyOffer(client, ((Client) client).getWantedOffer(), ((Client) client).getWantedQuantity())){ // this is SUBJECT TO CHANGE
//                    client.doesNotWantToTradeAnymore();
//                    System.out.println("The buyer " + client.getName() + " has bought an offer and does not want to trade anymore");
//                }
//            }
//        } else {
//            System.out.println("The seller wants to list his offer");
//            if(db.listNewOffer(((Company) client).getOffer())){
//                return;
//            }
//            System.out.println("The offer already exists");
//            System.out.println("Seller does not want to trade!");
//        }
//    }

    String printOffers(){
        return db.printOffers();
    }
}
