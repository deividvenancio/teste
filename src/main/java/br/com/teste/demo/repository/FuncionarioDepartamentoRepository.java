package br.com.teste.demo.repository;

import br.com.teste.demo.model.Departamento;
import br.com.teste.demo.model.Funcionario;
import br.com.teste.demo.model.FuncionarioDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioDepartamentoRepository extends JpaRepository<FuncionarioDepartamento, Long> {


    Optional<FuncionarioDepartamento> findByFuncionarioAndAtivo(Funcionario funcionario,
                                                                boolean ativo);

    List<FuncionarioDepartamento> findByDepartamentoAndAtivo(Departamento departamento, Boolean Ativo);

    List<FuncionarioDepartamento> findByFuncionario(Funcionario funcionario);
}
