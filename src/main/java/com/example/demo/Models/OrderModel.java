package com.example.demo.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "orders")
public class OrderModel {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
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
    private String ocUser;
    private Long ocUid;

    public Long getOcUid() {
        return ocUid;
    }

    public String getOcUser() {
        return ocUser;
    }

    public void setOcUser(String ocUser) {
        this.ocUser = ocUser;
    }

    public void setOcUid(Long ocUid) {
        this.ocUid = ocUid;
    }

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isactive;


    @Transient
    private long daysordered;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    public OrderModel() {}

    public OrderModel(Long id, String name, Long price, String description, LocalDate date, User user, Boolean isactive) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.date = date;
        this.user = user;
        this.isactive=isactive;
    }

    public OrderModel(String name, Long price, String description, LocalDate date, User user, Boolean isactive) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.date = date;
        this.user = user;
        this.isactive=isactive;
    }

     public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
    public boolean getIsactive() {
        return isactive;
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

    public User getUser() {
        return user;
    }
    public Integer getUserId() { return user.getId(); }

    public String getUsername() { return user.getUsername(); }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", user=" + user.getUsername() +
                '}';
    }
}
