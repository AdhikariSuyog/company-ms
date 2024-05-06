package com.example.companyms.companypart.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public static boolean isEmpty(Company company) {
        return (company.getName() == null || company.getName().isEmpty())
                && (company.getDescription() == null || company.getDescription().isEmpty());
    }
}
