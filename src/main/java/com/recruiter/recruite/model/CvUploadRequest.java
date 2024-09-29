package com.recruiter.recruite.model;


import com.recruiter.recruite.constants.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CvUploadRequest {

    private Long jobApplicantId;

    private Long latestCvId;

}
