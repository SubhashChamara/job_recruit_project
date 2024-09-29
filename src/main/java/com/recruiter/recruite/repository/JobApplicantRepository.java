package com.recruiter.recruite.repository;

import com.recruiter.recruite.entity.JobApplicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicantRepository extends JpaRepository<JobApplicant,Long> {
}
