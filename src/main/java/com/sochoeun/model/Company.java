package com.sochoeun.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyLocalName;
    private String companyEngName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;
    private String vatNumber;
    private String imagePath;
    private String image;


    @Setter
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isDeleted;

    public boolean getIsDeleted() {
        return isDeleted;
    }

}
