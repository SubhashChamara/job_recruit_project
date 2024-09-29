package com.recruiter.recruite.entity;


import com.recruiter.recruite.constants.UserType;
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
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "USER_SEQ_GENERATOR", sequenceName = "ID_SEQ_USER", allocationSize = 1)
public class User implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String  firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String  lastName;

    @Column(name = "USER_NAME", nullable = false)
    private String  userName;

    @Column(name = "PASSWORD", nullable = false)
    private String  password;

    @Column(name = "EMAIL", nullable = false)
    private String  email;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable=false)
    private UserType type;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "bit default 1", length = 1)
    private Boolean active;

}
