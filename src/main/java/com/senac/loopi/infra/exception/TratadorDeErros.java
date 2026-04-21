package com.senac.loopi.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    // Trata os erros de regra de negócio
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity tratarErroRegraDeNegocio(RuntimeException ex){
        return ResponseEntity.badRequest().body(new DadosErroGeral(ex.getMessage()));
    }

    // Trata os erros do @Valid (campos em branco ou errados no JSON)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroValidacaoFormulario(MethodArgumentNotValidException ex) {
        // Pega todos os erros que o Spring encontrou no JSON
        List<FieldError> erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroCampo::new).toList());
    }

    // --- DTOs internos para formatar a resposta ---

    // DTO para erros gerais (ex: "Rota não encontrada")
    private record DadosErroGeral(String erro) {}

    // DTO para erros de campos (ex: campo "nome", mensagem "não deve estar em branco")
    private record DadosErroCampo(String campo, String mensagem) {
        public DadosErroCampo(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}