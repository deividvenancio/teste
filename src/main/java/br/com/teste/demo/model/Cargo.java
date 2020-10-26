package br.com.teste.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
@Data
@EqualsAndHashCode(of = {"id"})
public class Cargo {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="cargo_sequence")
    @SequenceGenerator(name="cargo_sequence", sequenceName="cargo_seq", allocationSize = 0)
    @Column(name = "cargo_id")
    private Long id;

    @Column(name = "cargo_name", length = 50)
    private String name;
}
