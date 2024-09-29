package com.recruiter.recruite.model;

import com.recruiter.recruite.entity.MPosition;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MPositionInfo {
    private Long id;
    private String  name;

    public MPositionInfo(MPosition mPosition) {
        BeanUtils.copyProperties(mPosition,this);
    }
}
