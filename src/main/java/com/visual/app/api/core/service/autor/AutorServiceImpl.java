package com.visual.app.api.core.service.autor;

import com.visual.app.api.core.exceptions.EntityAlreadyExistsException;
import com.visual.app.api.core.exceptions.EntityNotFoundException;
import com.visual.app.api.core.repository.autor.AutorRepository;
import com.visual.app.api.core.repository.autor.model.AutorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Francislin Dos Reis on 19/07/18.
 */

@Slf4j
@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    AutorRepository autorRepository;

    @Override
    public void saveAutor(AutorEntity autorEntity) {

        hasConflictName(autorEntity.getNome());
        persistAutor(autorEntity);
    }

    @Override
    public void updateAutor(AutorEntity autorEntity) {

        AutorEntity findAutor = findById(autorEntity.getId());
        AutorEntity updateAutorEntity = AutorEntity.builder().id(findAutor.getId()).nome(autorEntity.getNome()).build();
        persistAutor(updateAutorEntity);

    }

    @Override
    public AutorEntity findAutorEntityByNome(String nome) {
        Optional<AutorEntity> autorEntity = autorRepository.findByNome(nome);
        return autorEntity.orElseThrow(() -> new EntityNotFoundException(String.format("Autor not found for nome='%s'", nome)));
    }

    @Override
    public List<AutorEntity> findAllAutores() {
        List<AutorEntity> listAutores = autorRepository.findAll();
        return listAutores;
    }

    @Override
    public void deleteAutorEntity(Long id) {

        AutorEntity toDelete = findById(id);

        log.info("autorEntity={} message=delete_successfully", toDelete);
        this.autorRepository.delete(toDelete);
    }

    private AutorEntity persistAutor(AutorEntity autorEntity) {
        AutorEntity saveAutorEntity = autorRepository.save(autorEntity);
        return saveAutorEntity;
    }

    private AutorEntity findById(Long id) {
        return autorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Autor not found for id='%s'", id)));
    }

    private void hasConflictName(String nome) {
        if (autorRepository.findByNome(nome).isPresent()) {
            throw new EntityAlreadyExistsException(String.format("AutorEntity with nome='%s' already exists", nome));
        }
    }
}
