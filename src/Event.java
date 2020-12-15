public class Event {
    public enum event{
        PRICE_DECREASE,
        PRICE_INCREASE,
        AMOUNT_DECREASE,
        INACTIVE_OFFER,
        ACTIVE_OFFER;
    }
    private double priceLimit;
    private int amountLimit;
    private event eventType;
    private Offer offer;
    private Buyer buyer;

    public Event(Offer offer, double priceLimit, int amountLimit, event eventType){
        this.amountLimit = amountLimit;
        this.priceLimit = priceLimit;
        this.eventType = eventType;
        this.offer = offer;
    }

    public Offer getOffer() {
        return offer;
    }

    public double getPriceLimit() {
        return priceLimit;
    }

    public int getAmountLimit() {
        return amountLimit;
    }

    public event getEventType() {
        return eventType;
    }

    public Buyer getBuyer() { return buyer; }

    public void setBuyer(Buyer buyer) { this.buyer = buyer; }
}
