package com.recruiter.recruite.repository;

import com.recruiter.recruite.entity.ApplicantCv;
import com.recruiter.recruite.entity.JobApplicant;
import com.recruiter.recruite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantCvRepository extends JpaRepository<ApplicantCv,Long> {

    //    Optional<ApplicantCv> findByIdAndActiveIsTrue(Long id);
    Optional<ApplicantCv> findByJobApplicantAndActiveTrue(JobApplicant jobApplicant);



}
