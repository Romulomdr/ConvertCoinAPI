package com.ms.emails.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.emails.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{

}
