package com.recruiter.recruite.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplicantPositionRequest {

    private Long jobApplicantId;

    private MPositionInfo mPositionInfo;

}
