package com.ms.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.user.dtos.CotacaoDto;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;
import com.ms.user.producers.UserProducer;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private static final String apiExterna = "https://economia.awesomeapi.com.br/";
	
	@Autowired
	UserProducer userProducer;
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public UserModel save(UserModel userModel) {
		
		userModel = userRepository.save(userModel);
		String cotacoes = converterMoeda(userModel.getEmail()).toString();
		userProducer.publishMessageEmail(userModel, cotacoes);
		
		return userModel;
	}
	
    public List<UserModel> getAll() {
        return userRepository.findAll();
    }
    public Optional<UserModel> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public ResponseEntity<UserModel> depositar(String email, Double balance) {
        Optional<UserModel> optionalUser = getUserByEmail(email);
        
        Double saldoAnterior = optionalUser.get().getBalance();
        
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            
            if(saldoAnterior == null) {
            	user.setBalance(balance);
            }else {
            	user.setBalance(balance + saldoAnterior);
            }
 
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    public String converterMoeda(String email) {
        Optional<UserModel> optionalUser = getUserByEmail(email);
        
        if (optionalUser.isPresent() && optionalUser.get().getCoin() != null && !optionalUser.get().getCoin().isEmpty()) {
            String[] moedas = optionalUser.get().getCoin().split(",");
            StringBuilder sb = new StringBuilder();
            
            for (String moeda : moedas) {
                ResponseEntity<CotacaoDto[]> response = restTemplate.getForEntity(apiExterna + moeda, CotacaoDto[].class);
                CotacaoDto[] cotacoes = response.getBody();
                if (cotacoes != null && cotacoes.length > 0) {
                    CotacaoDto cotacao = cotacoes[0];
                    double high = Double.parseDouble(cotacao.getHigh());
                    double novoSaldo = optionalUser.get().getBalance() / high;
                    sb.append(String.format("\n Saldo em %s: %.2f", moeda, novoSaldo));
                }
            }
            return sb.toString();
        }else {
        	return null;
        }
    }
}
