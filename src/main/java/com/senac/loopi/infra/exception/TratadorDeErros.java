package com.senac.loopi.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity tratarErroRegraDeNegocio(RuntimeException ex){
        return ResponseEntity.badRequest().body(new DadosErroValidacao(ex.getMessage()));
    }

    private record DadosErroValidacao(String erro) {
    }

}
