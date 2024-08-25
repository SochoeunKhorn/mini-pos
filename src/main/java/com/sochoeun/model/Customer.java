package com.sochoeun.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerLocalName;
    private String customerEngName;

    @Column(unique = true, nullable = false)
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted;

}
