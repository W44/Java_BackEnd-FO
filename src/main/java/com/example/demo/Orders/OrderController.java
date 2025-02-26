package com.example.demo.Orders;


import com.example.demo.Models.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<OrderModel> getOrder() {
        return orderService.getOrder();

    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "*")
    @PostMapping
    public void resisterOrder(@RequestBody OrderModel order)
    {
         orderService.addNewOrder(order);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "{orderId}")
    public void  deleteOrder(@PathVariable("orderId") Long id)
    {
        orderService.deleteOrder(id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @CrossOrigin(origins = "*")
    @PutMapping(path = "{orderid}")
    public void updateOrder(@PathVariable("orderid") Long id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) Long price,
                            @RequestParam(required = false) String description)
    {
        orderService.updateOrder(id,name,price,description);
    }

}
