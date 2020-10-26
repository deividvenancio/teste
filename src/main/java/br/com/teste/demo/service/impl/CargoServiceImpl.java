package br.com.teste.demo.service.impl;

import br.com.teste.demo.dto.CargoDTO;
import br.com.teste.demo.model.Cargo;
import br.com.teste.demo.repository.CargoRepository;
import br.com.teste.demo.service.CargoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public CargoDTO save(CargoDTO dto) {
        return montarDTO(cargoRepository.save(montarEntity(dto)));
    }

    @Override
    public CargoDTO update(CargoDTO dto) {
        findByIdInterno(dto.getId());
        return montarDTO(cargoRepository.save(montarEntity(dto)));
    }

    @Override
    public Cargo findByIdInterno(Long id) {
        return cargoRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Cargo não encontrado"));
    }

    @Override
    public void delete(Long id) {
        Cargo cargo = findByIdInterno(id);
        cargoRepository.delete(cargo);
    }

    @Override
    public CargoDTO montarDTO(Cargo entity) {
        return modelMapper.map(entity, CargoDTO.class);
    }

    @Override
    public List<CargoDTO> getAll() {
        return cargoRepository.findAll().stream().map(this::montarDTO).collect(Collectors.toList());
    }

    @Override
    public CargoDTO getById(Long id) {
        return montarDTO(findByIdInterno(id));
    }

    private Cargo montarEntity(CargoDTO dto) {
        return modelMapper.map(dto, Cargo.class);
    }
}
