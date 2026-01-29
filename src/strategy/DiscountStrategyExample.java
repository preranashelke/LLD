package strategy;


/***
 *
 * Factory → creates objects
 * Strategy → changes behavior at runtime
 * Discount calculation changes frequently based on business rules.
 * Using Strategy Pattern, each discount rule is encapsulated separately.
 * The pricing service remains unchanged even when new discounts are added,
 * which avoids large if-else blocks and follows Open-Closed Principle
 */

interface DiscountStrategy{
    double applyDiscount(double price);
}

class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price;
    }
}

class SeasonalDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double price){
        return price * 0.90;
    }
}

class EmployeeDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double price){
        return price * 0.65;
    }
}

class PricingService {
    private DiscountStrategy discountStrategy;
    public PricingService(DiscountStrategy discountStrategy){
        this.discountStrategy = discountStrategy;
    }

    public double calculateFinalPrice(double price){
        return discountStrategy.applyDiscount(price);
    }
}
public class DiscountStrategyExample {
    public static void main(String[] args){
        PricingService service = new PricingService(new EmployeeDiscount());

        System.out.println(service.calculateFinalPrice(1000));

    }
}
