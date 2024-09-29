package com.recruiter.recruite.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "USER_LOGIN_OTP")
@SequenceGenerator(name = "USER_LOGIN_OTP_SEQ_GENERATOR", sequenceName = "ID_SEQ_USER_LOGIN_OTP", allocationSize = 1)
public class UserLoginOTP implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_LOGIN_OTP_SEQ_GENERATOR")
    private Long id;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "FK_USER",nullable = false)
    private User user;

    @Column(name = "CODE",nullable = false)
    private String code;

    @Column(name = "EXPIRY_TIME",nullable = false)
    private Date expiryTime;

    @Column(name = "IS_USED" ,columnDefinition = "bit default 0", length = 1)
    private Boolean isUsed;


}
