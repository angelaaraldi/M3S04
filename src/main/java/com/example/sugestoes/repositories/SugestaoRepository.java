package com.example.sugestoes.repositories;

import com.example.sugestoes.entities.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, Long> {
    List<Sugestao> findByTituloContainingIgnoreCase(String titulo);
}
