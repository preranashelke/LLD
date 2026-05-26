package interview_lld;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//classes

class Order {
    int id;
    int userId;
    BigDecimal amount;
    LocalDateTime created_at;
    List<OrderItem> orderItems;
    OrderStatus status;
}

enum OrderStatus{
    PROCESSING,
    FAILED,
    SHIPPED
}

class OrderItem {
    int productId;
    String name;
    int qty;
    BigDecimal price;
}

class InventoryItem {
    int id;
    int requested;
    int availableStocks;
}
class Payment{
    int id;
    int orderId;
    PaymentStatus status;
    String provider;

}
enum PaymentStatus{
    COMPLETED,
    FAILED,
    INITIATED
}
class Shipment{
    int id;
    int orderId;
    String trackingId;
    ShipmentStatus status;
}
enum ShipmentStatus{
    PROCESSING,
    SHIPPED,
    DELIVERED
}

//repository

interface OrderRepository{
    void saveOrder(Order order);
    Order findById(int id);
    void updateStatus(int orderId, OrderStatus status);

}
interface InventoryRepository{
    InventoryItem findByProductId(int productId);
    void updateStock(int productId, int newStock);
}
interface PaymentRepository{
    void save(Payment payment);
}

//services

class OrderService {
    private PaymentService paymentService;
    private ShippingProvider shippingProvider;
    private InventoryService inventoryService;
    private OrderRepository orderRepository;

    public void createOrder(Order order, PaymentStrategy paymentStrategy){
       //reserve inventory
        for(OrderItem item : order.orderItems){
            boolean reserved = inventoryService.getItemFromInventory(item.productId, item.qty);

            if(!reserved){
                System.out.println("stock is not available");
                return;
            }

        }
        //save in db
        order.status=OrderStatus.PROCESSING;
        orderRepository.saveOrder(order);

        //process payment
        boolean paymentStatus = paymentService.processPayment(order, paymentStrategy);

        if(!paymentStatus){
            order.status = OrderStatus.FAILED;
            orderRepository.updateStatus(order.id, OrderStatus.FAILED);
            return;
        }
        //shipment

        String trackId = shippingProvider.createShipment(order);
        order.status =OrderStatus.SHIPPED;
        orderRepository.updateStatus(order.id, OrderStatus.SHIPPED);
        System.out.println("order tracking id is {}" + trackId);
    }
    public OrderStatus getOrderStatus(int id){
       return orderRepository.findById(id).status;
    }

}
class InventoryService{
    private InventoryRepository inventoryRepository;
    public synchronized boolean getItemFromInventory(int productId, int quantity){
        InventoryItem item = inventoryRepository.findByProductId(productId);

        if (item.availableStocks>=quantity){
            item.availableStocks-=quantity;
            inventoryRepository.updateStock(productId, item.availableStocks);
            return true;
        }
        return false;
    }

}

interface PaymentStrategy{
    boolean pay(BigDecimal amount);
}
class StripeStrategy implements PaymentStrategy{

    public boolean pay(BigDecimal amount){
        System.out.println("Processing via Stripe");
        return true;
    }
}

class RazorpayStrategy implements PaymentStrategy{
    public boolean pay(BigDecimal amount){
        System.out.println("Processing via Razorpay");
        return true;
    }
}

class PaymentService{
    private PaymentRepository paymentRepository;

    public boolean processPayment(Order order, PaymentStrategy paymentStrategy){
        Payment payment = new Payment();
        payment.orderId=order.id;
        payment.status=PaymentStatus.INITIATED;

        boolean success = paymentStrategy.pay(order.amount);
        payment.status = success ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;
        paymentRepository.save(payment);
        return success;
    }
}

interface ShippingProvider{
    String createShipment(Order order);
}

class ShipRocketAdapter implements ShippingProvider{
    public String createShipment(Order order){
        System.out.println("calling shipping ShipRocket api");
        return "XYARS";

    }
}


public class KotakEcommerce {
    public static void main(String[] args) {

        /***
         * Prompt:
         *         Design the backend system for an e-commerce platform that handles order processing. Consider aspects such as inventory management, payment processing, order tracking, and shipping integration.
         *         Sub-components to explore:
         *         Inventory Management – Locking & deducting stock
         *         Payment Processing – Integration with Stripe/Razorpay
         *         Order Tracking – Status updates (processing → shipped → delivered)
         *         Shipping Integration – Connect with 3rd-party APIs like ShipRocket
         *
         */

    }
}