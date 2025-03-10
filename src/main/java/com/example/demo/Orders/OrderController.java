package com.example.demo.Orders;


import com.example.demo.DTO.OrderCountResponseDto;
import com.example.demo.DTO.OrderRequestDto;
import com.example.demo.Models.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public List<Map<String, Object>> getOrders(@RequestParam(required = false) Integer userId) {
        List<OrderModel> orders;

        if (userId != null) {
            orders = orderService.getOrdersByUser(userId);
        } else {
            orders = orderService.getAllOrders();
        }
        return orders.stream().map(order -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", order.getId());
            response.put("name", order.getName());
            response.put("price", order.getPrice());
            response.put("description", order.getDescription());
            response.put("date", order.getDate());
            //response.put("daysOrdered", order.getDaysordered());
            response.put("userId", order.getUser().getId());
            response.put("username", order.getUser().getUsername());
            response.put("isactive", order.getIsactive());
            return response;
        }).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(path = "active")
    public List<Map<String, Object>> getActiveOrders() {
        List<OrderModel> orders;

        orders = orderService.getActiveOrders();

        return orders.stream().map(order -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", order.getId());
            response.put("name", order.getName());
            response.put("price", order.getPrice());
            response.put("description", order.getDescription());
            response.put("date", order.getDate());
            //response.put("daysOrdered", order.getDaysordered());
            response.put("userId", order.getUser().getId());
            response.put("username", order.getUser().getUsername());
            response.put("isactive", order.getIsactive());
            return response;
        }).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(path = "past")
    public List<Map<String, Object>> getPastOrders() {
        List<OrderModel> orders;

        orders = orderService.getPastOrders();

        return orders.stream().map(order -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", order.getId());
            response.put("name", order.getName());
            response.put("price", order.getPrice());
            response.put("description", order.getDescription());
            response.put("date", order.getDate());
            //response.put("daysOrdered", order.getDaysordered());
            response.put("userId", order.getUser().getId());
            response.put("username", order.getUser().getUsername());
            response.put("isactive", order.getIsactive());
            return response;
        }).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "*")
    @PostMapping
    public void registerOrder(@RequestBody OrderRequestDto orderRequest) {
        orderService.addNewOrder(orderRequest);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long id) {
        orderService.deleteOrder(id);
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "*")
    @PutMapping(path = "{orderId}")
    public void updateOrder(@PathVariable("orderId") Long id,
                            @RequestBody OrderRequestDto orderRequest) {
        orderService.updateOrder(id, orderRequest);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "*")
    @PutMapping(path = "complete/{orderId}")
    public void completeOrder(@PathVariable("orderId") Long id) {
        orderService.completeOrder(id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "*")
    @GetMapping("/stats")
    public OrderCountResponseDto getOrderCounts(@RequestParam(required = true) Integer userId) {
        long currentOrders = orderService.getCurrentOrdersCount(userId);
        long pastOrders = orderService.getPastOrdersCount(userId);
        //long totalOrders = pastOrders + currentOrders;

//       Map<String, Long> stats = new HashMap<>();
//        stats.put("pastOrders", pastOrders);
//        stats.put("currentOrders", currentOrders);
//        stats.put("totalOrders", totalOrders);

        return new OrderCountResponseDto(currentOrders,pastOrders);

    }
}
