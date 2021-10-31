package br.com.codezera.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Lucas Copque
 * @since 30/10/2021
 */

@Entity
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funcionario_x_credencial")
public class FuncionarioCredencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    private Date lastUpdated;

    private Integer acessos;

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Credencial credencial;

    @ElementCollection
    private List<String> warning;
}
