package com.recruiter.recruite.service.impl;

import com.recruiter.recruite.entity.*;
import com.recruiter.recruite.model.*;
import com.recruiter.recruite.repository.*;
import com.recruiter.recruite.service.RecruitService;
import com.recruiter.recruite.service.exception.BusinessException;
import com.recruiter.recruite.service.exception.BusinessExceptionType;
import com.recruiter.recruite.service.util.Mapper;
import com.recruiter.recruite.service.util.NicUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecruitServiceImpl implements RecruitService {

    private final Mapper mapper;
    private final JobApplicantRepository jobApplicantRepository;
    private final MasterPositionRepository masterPositionRepository;
    private final DocumentRepository documentRepository;
    private final ApplicantCvRepository applicantCvRepository;
    private final ApplicantPositionRepository applicantPositionRepository;
    @Override
    @Transactional
    public void saveJobApplicantData(JobApplicantRequest request){

        JobApplicant jobApplicant = mapper.toEntity(request);
        NicDetails nicDetails = NicUtil.getDetailsFromNic(request.getNic());

        // Set the existing User to the JobApplicant

        jobApplicant.setBirthDay(nicDetails.getDob());
        jobApplicant.setGender(nicDetails.getGender());
        jobApplicant.setActive(Boolean.TRUE);

        if (request.getLatestCvId() != null) {
            List<ApplicantCv> cvList = new ArrayList<>();
            Document latestCv = documentRepository.findById(request.getLatestCvId())
                    .orElseThrow(() -> new BusinessException(BusinessExceptionType.FILE_NOT_FOUND,"Document not found"));
            ApplicantCv applicantCv = new ApplicantCv();
            applicantCv.setJobApplicant(jobApplicant);
            applicantCv.setDocument(latestCv);
            applicantCv.setActive(Boolean.TRUE);
            cvList.add(applicantCv);
            jobApplicant.setCvList(cvList);
        }

        List<MPositionInfo> appliedPositions = request.getAppliedPositions();
        if (request.getLatestCvId() != null && appliedPositions != null && !appliedPositions.isEmpty()) {

            List<ApplicantPosition> applicantPositionList = new ArrayList<>();
            for (MPositionInfo mPositionInfo : appliedPositions) {
                MPosition mPosition = masterPositionRepository.findById(mPositionInfo.getId())
                        .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS,"M Position not found"));
                ApplicantPosition applicantPosition = new ApplicantPosition();
                applicantPosition.setJobApplicant(jobApplicant);
                applicantPosition.setMasterPosition(mPosition);
                applicantPositionList.add(applicantPosition);
            }
            jobApplicant.setAppliedPositions(applicantPositionList);
        }

        log.info("Try to save the job applicant with data {}", jobApplicant);
        jobApplicantRepository.save(jobApplicant);

    }

    @Override
    @Transactional
    public void saveCv(CvUploadRequest request){
        Document cvDocument = documentRepository.findById(request.getLatestCvId())
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.FILE_NOT_FOUND,"Document not found"));

        JobApplicant jobApplicant = jobApplicantRepository.findById(request.getJobApplicantId())
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS,"Applicant not found"));

        ApplicantCv applicantCv = new ApplicantCv();
        applicantCv.setJobApplicant(jobApplicant);
        applicantCv.setDocument(cvDocument);
        applicantCv.setActive(Boolean.TRUE);
        Optional<ApplicantCv> existActiveCv = applicantCvRepository.findByJobApplicantAndActiveTrue(jobApplicant);
        existActiveCv.ifPresent(cv -> cv.setActive(Boolean.FALSE));
        applicantCvRepository.save(applicantCv);
    }

    @Override
    @Transactional
    public void addApplicantPosition(ApplicantPositionRequest request){

        JobApplicant jobApplicant = jobApplicantRepository.findById(request.getJobApplicantId())
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS,"Applicant not found"));

        applicantCvRepository.findByJobApplicantAndActiveTrue(jobApplicant)
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS,"Cv must be upload before add job position"));

        MPosition mPosition = masterPositionRepository.findById(request.getMPositionInfo().getId())
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS,"Applicant position not found"));

        ApplicantPosition applicantPosition = new ApplicantPosition();
        applicantPosition.setJobApplicant(jobApplicant);
        applicantPosition.setMasterPosition(mPosition);
        applicantPosition.setActive(Boolean.TRUE);
        applicantPositionRepository.save(applicantPosition);
    }

    @Override
    @Transactional
    public void removeApplicantPosition(ApplicantPositionRequest request) {
        JobApplicant jobApplicant = jobApplicantRepository.findById(request.getJobApplicantId())
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS,"Applicant not found"));

        MPosition mPosition = masterPositionRepository.findById(request.getMPositionInfo().getId())
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS,"Applicant position not found"));

        ApplicantPosition applicantPosition = applicantPositionRepository.findByJobApplicantAndActiveTrueAndMasterPosition(jobApplicant, mPosition)
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS, "No added position to remove"));

        applicantPosition.setActive(Boolean.FALSE);

    }

    @Override
    @Transactional
    public List<MPosition> getAvailablePositions(Long applicantId) {
        JobApplicant jobApplicant = jobApplicantRepository.findById(applicantId)
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS, "Applicant not found"));

        return masterPositionRepository.findPositionsNotInApplicantPositions(jobApplicant);

    }

    @Override
    @Transactional
    public ApplicantInfo getApplicantData(Long applicantId) {
        JobApplicant jobApplicant = jobApplicantRepository.findById(applicantId)
                .orElseThrow(() -> new BusinessException(BusinessExceptionType.BUSINESS, "Applicant not found"));

        ApplicantInfo applicantInfo = new ApplicantInfo(jobApplicant);
        applicantInfo.setAppliedPositions(jobApplicant.getAppliedPositions()
                .stream()
                .map(applicantPosition -> new MPositionInfo(applicantPosition.getMasterPosition()))
                .collect(Collectors.toList()));
        Optional<ApplicantCv> updatedApplicantCv = applicantCvRepository.findByJobApplicantAndActiveTrue(jobApplicant);

        updatedApplicantCv.ifPresent(applicantCv -> applicantInfo.setLatestCvInfo(new DocumentInfo(applicantCv.getDocument())));

        return applicantInfo;
    }


}
