package com.ms.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;

@Component
public class UserProducer {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Value(value = "${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail (UserModel userModel, String cotacoes) {
		var emailDto = new EmailDto();
		emailDto.setUserId(userModel.getUserId());
		emailDto.setEmailTo(userModel.getEmail());
		emailDto.setSubject("Cadastro Realizado com Sucesso!");
		emailDto.setText(userModel.getName() + ", Seja Bem vindo(a)! \n Agradecemos o seu cadastro, Seu saldo é de: "+"R$ "+ userModel.getBalance() + 
				"\n Suas moedas escolhidas foram: " + userModel.getCoin() + "\n Abaixo segue seu saldo nas moedas escolhidas com base na cotação atual: "+ cotacoes +
				"\n Em breve recebera uma planilha com tendências, agradecemos a compreensão."
				);
		
		
		rabbitTemplate.convertAndSend("", routingKey, emailDto);
	}
}
