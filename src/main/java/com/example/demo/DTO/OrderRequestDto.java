package com.example.demo.DTO;
import java.time.LocalDate;

public class OrderRequestDto {
    private String name;
    private Long price;
    private String description;
    private LocalDate date;
    private Integer userId;
    private boolean isactive;


    public boolean getIsactive() { return isactive; }
    public String getName() { return name; }
    public Long getPrice() { return price; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public Integer getUserId() { return userId; }
}

