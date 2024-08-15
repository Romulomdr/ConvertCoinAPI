package com.ms.user.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
		var userModel = new UserModel();
		
		// Convertendo de Dto para model
		BeanUtils.copyProperties(userRecordDto, userModel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
	}
	@GetMapping("/users")
    public ResponseEntity<List<UserModel>> getUsers() {
        
		List<UserModel> users = userService.getAll();
       
		return ResponseEntity.status(HttpStatus.OK).body(users);
    }
	
    @GetMapping("/email/{email}")
    public ResponseEntity<UserModel> getUserByEmail(@PathVariable("email") String email) {
        Optional<UserModel> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/depositar/{email}/{saldo}")
    public ResponseEntity<UserModel> updateSaldo(@PathVariable("email") String email, @PathVariable("saldo") Double saldo) {
    	return userService.depositar(email, saldo);
    }
    
    @GetMapping("/conversao/{email}")
    public void conversao(@PathVariable("email") String email){
    	userService.converterMoeda(email);
    }
    
    
}
