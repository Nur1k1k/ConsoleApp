import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private String orderDate;
    private String status;
    private List<OrderItem> orderItems;

    public Order(int orderID, String orderDate, String status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.status = status;
        this.orderItems = new ArrayList<>();
    }

    public int getOrderID() {
        return orderID;
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
        if (product.getStockQuantity() >= quantity) {
            orderItems.add(new OrderItem(product, quantity));
            product.reduceStock(quantity);
        } else {
            System.out.println("Ошибка: недостаточно товара на складе!");
        }
    }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
