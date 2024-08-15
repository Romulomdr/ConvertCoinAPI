package com.ms.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UserRecordDto(@NotBlank String name, 
							@NotBlank @Email String email,
							@NotEmpty String moeda,
							Double saldo) {
	
}
