package br.com.teste.demo.service;

import br.com.teste.demo.dto.CargoDTO;
import br.com.teste.demo.model.Cargo;

import java.util.List;

public interface CargoService {

    CargoDTO save(CargoDTO dto);

    CargoDTO update(CargoDTO dto);

    Cargo findByIdInterno(Long id);

    void delete(Long id);

    CargoDTO montarDTO(Cargo entity);

    List<CargoDTO> getAll();

    CargoDTO getById(Long id);

}
