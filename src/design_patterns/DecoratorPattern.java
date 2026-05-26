package design_patterns;

interface Coffee{
    double getCost();
    String getDescription();
}
class SimpleCoffee implements Coffee{
    @Override
    public double getCost(){
        return 50;
    }

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
}

abstract class CoffeeDecorator implements Coffee{
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }
}

class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " milk";
    }
}

class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 10;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " sugar";
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {
       Coffee coffee = new SimpleCoffee();
        System.out.println("Cost: $" + coffee.getCost() + "; Description: " + coffee.getDescription());

        coffee = new MilkDecorator(coffee);
        System.out.println("Cost: $" + coffee.getCost() + "; Description: " + coffee.getDescription());

        coffee = new SugarDecorator(coffee);
        System.out.println("Cost: $" + coffee.getCost() + "; Description: " + coffee.getDescription());

    }
}
