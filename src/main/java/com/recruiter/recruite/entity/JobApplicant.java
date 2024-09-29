package com.recruiter.recruite.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recruiter.recruite.constants.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "JOB_APPLICANT")
@SequenceGenerator(name = "JOB_APPLICANT_SEQ_GENERATOR", sequenceName = "ID_SEQ_JOB_APPLICANT", allocationSize = 1)
public class JobApplicant extends User {

    @Column(name = "NIC",nullable = false,length = 20)
    private String nic;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;

    @Column(name = "BIRTH_DAY")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDay;

    @Column(name = "CONTACT_NUM")
    private String contactNumber;

    @Column(name = "OPEN_TO_WORK", nullable = false, columnDefinition = "bit default 1", length = 1)
    private Boolean openToWork;

    @OneToMany(mappedBy = "jobApplicant" ,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("jobApplicant")
    @ToString.Exclude
    private List<ApplicantPosition> appliedPositions;

    @OneToMany(mappedBy = "jobApplicant" ,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("jobApplicant")
    @ToString.Exclude
    private List<ApplicantCv> cvList;

}
