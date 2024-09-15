package com.example.sugestoes.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "Resposta de sugestão")
public class SugestaoResponse {
    @Schema(description = "ID da sugestão", example = "1")
    private Long id;
    @Schema(description = "Título da sugestão", example = "Sugestão de melhoria")
    private String titulo;
    @Schema(description = "Descrição da sugestão", example = "Sugestão para melhoria do produto ou serviço")
    private String descricao;
    @Schema(description = "Data e hora em que a sugestão foi enviada", pattern = "yyyy-MM-dd'T'HH:mm:ss", example = "2024-09-14T16:24:50.343Z", type = "string")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataEnvio;
    @Schema(description = "Data e hora em que a sugestão foi atualizada (postada ou comentada)", pattern = "yyyy-MM-dd'T'HH:mm:ss", example = "2024-09-14T16:24:50.343Z", type = "string")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataAtualizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
