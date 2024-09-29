package com.recruiter.recruite.service;

import com.recruiter.recruite.entity.MPosition;
import com.recruiter.recruite.model.ApplicantInfo;
import com.recruiter.recruite.model.ApplicantPositionRequest;
import com.recruiter.recruite.model.CvUploadRequest;
import com.recruiter.recruite.model.JobApplicantRequest;

import java.util.List;

public interface RecruitService {

    void saveJobApplicantData(JobApplicantRequest request);

    void saveCv(CvUploadRequest request);

    void addApplicantPosition(ApplicantPositionRequest request);

    List<MPosition> getAvailablePositions(Long applicantId);

    ApplicantInfo getApplicantData(Long applicantId);

    void removeApplicantPosition(ApplicantPositionRequest request);
}
