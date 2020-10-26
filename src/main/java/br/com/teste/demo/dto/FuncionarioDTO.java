package br.com.teste.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FuncionarioDTO {

    private Long id;

    private String name;

    private Long age;

    private Date birthday;

    private String document;

    private CargoDTO cargoDTO;

}
