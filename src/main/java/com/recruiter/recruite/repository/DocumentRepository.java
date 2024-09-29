package com.recruiter.recruite.repository;

import com.recruiter.recruite.entity.Document;
import com.recruiter.recruite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {


}
