package com.example.demo.Interfaces;

import com.example.demo.Models.OrderModel;
import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface IOrderDBRepository extends JpaRepository<OrderModel,Long> {

    Optional<OrderModel>  findOrderByPrice(Long price);
    List<OrderModel> findByUser_Id(Integer userId);
    List<OrderModel> findByUser(User user);
    List<OrderModel> findByIsactiveTrue();
    List<OrderModel> findByIsactiveFalse();
    long countByUser_IdAndIsactiveTrue(Integer userId);
    long countByUser_IdAndIsactiveFalse(Integer userId);


}
