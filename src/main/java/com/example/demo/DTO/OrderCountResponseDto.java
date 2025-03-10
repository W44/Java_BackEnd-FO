package com.example.demo.DTO;


public class OrderCountResponseDto {
    private long currentOrders;
    private long pastOrders;
    private long totalOrders;

    public OrderCountResponseDto(long currentOrders, long pastOrders) {
        this.currentOrders = currentOrders;
        this.pastOrders = pastOrders;
        this.totalOrders = currentOrders + pastOrders;
    }

    public long getCurrentOrders() {
        return currentOrders;
    }

    public long getPastOrders() {
        return pastOrders;
    }

    public long getTotalOrders() {
        return totalOrders;
    }
}
