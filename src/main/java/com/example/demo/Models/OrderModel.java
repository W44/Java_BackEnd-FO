package com.example.demo.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;


@Entity
@Table
public class OrderModel {
    @Id
    @SequenceGenerator(
            name="order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;
    private String name;
    private Long price;
    private String description;
    private LocalDate date;
    @Transient
    private long daysordered;

    public OrderModel() {
    }

    public OrderModel(Long id, String name, Long price, String description, LocalDate date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.date = date;
    }

    public OrderModel(String name, Long price, String description, LocalDate date) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getDaysordered() {
        return Period.between(this.date, LocalDate.now()).getDays();
    }

    public void setDaysordered(long daysordered) {
        this.daysordered = daysordered;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
