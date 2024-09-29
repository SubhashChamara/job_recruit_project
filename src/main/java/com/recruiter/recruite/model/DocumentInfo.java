package com.recruiter.recruite.model;

import com.recruiter.recruite.constants.DocumentType;
import com.recruiter.recruite.entity.Document;
import com.recruiter.recruite.entity.MBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentInfo {

    private Long id;

    private String name;

    private String path;

    private String absolutePath;

    private DocumentType type;

    public DocumentInfo(Document document) {
        BeanUtils.copyProperties(document, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DocumentInfo module = (DocumentInfo) o;
        return getId() != null && Objects.equals(getId(), module.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
