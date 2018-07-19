package com.visual.app.api.core.service.autor;

import com.visual.app.api.core.repository.autor.model.AutorEntity;

import java.util.List;

/**
 * Created by Francislin Dos Reis on 12/12/17.
 */

public interface AutorService {

    void saveAutor(AutorEntity autorEntity);

    void updateAutor(AutorEntity autorEntity);

    AutorEntity findAutorEntityByNome(String nome);

    List<AutorEntity> findAllAutores();

    void deleteAutorEntity(Long id);
}
