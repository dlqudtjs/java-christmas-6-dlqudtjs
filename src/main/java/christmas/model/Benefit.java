package christmas.model;

import christmas.model.enums.GiveawayType;

public class Benefit {

    private final Order order;
    private final VisitDate visitDate;

    public Benefit(Order order, VisitDate visitDate) {
        this.order = order;
        this.visitDate = visitDate;
    }

    public Giveaway getGiveaway(GiveawayType giveawayType) {
        return new Giveaway(giveawayType, order.getTotalPrice());
    }
}
