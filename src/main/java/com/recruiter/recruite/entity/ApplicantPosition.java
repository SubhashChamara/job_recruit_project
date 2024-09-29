package com.recruiter.recruite.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "APPLICANT_POSITION")
@SequenceGenerator(name = "APPLICANT_POSITION_SEQ_GENERATOR", sequenceName = "ID_SEQ_APPLICANT_POSITION", allocationSize = 1)
public class ApplicantPosition extends MBaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPLICANT_POSITION_SEQ_GENERATOR")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_APPLICANT_POSITION",nullable = false)
    private MPosition masterPosition;

    @ManyToOne
    @JoinColumn(name = "FK_JOB_APPLICANT",nullable = false)
    private JobApplicant jobApplicant;

}
