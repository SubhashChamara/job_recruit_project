package com.recruiter.recruite.entity;

import com.recruiter.recruite.constants.DocumentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DOCUMENT")
@SequenceGenerator(name = "DOCUMENT_SEQ_GENERATOR", sequenceName = "ID_SEQ_DOCUMENT", allocationSize = 1)
public class Document extends MBaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_SEQ_GENERATOR")
    private Long id;
    
    @Column(name = "NAME", nullable = false, length = 225)
    private String name;

    @Column(name = "PATH", nullable = false, length = 500)
    private String path;

    @Column(name = "ABSOLUTE_PATH", nullable = false, length = 500)
    private String absolutePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private DocumentType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Document module = (Document) o;
        return getId() != null && Objects.equals(getId(), module.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
