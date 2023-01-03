package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Order;
import de.cinema.backendp2cinema.exceptions.OrderNotFoundException;
import de.cinema.backendp2cinema.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository OrderRepository;

    @Autowired
    public OrderService(OrderRepository OrderRepository){
        this.OrderRepository = OrderRepository;
    }

    public Order addOrder(Order Order) {
        return OrderRepository.save(Order);

    }

    public List<Order> getAllOrders() {
        return (List<Order>) OrderRepository.findAll();
    }

    public Order findOrderById(UUID id) {
        return OrderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("Order with id " + id + " not found"));
    }

    public void delete(UUID id) {
        OrderRepository.deleteById(id);
    }


}
