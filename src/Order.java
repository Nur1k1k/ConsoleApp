import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private String orderDate;
    private String status;
    private List<OrderItem> orderItems;

    public Order(int orderId, String orderDate, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(Product product, int quantity) {
        if(product.getStockQuantity() >= quantity) {
            orderItems.add(new OrderItem(product, quantity));
            product.reduceStockQuantity(quantity);
        } else {
            System.out.println("Ошибка: недостаточно товара на складе!");
        }
    }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getTotelPrice();
        }
        return total;
    }



}
