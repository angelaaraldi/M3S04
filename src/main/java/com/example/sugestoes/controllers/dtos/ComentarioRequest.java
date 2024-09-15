package com.example.sugestoes.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
@Schema(description = "Requisição de comentário")
public class ComentarioRequest {
    @Schema(description = "Texto do comentário", example = "Obrigado pela sugestão")
    @NotBlank
    private String texto;

    public @NotBlank String getTexto() {
        return texto;
    }

    public void setTexto(@NotBlank String texto) {
        this.texto = texto;
    }
}
