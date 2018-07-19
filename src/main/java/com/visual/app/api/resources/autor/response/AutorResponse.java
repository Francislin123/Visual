package com.visual.app.api.resources.autor.response;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Francislin Dos Reis on 19/07/18.
 */

@Data
@Builder
public class AutorResponse {

    private Long id;

    private String nome;
}
