package com.algaworks.algafoodapi.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "tab_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_nome")
    private String nome;

    @Column(name = "usuario_email")
    private String email;

    @Column(name = "usuario_senha")
    private String senha;

    @Column(name = "usuario_data_cadastro", nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "tab_usuario_grupo",
            joinColumns = @JoinColumn(name = "tab_usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "tab_grupo_id"))
    private List<Grupo> grupos = new ArrayList<>();

}
