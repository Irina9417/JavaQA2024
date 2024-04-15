package ru.shop;

import ru.shop.exception.BadOrderCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.CustomerRepository;
import ru.shop.repository.OrderRepository;
import ru.shop.repository.ProductRepository;
import ru.shop.service.CustomerService;
import ru.shop.service.OrderService;
import ru.shop.service.ProductService;

import java.util.UUID;

public class Main {
    private static final ProductRepository productRepository = new ProductRepository();
    private static final CustomerRepository customerRepository = new CustomerRepository();
    private static final OrderRepository orderRepository = new OrderRepository();
    private static final ProductService productService = new ProductService(productRepository);
    private static final OrderService orderService = new OrderService(orderRepository);
    private static final CustomerService customerService = new CustomerService(customerRepository);

    private static final String CONST = "CONST";
    private static final long LONG_CONST = 99999999999L;

    public static void main(String[] args) {
        var product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("product1");
        product.setCost(99);
        product.setProductType(ProductType.GOOD);

        var product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setName("product2");
        product2.setCost(55);
        product2.setProductType(ProductType.GOOD);

        productService.save(product);
        productService.save(product2);

        var allProducts = productService.findAll();


        System.out.println("products = " + allProducts);


        var customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("customer1");
        customer.setAge(33);
        customer.setPhone("89997776655");

        customerService.save(customer);


        var customer2 = new Customer();
        customer2.setId(UUID.randomUUID());
        customer2.setName("customer2");
        customer2.setAge(19);
        customer2.setPhone("89997776655");

        customerService.save(customer2);

        System.out.println("customers = " + customerRepository.findAll());

       orderService.add(customer, product, 1);
       orderService.add(customer2, product2, 1);

       try {
           orderService.add(customer2, product, -5);
       } catch (BadOrderCountException e) {
           System.out.println(e.getMessage());
       }
       System.out.println("orders = " + orderRepository.findAll());

       System.out.println("orders for customer = " + orderService.findByCustomer(customer));
       System.out.println("orders for customer2 = " + orderService.findByCustomer(customer2));

        System.out.println("total amount for customer = " + orderService.getTotalCustomerAmount(customer));
        System.out.println("total amount for customer2 = " + orderService.getTotalCustomerAmount(customer2));

        //


//        allProducts.remove(0);
//
//
//        allProducts = productRepository.findAll();
//
//        System.out.println("products = " + allProducts);




//
//        var productType = ProductType.SERVICE;
//
//        var sp = switch (productType) {
//            case GOOD -> "G";
//            case SERVICE -> "S";
//        };

    }

}