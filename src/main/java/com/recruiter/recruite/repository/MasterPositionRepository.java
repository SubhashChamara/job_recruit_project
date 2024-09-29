package com.recruiter.recruite.repository;

import com.recruiter.recruite.entity.ApplicantPosition;
import com.recruiter.recruite.entity.JobApplicant;
import com.recruiter.recruite.entity.MPosition;
import com.recruiter.recruite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MasterPositionRepository extends JpaRepository<MPosition,Long> {

    @Query("SELECT m FROM MPosition m WHERE m NOT IN (SELECT ap.masterPosition FROM ApplicantPosition ap WHERE ap.jobApplicant =:applicantPositions AND ap.active=true)")
    List<MPosition> findPositionsNotInApplicantPositions(JobApplicant applicantPositions);
}
