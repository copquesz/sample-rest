package br.com.codezera.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private String cpf;

    private String profissao;

    private Double salario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credencial_id")
    private Credencial credencial;
}

