package com.visual.app.api.resources.autor.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by Francislin Dos Reis on 19/07/18.
 */

@Data
public class AutorRequest {

    @NotBlank(message = "The nome of the party can not be empty!")
    @Size(max = 50)
    private String nome;
}
