//package com.React.react;
//
//import com.React.react.Model.Product;
//import com.React.react.Reposoitry.ProductRepo;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final ProductRepo productRepository;
//
//    public DataLoader(ProductRepo productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Product p1 = new Product(1, "Laptop", "Gaming laptop", new BigDecimal("1500.00"), "Dell","Electronics", true, new Date(), 5);
//        Product p2 = new Product(2, "Phone", "Smartphone", new BigDecimal("800.00"), "Samsung","Electronics", true,new Date(), 10);
//
//        productRepository.save(p1);
//        productRepository.save(p2);
//    }
//}
