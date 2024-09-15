package com.example.sugestoes.controllers.dtos;

import com.example.sugestoes.entities.Sugestao;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "Resposta de comentário")
public class ComentarioResponse {
    @Schema(description = "ID do comentário", example = "1")
    private Long id;
    @Schema(description = "Sugestão a que se refere o comentário", example = "{\"id\": 1, \"titulo\": \"Sugestão de melhoria\", \"descricao\": \"Sugestão para melhoria do produto ou serviço\", \"dataEnvio\": \"2024-09-14T16:24:50.343Z\", \"dataAtualizacao\": \"2024-09-14T16:24:50.343Z\"}")
    private Sugestao sugestao;
    @Schema(description = "Texto do comentário", example = "Obrigado pela sugestão")
    private String texto;
    @Schema(description = "Data e hora em que o comentário foi enviado", pattern = "yyyy-MM-dd'T'HH:mm:ss", example = "2024-09-14T16:24:50.343Z", type = "string")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataEnvio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sugestao getSugestao() {
        return sugestao;
    }

    public void setSugestao(Sugestao sugestao) {
        this.sugestao = sugestao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
