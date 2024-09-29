package com.recruiter.recruite.service.util;

import com.recruiter.recruite.entity.JobApplicant;
import com.recruiter.recruite.entity.MPosition;
import com.recruiter.recruite.entity.User;
import com.recruiter.recruite.model.ApplicantInfo;
import com.recruiter.recruite.model.JobApplicantRequest;
import com.recruiter.recruite.model.MPositionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Mapper {

    public JobApplicant toEntity(JobApplicantRequest model) {
        JobApplicant entity = new JobApplicant();

        BeanUtils.copyProperties(model,entity);
        return entity;
    }

    public MPosition toEntity(MPositionInfo model) {
        MPosition entity = new MPosition();

        BeanUtils.copyProperties(model,entity);
        return entity;
    }

    public MPositionInfo fromEntity(MPosition entity) {
        MPositionInfo model = new MPositionInfo();

        BeanUtils.copyProperties(entity,model);
        return model;
    }

}
