package com.weddingapp.model;

import jakarta.persistence.*;

@Entity
public class GiftEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guestName;
    private String giftItem;
    private String contact;
    private Double amount;

    // Getters and Setters
}
