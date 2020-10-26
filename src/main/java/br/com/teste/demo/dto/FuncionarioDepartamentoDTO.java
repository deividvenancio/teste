package br.com.teste.demo.dto;

import lombok.Data;

@Data
public class FuncionarioDepartamentoDTO {

    private Long id;

    private DepartamentoDTO departamentoDTO;

    private FuncionarioDTO funcionarioDTO;

    private Boolean ativo;
}
