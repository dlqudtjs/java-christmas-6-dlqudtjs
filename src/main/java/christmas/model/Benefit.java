package christmas.model;

public class Benefit {

    private final Order order;
    private final VisitDate visitDate;

    public Benefit(Order order, VisitDate visitDate) {
        this.order = order;
        this.visitDate = visitDate;
    }
}
