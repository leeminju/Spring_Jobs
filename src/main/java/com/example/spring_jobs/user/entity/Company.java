package com.example.spring_jobs.user.entity;

import com.example.spring_jobs.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "company")
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String industry;

    @OneToOne(mappedBy = "company")
    private User user;
}
