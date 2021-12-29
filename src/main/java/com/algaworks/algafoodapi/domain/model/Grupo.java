package com.algaworks.algafoodapi.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "tab_grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "grupo_nome")
    private String nome;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "tab_grupo_permissoes",
            joinColumns = @JoinColumn(name = "tab_grupo_id"),
            inverseJoinColumns = @JoinColumn(name = "tab_permissao_id"))
    private List<Permissao> permissoes = new ArrayList<>();

}
