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
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String supplierLocalName;
    private String supplierEngName;
    @Column(unique = true,nullable = false)
    private String supplierEmail;
    private String supplierPhone;
    private String supplierAddress;
    private String vatNumber;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;
}
