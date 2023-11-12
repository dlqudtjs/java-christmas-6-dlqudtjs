package christmas.model;

public class BookingInfo {

    private final Order order;
    private final VisitDate visitDate;

    public BookingInfo(Order order, VisitDate visitDate) {
        this.order = order;
        this.visitDate = visitDate;
    }

    public Order getOrder() {
        return order;
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }
}
