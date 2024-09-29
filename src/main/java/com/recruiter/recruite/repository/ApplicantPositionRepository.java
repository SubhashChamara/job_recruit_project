package com.recruiter.recruite.repository;

import com.recruiter.recruite.entity.ApplicantPosition;
import com.recruiter.recruite.entity.JobApplicant;
import com.recruiter.recruite.entity.MPosition;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicantPositionRepository extends JpaRepository<ApplicantPosition,Long> {

    List<ApplicantPosition> findByJobApplicantAndActiveTrue(JobApplicant jobApplicant);

    Optional<ApplicantPosition> findByJobApplicantAndActiveTrueAndMasterPosition(JobApplicant jobApplicant, MPosition mPosition);

}
