package com.example.spring_jobs.user.entity;

import com.example.spring_jobs.company.dto.CompanyUpdateDto;
import com.example.spring_jobs.company.entity.Company;
import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.dto.UserUpdateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login_id", nullable = false, unique = true)
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

    @Builder
    public User(String loginId, String password, String email, String phone, UserRoleEnum role) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public void addCompany(Company company) {
        this.company = company;
    }

    public void updateInfo(UserUpdateDto userUpdateDto) {
        this.email = userUpdateDto.getEmail();
        this.phone = userUpdateDto.getPhone();
    }
    public void updateInfo(CompanyUpdateDto companyUpdateDto) {
        this.email = companyUpdateDto.getEmail();
        this.phone = companyUpdateDto.getPhone();
    }
}
