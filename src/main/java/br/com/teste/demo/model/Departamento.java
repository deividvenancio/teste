package br.com.teste.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "departamento")
@Data
@EqualsAndHashCode(of = {"id"})
public class Departamento {

    @Id
    @Column(name = "departamento_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="departamento_sequence")
    @SequenceGenerator(name="departamento_sequence", sequenceName="departamento_seq", allocationSize = 0)
    private Long id;

    @Column(name = "departamento_name")
    private String name;
}
