package br.com.teste.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "funcionario_departamento")
@Data
@EqualsAndHashCode(of = {"departamento","funcionario"})
public class FuncionarioDepartamento {

    @Id
    @Column(name = "funcionario_departamento_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="funcionario_departamento_sequence")
    @SequenceGenerator(name="funcionario_departamento_sequence", sequenceName="funcionario_departamento_seq", allocationSize = 0)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @ManyToOne()
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Column(name = "ativo")
    private Boolean ativo;
}
