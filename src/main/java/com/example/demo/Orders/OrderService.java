package com.example.demo.Orders;

import com.example.demo.Interfaces.IOrderDBRepository;
import com.example.demo.Models.OrderModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    final IOrderDBRepository IOrderDBRepository;

    @Autowired
    public OrderService(IOrderDBRepository IOrderDBRepository) {
        this.IOrderDBRepository = IOrderDBRepository;
    }


    public List<OrderModel> getOrder() {
        return IOrderDBRepository.findAll();
    }

    public void addNewOrder(OrderModel order) {
        Optional<OrderModel> orderModelOptional = IOrderDBRepository.findOrderByPrice(order.getPrice());

        if (orderModelOptional.isPresent())
        {
            throw new IllegalStateException("Same price Order already Present");
        }
         IOrderDBRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Boolean orderExist = IOrderDBRepository.existsById(id);
        if (!orderExist)
        {
            throw new IllegalStateException("No oder with ID:"+ id +" exists");
        }
        IOrderDBRepository.deleteById(id);
    }

    @Transactional
    public void updateOrder(Long id, String name, Long price, String description) {
        OrderModel orderModel = IOrderDBRepository.findById(id).orElseThrow(() -> new IllegalStateException("Order with ID:" + id + " does not exists"));


        if (name != null && name.length() > 0 && !Objects.equals(name,orderModel.getPrice()))
        {
            orderModel.setName(name);
        }
        if (price != null && price > 0 && !Objects.equals(price,orderModel.getPrice()))
        {
            orderModel.setPrice(price);
        }
        if (description != null && description.length() > 0 && !Objects.equals(description,orderModel.getDescription()))
        {
            orderModel.setDescription(description);
        }


    }
}
