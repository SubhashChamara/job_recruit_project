package com.recruiter.recruite.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APPLICANT_CV")
@SequenceGenerator(name = "APPLICANT_CV_SEQ_GENERATOR", sequenceName = "ID_SEQ_APPLICANT_CV", allocationSize = 1)
public class ApplicantCv extends MBaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPLICANT_CV_SEQ_GENERATOR")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_DOCUMENT",nullable = false)
    private Document document;

    @ManyToOne
    @JoinColumn(name = "FK_JOB_APPLICANT",nullable = false)
    private JobApplicant jobApplicant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ApplicantCv module = (ApplicantCv) o;
        return getId() != null && Objects.equals(getId(), module.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
