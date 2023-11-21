package com.example.spring_jobs.user;

import com.example.spring_jobs.company.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name="user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login_id",nullable = false,unique = true)
    private String loginId;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)//Enum타입 저장할 떄 사용
    private UserRoleEnum role;

    @Column(nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
