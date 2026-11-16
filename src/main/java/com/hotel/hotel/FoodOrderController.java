package com.hotel.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class FoodOrderController {

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @GetMapping
    public List<FoodOrder> getAllOrders() {
        return foodOrderRepository.findAll();
    }

    @PostMapping
    public FoodOrder createOrder(@RequestBody OrderRequest request) {
        Client client = clientRepository.findById(request.getClientId()).orElseThrow();
        FoodItem foodItem = foodItemRepository.findById(request.getFoodItemId()).orElseThrow();

        FoodOrder order = new FoodOrder();
        order.setClient(client);
        order.setFoodItem(foodItem);
        order.setQuantity(request.getQuantity());

        // Calcul du prix total
        order.setTotalPrice(foodItem.getPrice() * request.getQuantity());

        return foodOrderRepository.save(order);
    }
    // Route publique pour commander depuis la chambre
    @PostMapping("/public-order")
    public FoodOrder createPublicOrder(@RequestBody PublicOrderRequest request) {
        // On cherche le client par son nom (ou son numéro de chambre)
        // Pour simplifier, on suppose que le client donne son nom exact
        Client client = clientRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(request.getClientName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Client not found"));

        FoodItem foodItem = foodItemRepository.findById(request.getFoodItemId()).orElseThrow();

        FoodOrder order = new FoodOrder();
        order.setClient(client);
        order.setFoodItem(foodItem);
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(foodItem.getPrice() * request.getQuantity());

        return foodOrderRepository.save(order);
    }
}