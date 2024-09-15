package com.example.sugestoes.mappers;

import com.example.sugestoes.controllers.dtos.ComentarioRequest;
import com.example.sugestoes.controllers.dtos.ComentarioResponse;
import com.example.sugestoes.entities.Comentario;

public class ComentarioMapper {
    public static Comentario map(ComentarioRequest source) {
        Comentario target = new Comentario();
        target.setTexto(source.getTexto());
        return target;
    }
    public static ComentarioResponse map(Comentario source) {
        ComentarioResponse target = new ComentarioResponse();
        target.setId(source.getId());
        target.setSugestao(source.getSugestao());
        target.setTexto(source.getTexto());
        target.setDataEnvio(source.getDataEnvio());
        return target;
    }
}
