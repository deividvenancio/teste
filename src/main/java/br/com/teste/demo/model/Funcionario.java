package br.com.teste.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "funcionario")
@Data
@EqualsAndHashCode(of = {"id"})
public class Funcionario {

    @Id
    @Column(name = "funcionario_id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="funcionario_sequence")
    @SequenceGenerator(name="funcionario_sequence", sequenceName="funcionario_seq", allocationSize = 0)
    private Long id;

    @Column(name = "funcionario_name", length = 50)
    private String name;

    @Column(name = "age")
    private Long age;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "funcionario_birthday")
    private Date birthday;

    @Column(name = "funcionario_document", length = 50)
    private String document;

    @ManyToOne()
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

}
