import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Список клиентов, товаров и заказов
        List<Customer> customers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        // Добавляем тестовые товары
        products.add(new Product(1, "Смартфон", 100000, 10));
        products.add(new Product(2, "Ноутбук", 300000, 5));
        products.add(new Product(3, "Наушники", 25000, 20));

        System.out.println("Добро пожаловать в интернет-магазин!");

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Зарегистрировать клиента");
            System.out.println("2. Показать список товаров");
            System.out.println("3. Оформить заказ");
            System.out.println("4. Просмотреть заказы клиента");
            System.out.println("5. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите имя клиента: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите email клиента: ");
                    String email = scanner.nextLine();
                    int customerID = customers.size() + 1;
                    Customer newCustomer = new Customer(customerID, name, email);
                    customers.add(newCustomer);
                    System.out.println("Клиент зарегистрирован с ID: " + customerID);
                    break;

                case 2:
                    System.out.println("Список доступных товаров:");
                    for (Product product : products) {
                        System.out.println(product.getProductID() + ". " + product.getName() +
                                " - " + product.getPrice() + " KZT (На складе: " + product.getStockQuantity() + ")");
                    }
                    break;

                case 3:
                    if (customers.isEmpty()) {
                        System.out.println("Сначала зарегистрируйте клиента!");
                        break;
                    }

                    System.out.print("Введите ID клиента: ");
                    int custID = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера

                    Customer customer = null;
                    for (Customer c : customers) {
                        if (c.getCustomerID() == custID) {
                            customer = c;
                            break;
                        }
                    }

                    if (customer == null) {
                        System.out.println("Клиент не найден!");
                        break;
                    }

                    Order newOrder = new Order(orders.size() + 1, "2025-03-22", "Processing");
                    while (true) {
                        System.out.println("Выберите товар по ID (или 0 для завершения): ");
                        int productID = scanner.nextInt();

                        if (productID == 0) break;

                        System.out.print("Введите количество: ");
                        int quantity = scanner.nextInt();

                        Product selectedProduct = null;
                        for (Product p : products) {
                            if (p.getProductID() == productID) {
                                selectedProduct = p;
                                break;
                            }
                        }

                        if (selectedProduct != null) {
                            newOrder.addOrderItem(selectedProduct, quantity);
                        } else {
                            System.out.println("Товар не найден!");
                        }
                    }

                    customer.addOrder(newOrder);
                    orders.add(newOrder);
                    System.out.println("Заказ успешно оформлен! ID заказа: " + newOrder.getOrderID());
                    break;

                case 4:
                    System.out.print("Введите ID клиента: ");
                    int clientID = scanner.nextInt();
                    scanner.nextLine();

                    Customer foundCustomer = null;
                    for (Customer c : customers) {
                        if (c.getCustomerID() == clientID) {
                            foundCustomer = c;
                            break;
                        }
                    }

                    if (foundCustomer == null) {
                        System.out.println("Клиент не найден!");
                        break;
                    }

                    System.out.println("Заказы клиента " + foundCustomer.getName() + ":");
                    for (Order order : foundCustomer.getOrders()) {
                        System.out.println("Заказ ID: " + order.getOrderID() + ", Статус: " + order.getStatus());
                        for (OrderItem item : order.getOrderItems()) {
                            System.out.println("- " + item.getProduct().getName() + " x " + item.getQuantity());
                        }
                        System.out.println("Итоговая сумма: " + order.getTotalAmount() + " KZT");
                    }
                    break;

                case 5:
                    System.out.println("Выход из программы...");
                    return;

                default:
                    System.out.println("Неверный ввод! Попробуйте снова.");
            }
        }
    }
}
