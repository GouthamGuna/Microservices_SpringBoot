package in.dev.ggs.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class DemoController {

    // Endpoint 1: Root endpoint
    @GetMapping("/")
    public String welcome() {
        return "Welcome to Spring Boot Application!";
    }

    // Endpoint 2: User endpoint with query parameter
    @GetMapping("/user")
    public String welcomeUser(@RequestParam String name) {
        return "Welcome " + name + "!";
    }

    // Endpoint 3: Product endpoint with path variable
    @GetMapping("/product/{id}")
    public Map<String, Object> getProduct(@PathVariable int id) {
        // Hardcoded product values
        Map<String, Object> product = new HashMap<>();
        product.put("id", id);
        product.put("name", "Sample Product");
        product.put("price", 99.99);
        product.put("quantity", 10);
        product.put("category", "Electronics");
        product.put("description", "High-quality sample product");
        product.put("rating", 4.5);
        product.put("inStock", true);
        product.put("supplier", "Demo Supplier");
        product.put("tags", Arrays.asList("new", "popular", "featured"));

        return product;
    }
}
