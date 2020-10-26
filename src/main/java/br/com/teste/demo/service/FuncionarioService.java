package br.com.teste.demo.service;

import br.com.teste.demo.dto.FuncionarioDTO;
import br.com.teste.demo.model.Funcionario;

import java.util.List;

public interface FuncionarioService {

    FuncionarioDTO save(FuncionarioDTO dto);

    FuncionarioDTO update(FuncionarioDTO dto);

    Funcionario findByIdInterno(Long id);

    void delete(Long id);

    FuncionarioDTO montarDTO(Funcionario entity);

    List<FuncionarioDTO> getAll();

    FuncionarioDTO getById(Long id);

}
