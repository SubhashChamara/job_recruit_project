package com.recruiter.recruite.model;


import com.recruiter.recruite.constants.UserType;
import com.recruiter.recruite.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobApplicantRequest{

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

    private Long latestCvId;

}
