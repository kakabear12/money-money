package com.example.moneymoney.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment_gateway")
public class PaymentGateway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "api_key", nullable = false)
    private String apiKey;

    @Column(name = "secret_key", nullable = false)
    private String secretKey;

    // getters and setters
}
