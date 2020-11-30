public class Event {
    private enum event{
        PRICE_DECREASE,
        PRICE_INCREASE,
        AMOUNT_INCREASE,
        AMOUNT_DECREASE,
        INACTIVE_OFFER,
        ACTIVE_OFFER;
    }
    private double priceLimit;
    private int amountLimit;
    private event eventType;

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
