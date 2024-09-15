package com.example.sugestoes.services;

import com.example.sugestoes.controllers.dtos.SugestaoGetRequest;
import com.example.sugestoes.controllers.dtos.SugestaoGetResponse;
import com.example.sugestoes.controllers.dtos.SugestaoRequest;
import com.example.sugestoes.controllers.dtos.SugestaoResponse;
import com.example.sugestoes.entities.Comentario;
import com.example.sugestoes.entities.Sugestao;
import com.example.sugestoes.repositories.ComentarioRepository;
import com.example.sugestoes.repositories.SugestaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.sugestoes.mappers.SugestaoMapper.map;

@Service
public class SugestaoService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SugestaoService.class);
    private final SugestaoRepository repository;
    private final ComentarioRepository comentarioRepository;
    public SugestaoService(SugestaoRepository repository, ComentarioRepository comentarioRepository) {
        this.repository = repository;
        this.comentarioRepository = comentarioRepository;
    }
    public SugestaoResponse post(SugestaoRequest request) {
        log.info("Postando sugestão: {}", request.getTitulo());
        Sugestao sugestao = map(request);
        LocalDateTime agora = LocalDateTime.now();
        sugestao.setDataEnvio(agora);
        sugestao.setDataAtualizacao(agora);
        SugestaoResponse response = map(repository.save(sugestao));
        log.info("Sugestão postada: {}", request.getTitulo());
        return response;
    }
    public List<SugestaoResponse> get(SugestaoGetRequest filtro) {
        log.info("Obtendo sugestões");
        String filtroTitulo = filtro.getTitulo() != null ? filtro.getTitulo() : "";
        List<Sugestao> lista = repository.findByTituloContainingIgnoreCase(filtroTitulo);
        List<SugestaoResponse> listaResponse = new ArrayList<>();
        for (Sugestao sugestao : lista) {
            listaResponse.add(map(sugestao));
        }
        listaResponse.sort((a, b) -> {
            LocalDateTime dataA = a.getDataAtualizacao();
            LocalDateTime dataB = b.getDataAtualizacao();
            if (dataA.isAfter(dataB)) {
                return -1;
            } else if (dataA.isEqual(dataB)) {
                return 0;
            } else {
                return 1;
            }
        });
        log.info("Sugestões obtidas: {}", listaResponse.size());
        return listaResponse;
    }
    public SugestaoGetResponse get(Long id) {
        log.info("Obtendo sugestão com o ID {}", id);
        Sugestao sugestao = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sugestão não encontrada com o ID " + id));
        SugestaoGetResponse response = map(map(sugestao));
        List<Comentario> comentarios = new ArrayList<>();
        for (Comentario comentario : comentarioRepository.findAll()) {
            if (Objects.equals(comentario.getSugestao().getId(), id)) {
                comentarios.add(comentario);
            }
        }
        comentarios.sort((a,b) -> {
            LocalDateTime dataA = a.getDataEnvio();
            LocalDateTime dataB = b.getDataEnvio();
            if (dataA.isAfter(dataB)) {
                return -1;
            } else if (dataA.isEqual(dataB)) {
                return 0;
            } else {
                return 1;
            }
        });
        response.setComentarios(comentarios);
        log.info("Sugestão obtida com o ID {}", id);
        return response;
    }
}
