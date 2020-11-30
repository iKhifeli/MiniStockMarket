public class Event {
    public enum event{
        PRICE_DECREASE,
        PRICE_INCREASE,
        INACTIVE_OFFER,
        ACTIVE_OFFER;
    }
    private double priceLimit;
    private int amountLimit;
    private event eventType = event.ACTIVE_OFFER;
    private Offer offer;

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

    public void setPriceLimit(double priceLimit) {
        this.priceLimit = priceLimit;
    }

    public int getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(int amountLimit) {
        this.amountLimit = amountLimit;
    }

    public event getEventType() {
        return eventType;
    }

    public void setEventType(event eventType) {
        this.eventType = eventType;
    }
}
