package br.com.teste.demo.service;

import br.com.teste.demo.dto.FuncionarioDepartamentoDTO;
import br.com.teste.demo.model.FuncionarioDepartamento;

import java.util.List;

public interface FuncionarioDepartamentoService {

    FuncionarioDepartamentoDTO save(FuncionarioDepartamentoDTO dto);

    FuncionarioDepartamentoDTO update(FuncionarioDepartamentoDTO dto);

    FuncionarioDepartamento findByIdInterno(Long id);

    void delete(Long id);

    FuncionarioDepartamentoDTO montarDTO(FuncionarioDepartamento entity);

    List<FuncionarioDepartamentoDTO> getAll();

    List<FuncionarioDepartamentoDTO> getByDepartamento(String departamento);

    List<FuncionarioDepartamentoDTO> getByFuncionarioId(Long funcionarioId);

    FuncionarioDepartamentoDTO getById(Long id);

}
