package com.example.sugestoes.services;

import com.example.sugestoes.controllers.dtos.ComentarioRequest;
import com.example.sugestoes.controllers.dtos.ComentarioResponse;
import com.example.sugestoes.entities.Comentario;
import com.example.sugestoes.entities.Sugestao;
import com.example.sugestoes.repositories.ComentarioRepository;
import com.example.sugestoes.repositories.SugestaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.sugestoes.mappers.ComentarioMapper.map;

@Service
public class ComentarioService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ComentarioService.class);
    private final ComentarioRepository repository;
    private final SugestaoRepository sugestaoRepository;
    public ComentarioService(ComentarioRepository repository, SugestaoRepository sugestaoRepository) {
        this.repository = repository;
        this.sugestaoRepository = sugestaoRepository;
    }
    public ComentarioResponse post(Long id, ComentarioRequest request) {
        log.info("Postando comentário: {}", request.getTexto());
        Comentario comentario = map(request);
        Sugestao sugestao = sugestaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sugestão não encontrada com o ID " + id));
        comentario.setSugestao(sugestao);
        LocalDateTime agora = LocalDateTime.now();
        comentario.setDataEnvio(agora);
        ComentarioResponse response = map(repository.save(comentario));
        sugestao.setDataAtualizacao(agora);
        sugestaoRepository.save(sugestao);
        log.info("Comentário postado: {}", request.getTexto());
        return response;
    }
}
