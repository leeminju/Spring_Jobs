package com.example.spring_jobs.company.entity;

import com.example.spring_jobs.company.IndustryEnum;
import com.example.spring_jobs.company.dto.CompanyUpdateDto;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @Enumerated(value = EnumType.STRING)
    private IndustryEnum industry;

    @OneToOne(mappedBy = "company", cascade = CascadeType.PERSIST)
    private User user;
    @Builder
    public Company(String companyName, String location, String industry, User user) {
        this.companyName = companyName;
        this.location = location;
        this.industry = IndustryEnum.from(industry);
        this.user = user;
        user.addCompany(this);
    }

    public void updateInfo(CompanyUpdateDto companyUpdateDto) {
        this.user.updateInfo(companyUpdateDto);
        this.companyName = companyUpdateDto.getCompanyName();
        this.location = companyUpdateDto.getLocation();
        this.industry = IndustryEnum.from(companyUpdateDto.getIndustry());
    }
}
