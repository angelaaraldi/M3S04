package com.example.sugestoes.exceptions.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.FieldError;
@Schema(description = "Resposta de erro")
public class ErroResponse {
    @Schema(description = "Campo em que ocorreu o erro", example = "id")
    private String campo;
    @Schema(description = "Mensagem de erro", example = "Sugestão não encontrada com o ID 1")
    private String mensagem;

    public ErroResponse() {
    }

    public ErroResponse(FieldError erro) {
        this.campo = erro.getField();
        this.mensagem = erro.getDefaultMessage();
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
