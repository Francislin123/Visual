package com.visual.app.api.core.repository.autor;

import com.visual.app.api.core.repository.autor.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by Francislin Dos Reis on 19/07/18.
 */

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, UUID> {

    Optional<AutorEntity> findByNome(String nome);

    Optional<AutorEntity> findById(Long id);
}
