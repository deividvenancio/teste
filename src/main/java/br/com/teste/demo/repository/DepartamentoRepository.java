package br.com.teste.demo.repository;

import br.com.teste.demo.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    Optional<Departamento> findByName(String departamentoName);
}
