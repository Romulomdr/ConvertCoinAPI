package com.ms.user.apiexterna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.user.dtos.CotacaoDto;
import com.ms.user.services.UserService;
import com.ms.user.models.UserModel;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("consulta-cotacao")
public class ConsultaCotacao {

	@Autowired
	UserService userService;
	
    @GetMapping("{email}")
    public List<CotacaoDto> consultaCotacao(@PathVariable("email") String email) {
    	
        RestTemplate restTemplate = new RestTemplate();
        
        UserModel user = userService.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        
        String url = String.format("https://economia.awesomeapi.com.br/json/%s", user.getMoeda());
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<CotacaoDto> cotacaoDtos = null;

        try {
            cotacaoDtos = objectMapper.readValue(response.getBody(), new TypeReference<List<CotacaoDto>>() {});
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }

        return cotacaoDtos;
    }
}