package com.ms.emails.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.emails.enums.StatusEmail;
import com.ms.emails.models.EmailModel;
import com.ms.emails.repositories.EmailRepository;

@Service
public class EmailServices {

	@Autowired
	EmailRepository emailRepository;
	
	@Autowired
	JavaMailSender emailSender;
	
	@Value(value="${spring.mail.username}")
	private String emailFrom;
	
	@SuppressWarnings("finally")
	@Transactional
	public EmailModel sendEmail (EmailModel emailModel) {
		try {
			emailModel.setSendDateEmail(LocalDateTime.now());
			emailModel.setEmailFrom(emailFrom);
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setFrom(emailModel.getEmailFrom());
			message.setText(emailModel.getText());
			emailSender.send(message);
			emailModel.setStatusEmail(StatusEmail.SENT);
		}
		catch (MailException e){
			emailModel.setStatusEmail(StatusEmail.ERROR);
		}
		finally{
			return emailRepository.save(emailModel);
			
		}
	}
}
