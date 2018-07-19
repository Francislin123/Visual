package com.visual.app.api.resources.autor;

import com.visual.app.api.core.exceptions.EntityNotFoundException;
import com.visual.app.api.core.repository.autor.model.AutorEntity;
import com.visual.app.api.core.service.autor.AutorService;
import com.visual.app.api.resources.autor.request.AutorRequest;
import com.visual.app.api.resources.autor.response.AutorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Francislin Dos Reis on 19/07/18.
 */

@Slf4j
@RestController
@RequestMapping(AutorController.URI_AUTOR)
public class AutorController {

    public static final String URI_AUTOR = "/v1/autors";

    @Autowired
    private AutorService autorService;


    /**
     * POST  /v1/autors : Create a new one autorEntity.
     *
     * @param autorRequest the AutorRequest to create
     * @return the ResponseEntity with status 201 (Created) and with body the new AutorRequest, or with status 400
     * (Bad Request) if the Autor has already an name
     * @throws EntityNotFoundException When the author already exists this exception
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createAutor(@Valid @RequestBody AutorRequest autorRequest, UriComponentsBuilder builder) {

        AutorEntity autorEntity = AutorEntity.builder()
                .nome(autorRequest.getNome()).build();

        autorService.saveAutor(autorEntity);

        UriComponents uriComponents = builder.path(URI_AUTOR.concat("/{nome}")).buildAndExpand(autorEntity.getNome());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    /**
     * PUT  /v1/autors/nome : Update a new one of AutorEntity.
     *
     * @param autorRequest the AutorRequest to create
     * @return the ResponseEntity with status 200 (Update) and with body the new AutorRequest, or with status 400
     * (Bad Request) if the Autor has already an name
     * @throws EntityNotFoundException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAutot(@Valid @RequestBody AutorRequest autorRequest, @PathVariable(value = "id") Long id) {

        AutorEntity autorEntity = AutorEntity.builder().id(id).nome(autorRequest.getNome()).build();

        autorService.updateAutor(autorEntity);

        return ResponseEntity.ok().build();
    }

    /**
     * GET  /v1/autors/ : List a new autorEntity.
     *
     * @return List of AutorResponse with status 200 (OK) and with body the new AutorRequest, or with status 400
     * (Bad Request) if the Autors has already an name
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CollectionResponse<AutorResponse>> listAllAutores() {

        List<AutorEntity> allAutoresList = autorService.findAllAutores();

        return ResponseEntity.ok().body(CollectionResponse.<AutorResponse>builder()
                .result(allAutoresList.stream().map(a ->
                        AutorResponse.builder().id(a.getId()).nome(a.getNome()).build()).collect(Collectors.toList())).build());
    }

    /**
     * GET  /v1/autors/{nome} : Get one autor by nome.
     *
     * @return Search of AutorResponse with status 204 (noContent) or with status 400
     */
    @RequestMapping(value = "/{nome}", method = RequestMethod.GET)
    public AutorEntity getOneAutor(@PathVariable("nome") String nome) {

        AutorEntity listAutor = autorService.findAutorEntityByNome(nome);

        return listAutor;
    }

    /**
     * DELETE  /v1/autors/{id} : Delete one autor by id.
     *
     * @return Search of AutorResponse with status 204 (noContent) or with status 400
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAutor(@PathVariable("id") Long id) {

        autorService.deleteAutorEntity(id);

        return ResponseEntity.noContent().build();
    }
}
