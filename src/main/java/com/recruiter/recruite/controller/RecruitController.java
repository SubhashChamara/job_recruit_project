package com.recruiter.recruite.controller;

import com.recruiter.recruite.model.*;
import com.recruiter.recruite.service.RecruitService;
import com.recruiter.recruite.service.util.Mapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/recruit")
@Slf4j
@Tag(name = "Recruit Controller", description = "Manage recruit process")
public class RecruitController {

    private final RecruitService recruitService;
    private final Mapper mapper;


    @PostMapping("job-applicant")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Onboard an Applicant to the system", description = "Save an Applicant to the system with their applicant details")
    public void save(@RequestBody JobApplicantRequest request){
        log.info("Api Layer- request received to save job applicant data with data {}",request);
        recruitService.saveJobApplicantData(request);
    }


    @PostMapping("upload-cv")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Upload a cv for a exists applicant", description = "Save a cv for a exist applicant user in the system if already exist a cv then update it")
    public void uploadCv(@RequestBody CvUploadRequest request){
        log.info("Api Layer- request received to save job applicant data with data {}",request);
        recruitService.saveCv(request);
    }

    @PostMapping("add-position")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a job position for a applicant", description = "Add a new position for a exist applicant,it must be a applicant cv to add a applicant position")
    public void addPosition(@RequestBody ApplicantPositionRequest request){
        log.info("Api Layer- request received to add job position to a active job applicant {}",request);
        recruitService.addApplicantPosition(request);
    }

    @PutMapping("remove-position")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove a job position for a applicant", description = "Remove a position from a exist applicant")
    public void removePosition(@RequestBody ApplicantPositionRequest request){
        log.info("Api Layer- request received to remove job position from a active job applicant {}",request);
        recruitService.removeApplicantPosition(request);
    }

    @GetMapping("available-position/{applicant-id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get available job positions for a applicant", description = "get the applicant positions that are not add yet for the particular applicant")
    public List<MPositionInfo> getAvailablePositions(@PathVariable("applicant-id") Long applicantId){
        log.info("Api Layer- request received to get not applied positions for a applicant {}",applicantId);
        return recruitService.getAvailablePositions(applicantId).stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("applicant-details/{applicant-id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "get applicant details for a applicant", description = "get the applicant applicant details with positions and updated cv for the particular applicant")
    public ApplicantInfo getApplicantData(@PathVariable("applicant-id") Long applicantId){
        log.info("Api Layer- request received to get applicant details for a applicant {}",applicantId);
        return recruitService.getApplicantData(applicantId);
    }

}
