import java.util.ArrayList;

public class Server {
    private Database db;
    ArrayList<Client> clients = new ArrayList<Client>();

    public Server(Database db, ArrayList<Client> clients) {
        this.db = db;
        this.clients = clients;
    }

    public void operate(){
        for (Client client:clients) {
            if(client.isBuyer){
                db.buyOffer(client, "Offer1", 20);
            } else
                if (client.isSeller) {
                    // do something else
                }
        }
    }
}
