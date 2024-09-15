package com.example.sugestoes.mappers;

import com.example.sugestoes.controllers.dtos.SugestaoGetResponse;
import com.example.sugestoes.controllers.dtos.SugestaoRequest;
import com.example.sugestoes.controllers.dtos.SugestaoResponse;
import com.example.sugestoes.entities.Sugestao;

public class SugestaoMapper {
    public static Sugestao map(SugestaoRequest source) {
        Sugestao target = new Sugestao();
        target.setTitulo(source.getTitulo());
        target.setDescricao(source.getDescricao());
        return target;
    }
    public static SugestaoResponse map(Sugestao source) {
        SugestaoResponse target = new SugestaoResponse();
        target.setId(source.getId());
        target.setTitulo(source.getTitulo());
        target.setDescricao(source.getDescricao());
        target.setDataEnvio(source.getDataEnvio());
        target.setDataAtualizacao(source.getDataAtualizacao());
        return target;
    }
    public static SugestaoGetResponse map(SugestaoResponse source) {
        SugestaoGetResponse target = new SugestaoGetResponse();
        target.setId(source.getId());
        target.setTitulo(source.getTitulo());
        target.setDescricao(source.getDescricao());
        target.setDataEnvio(source.getDataEnvio());
        target.setDataAtualizacao(source.getDataAtualizacao());
        return target;
    }
}
