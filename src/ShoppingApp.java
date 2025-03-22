import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Customer> customers = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        products.add(new Product(1, "Phone", 100000, 10));
        products.add(new Product(2, "Laptop", 300000, 5));
        products.add(new Product(3, "Headphone", 50000, 20));

        System.out.println("Добро пожаловать в интернет-магазин!");

        while(true){
            System.out.println("\nВыберите действие:");
            System.out.println("1. Зарегистрировать клиента");
            System.out.println("2. Показать список товаров");
            System.out.println("3. Оформить заказ");
            System.out.println("4. Просмотреть заказы клиента");
            System.out.println("5. Выход");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Введите имя клиента: ");
                    String name = sc.nextLine();
                    System.out.print("Введите email клиента: ");
                    String email = sc.nextLine();
                    int customerId = sc.nextInt();
                    Customer newCustomer = new Customer(customerId, name, email);
                    customers.add(newCustomer);
                    System.out.println("Клиент зарегистрирован с ID: " + customerId);
                    break;

                case 2:
                    System.out.println("Список доступных товаров:");
                    for(Product product : products){
                        System.out.println(product.getProductId() + ". " + product.getName() +
                                    " - " + product.getPrice() + " KZT (На складе: " + product.getStockQuantity() + ")");
                    }
                    break;

                case 3:
                    if(customers.isEmpty()){
                        System.out.println("Сначала зарегистрируйте клиента!");
                        break;
                    }

                    System.out.print("Введите ID клиента: ");
                    int custId = sc.nextInt();
                    sc.nextLine();

                    Customer customer = null;
                    for(Customer c : customers){
                        if (c.getCustomerId() == custId){
                            customer = c;
                            break;
                        }
                    }

                    if(customer == null){
                        System.out.println("Клиент не найден!");
                        break;
                    }

                    Order newOrder = new Order(orders.size() + 1,"2025-03-22", "Processing");
                    while (true){
                        System.out.println("Выберите товар по ID (или 0 для завершения): ");
                        int productID = sc.nextInt();

                        if (productID == 0) break;

                        System.out.print("Введите количество: ");
                        int quantity = sc.nextInt();

                        Product selectedProduct = null;
                        for(Product p : products){
                            if (p.getProductId() == productID){
                                selectedProduct = p;
                                break;
                            }
                        }
                    }

            }
        }



    }
}