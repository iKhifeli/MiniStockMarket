import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Dispatcher {
    private CopyOnWriteArrayList<Company> companies;
    private static Server sv;

    public Dispatcher(List<Company> companies, Server sv) {
        this.companies = new CopyOnWriteArrayList<>(companies);
        this.sv = sv;
    }

    public void sendEvent(Event e, Company c){

    }

    public void registerListener(Event e, Offer offer){

    }

    public void addCompany(Company c){
        companies.add(c);
    }
}
