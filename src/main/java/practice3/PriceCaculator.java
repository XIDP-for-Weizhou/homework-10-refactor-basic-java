package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {

    private BigDecimal subTotal;
    private final List<OrderLineItem> orderLineItemList;
    private final List<BigDecimal> discounts;
    private final BigDecimal tax;

    public PriceCaculator(Order order) {
        this.orderLineItemList = order.getOrderLineItemList();
        this.discounts = order.getDiscounts();
        this.tax = order.getTax();
    }

    public BigDecimal calculate() {
        subTotal = new BigDecimal(0);
        getSubTotal();
        subtractdiscounts();
        BigDecimal allTax = calculateTax();

        // calculate GrandTotal
        return calculateGrandTotal(allTax);
    }

    private BigDecimal calculateGrandTotal(BigDecimal tax) {
        return subTotal.add(tax);
    }

    private BigDecimal calculateTax() {
        // calculate tax
        return subTotal.multiply(this.tax);
    }

    private void subtractdiscounts() {
        // Subtract discounts
        for (
                BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }
    }

    private void getSubTotal() {
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
    }

}
