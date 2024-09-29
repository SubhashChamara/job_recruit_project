package com.recruiter.recruite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class MBaseEntity implements Serializable {

    @Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "bit default 1", length = 1)
    protected Boolean active;

    @Column(name = "CREATE_BY", updatable = false, columnDefinition = "nvarchar(64)")
    protected String createdBy;

    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false)
    protected LocalDateTime createdDate;

    @Column(name = "UPDATED_BY", columnDefinition = "nvarchar(64)")
    protected String updatedBy;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    protected LocalDateTime updatedDate;

    @PrePersist
    private void setActive() {
        this.active = true;
        this.createdDate = LocalDateTime.now();
    }
}
