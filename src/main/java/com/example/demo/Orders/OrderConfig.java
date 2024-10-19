package com.example.demo.Orders;


import com.example.demo.Interfaces.IOrderDBRepository;
import com.example.demo.Models.OrderModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class OrderConfig {

    @Bean
    CommandLineRunner commandLineRunner(IOrderDBRepository repository ){

        return args -> {
             OrderModel test = new OrderModel("tester",44L,"testing description", LocalDate.now());
             OrderModel test2 = new OrderModel("tester2",88L,"testing description of 2", LocalDate.of(2024, Month.JULY,14));

             repository.saveAll(List.of(test,test2));
        };
    }
}
