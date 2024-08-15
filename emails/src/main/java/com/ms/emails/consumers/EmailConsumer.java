package com.ms.emails.consumers;

import com.ms.emails.dtos.EmailRecordDto;
import com.ms.emails.models.EmailModel;
import com.ms.emails.services.EmailServices;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

	@Autowired
	EmailServices emailService;
	
	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto ) {
		var emailModel = new EmailModel();
		BeanUtils.copyProperties(emailRecordDto, emailModel);

		// Send email
		emailService.sendEmail(emailModel);
	}
}
