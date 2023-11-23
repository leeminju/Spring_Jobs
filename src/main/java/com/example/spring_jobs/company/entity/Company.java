package com.example.spring_jobs.company.entity;

import com.example.spring_jobs.company.dto.CompanyUpdateDto;
import com.example.spring_jobs.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
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

    @OneToOne(mappedBy = "company",cascade = CascadeType.PERSIST)
    private User user;

    @Builder
    public Company(String companyName, String location, String industry,User user) {
        this.companyName = companyName;
        this.location = location;
        this.industry = industry;
        this.user = user;
        user.addCompany(this);
    }

    public void updateInfo(CompanyUpdateDto companyUpdateDto) {
        this.user.updateInfo(companyUpdateDto);
        this.companyName = companyUpdateDto.getCompanyName();
        this.location = companyUpdateDto.getLocation();
        this.industry = companyUpdateDto.getIndustry();
    }
}
