package com.visual.app.api.core.repository.autor.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

/**
 * Created by Francislin Dos Reis on 19/07/18.
 */

@Entity
@Data
@Table(name = "autor")
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private String nome;

    @Tolerate
    public AutorEntity() {
        //default constructor for hibernate
    }

    @Builder
    public AutorEntity(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "AutorEntity{" + "id=" + id +", nome='" + nome + '\'' +'}';
    }
}
