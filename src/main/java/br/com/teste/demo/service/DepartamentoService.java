package br.com.teste.demo.service;

import br.com.teste.demo.dto.DepartamentoDTO;
import br.com.teste.demo.model.Departamento;

import java.util.List;

public interface DepartamentoService {

    DepartamentoDTO save(DepartamentoDTO dto);

    DepartamentoDTO update(DepartamentoDTO dto);

    Departamento findByIdInterno(Long id);

    Departamento findByName(String departamentoName);

    void delete(Long id);

    DepartamentoDTO montarDTO(Departamento entity);

    List<DepartamentoDTO> getAll();

    DepartamentoDTO getById(Long id);

}
