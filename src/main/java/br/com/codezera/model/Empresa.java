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
@Table(name = "empresa")
public class Empresa {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)



    private Long id;

    @Column(unique = true)
    private String nome;

    @Column(unique = true)
    private String cnpj;
}
