package com.example.demo.Interfaces;

import com.example.demo.Models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IOrderDBRepository extends JpaRepository<OrderModel,Long> {

    Optional<OrderModel>  findOrderByPrice(Long price);

}
