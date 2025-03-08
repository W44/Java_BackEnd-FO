package com.example.demo.Orders;

import com.example.demo.DTO.OrderRequestDto;
import com.example.demo.Interfaces.IOrderDBRepository;
import com.example.demo.Interfaces.IUsersDBRepository;
import com.example.demo.Models.OrderModel;
import com.example.demo.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final IOrderDBRepository orderRepository;
    private final IUsersDBRepository userRepository;  // Inject User repository

    @Autowired
    public OrderService(IOrderDBRepository orderRepository, IUsersDBRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }
     public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderModel> getOrdersByUser(Integer userId) {
        return orderRepository.findByUser_Id(userId);
    }

    public void addNewOrder(OrderRequestDto orderRequest) {
        //        Optional<OrderModel> orderModelOptional = IOrderDBRepository.findOrderByPrice(order.getPrice());
//
//        if (orderModelOptional.isPresent())
//        {
//            throw new IllegalStateException("Same price Order already Present");
//        }
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new IllegalStateException("User not found with ID: " + orderRequest.getUserId()));

        OrderModel order = new OrderModel(
                orderRequest.getName(),
                orderRequest.getPrice(),
                orderRequest.getDescription(),
                orderRequest.getDate(),
                user,
                orderRequest.getIsactive()
        );

        orderRepository.save(order);
    }

   public void deleteOrder(Long id) {
        boolean exists = orderRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No order with ID: " + id + " exists");
        }
        orderRepository.deleteById(id);
    }

    @Transactional
    public void updateOrder(Long id, OrderRequestDto orderRequest) {
        OrderModel order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Order with ID: " + id + " does not exist"));

        if (orderRequest.getName() != null && !orderRequest.getName().isEmpty()) {
            order.setName(orderRequest.getName());
        }
        if (orderRequest.getPrice() != null && orderRequest.getPrice() > 0) {
            order.setPrice(orderRequest.getPrice());
        }
        if (orderRequest.getDescription() != null && !orderRequest.getDescription().isEmpty()) {
            order.setDescription(orderRequest.getDescription());
        }
    }
}
