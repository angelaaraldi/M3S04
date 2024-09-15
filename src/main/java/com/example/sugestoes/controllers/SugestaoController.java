package com.example.sugestoes.controllers;

import com.example.sugestoes.controllers.dtos.*;
import com.example.sugestoes.services.ComentarioService;
import com.example.sugestoes.services.SugestaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sugestões", description = "Postar e obter sugestões e comentários")
@RestController
@RequestMapping("/sugestoes")
public class SugestaoController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SugestaoController.class);
    private final SugestaoService service;
    private final ComentarioService comentarioService;
    public SugestaoController(SugestaoService service, ComentarioService comentarioService) {
        this.service = service;
        this.comentarioService = comentarioService;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Postar sugestão", description = "Postar uma sugestão com título e descrição")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Sugestão postada")})
    public SugestaoResponse post(@Valid @RequestBody SugestaoRequest request) {
        log.info("POST /sugestoes -> Início");
        SugestaoResponse response = service.post(request);
        log.info("POST /sugestoes -> Fim");
        return response;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "Obter sugestões", description = "Obter as sugestões, filtradas opcionalmente pelo título")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Sugestões obtidas")})
    public List<SugestaoResponse> get(@ParameterObject() SugestaoGetRequest filtro) {
        log.info("GET /sugestoes -> Início");
        List<SugestaoResponse> response = service.get(filtro);
        log.info("GET /sugestoes -> Fim");
        return response;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "Obter sugestão por ID", description = "Obter a sugestão com seus respectivos comentários")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Sugestão obtida")})
    public SugestaoGetResponse get(@Parameter(example = "1", description = "ID da sugestão") @PathVariable Long id) {
        log.info("GET /sugestoes/{} -> Início", id);
        SugestaoGetResponse response = service.get(id);
        log.info("GET /sugestoes/{} -> Fim", id);
        return response;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/comentarios")
    @Operation(summary = "Postar comentário", description = "Postar um comentário referente a determinada sugestão")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Comentário postado")})
    public ComentarioResponse post(@Parameter(example = "1", description = "ID da sugestão") @PathVariable Long id, @Valid @RequestBody ComentarioRequest request) {
        log.info("POST /sugestoes/{}/comentarios -> Início", id);
        ComentarioResponse response = comentarioService.post(id, request);
        log.info("POST /sugestoes/{}/comentarios -> Fim", id);
        return response;
    }
}
