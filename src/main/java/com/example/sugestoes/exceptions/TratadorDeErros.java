package com.example.sugestoes.exceptions;

import com.example.sugestoes.exceptions.dtos.ErroResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroResponse>> trataArgumentoInvalido(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<ErroResponse> responseList = fieldErrors.stream().map(ErroResponse::new).toList();
        return ResponseEntity.badRequest().body(responseList);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> trataMensagemIlegivel(HttpMessageNotReadableException exception) {
        ErroResponse response = new ErroResponse();
        try {
            response.setCampo(exception.getCause().toString().split("\"")[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                response.setCampo(exception.getCause().toString().split("\"")[1]);
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        } catch (NullPointerException ignored) {}
        response.setMensagem(exception.getLocalizedMessage());
        return ResponseEntity.badRequest().body(response);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroResponse> trataRestricaoViolada(ConstraintViolationException exception) {
        ErroResponse response = new ErroResponse();
        response.setCampo(exception.getConstraintViolations().toString().split("', propertyPath=|, rootBeanClass=")[1]);
        response.setMensagem(exception.getConstraintViolations().toString().split("interpolatedMessage='|', propertyPath=")[1]);
        return ResponseEntity.badRequest().body(response);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroResponse> trataEntidadeNaoEncontrada(EntityNotFoundException exception) {
        ErroResponse erro = new ErroResponse();
        erro.setCampo("id");
        erro.setMensagem(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
