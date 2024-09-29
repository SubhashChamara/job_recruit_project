package com.recruiter.recruite.model;


import com.recruiter.recruite.constants.UserType;
import com.recruiter.recruite.entity.JobApplicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplicantInfo {

    private String  firstName;

    private String  lastName;

    private String  userName;

    private String  password;

    private String  email;

    private UserType type;

    private String nic;

    private String contactNumber;

    protected Boolean openToWork;

    private List<MPositionInfo> appliedPositions;

    private DocumentInfo latestCvInfo;

    public ApplicantInfo(JobApplicant jobApplicant) {
        BeanUtils.copyProperties(jobApplicant, this);
    }
}
