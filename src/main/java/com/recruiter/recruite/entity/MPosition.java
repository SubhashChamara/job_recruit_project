package com.recruiter.recruite.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "M_POSITION")
@SequenceGenerator(name = "M_POSITION_SEQ_GENERATOR", sequenceName = "ID_SEQ_M_POSITION", allocationSize = 1)
public class MPosition extends MBaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "M_POSITION_SEQ_GENERATOR")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String  name;


}
