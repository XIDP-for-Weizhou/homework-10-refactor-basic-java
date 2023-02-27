package practice3;


import java.math.BigDecimal;
import java.util.List;

public class Order {

    private final List<OrderLineItem> orderLineItemList;
    private final List<BigDecimal> discounts;
    private final BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = BigDecimal.valueOf(0.1);
    }

    public BigDecimal calculate() {
        return new PriceCaculator(this).calculate();
    }

    public List<OrderLineItem> getOrderLineItemList() {
        return orderLineItemList;
    }

    public List<BigDecimal> getDiscounts() {
        return discounts;
    }

    public BigDecimal getTax() {
        return tax;
    }
}
